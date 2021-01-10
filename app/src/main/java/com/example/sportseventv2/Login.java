package com.example.sportseventv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {

    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this hides status bar on screen

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.ny_bruger);
        image = findViewById(R.id.logo_login);
        logoText = findViewById(R.id.velkommen_besked);
        sloganText = findViewById(R.id.log_ind_besked);
        username = findViewById(R.id.brugernavn);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.fortsæt_login);



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(v);
            }
        });




        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "slogan_text");
                pairs[2] = new Pair<View, String>(sloganText, "log_ind_besked");
                pairs[3] = new Pair<View, String>(username, "brugernavn_slot");
                pairs[4] = new Pair<View, String>(password, "password_slot");
                pairs[5] = new Pair<View, String>(login_btn, "fortsæt_knap");
                pairs[6] = new Pair<View, String>(callSignUp, "logind_signind_knap");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent, options.toBundle());

            }
        });
    }

    private Boolean validateUsername(){
        String val = username.getEditText().getText().toString();

        if (val.isEmpty()){
            username.setError("Feltet skal udfyldes");
            return false;
        }
        else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();

        if (val.isEmpty()){
            password.setError("Feltet skal udfyldes");
            return false;
        }
        else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view){
        //validate login info
        if (!validateUsername() | !validatePassword()){
            return;
        }
         else{
             isUser();
        }
    }


    private void isUser() {

        String userEnteredUsername = username.getEditText().getText().toString().trim();
        String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);


        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)){

                        username.setError(null);
                        username.setErrorEnabled(false);

                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = dataSnapshot.child(userEnteredPassword).child("phoneNo").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("emial").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),Profil.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("emial",emailFromDB);
                        intent.putExtra("phoneNo",phoneNoFromDB);
                        intent.putExtra("password",passwordFromDB);

                        startActivity(intent);
                    }
                    else {
                        password.setError("Forkert password");
                        password.requestFocus();
                    }
                }
                else{
                    username.setError("Bruger findes ikke");
                    username.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



     //Metode til at åbne løb.class

    public void openBottomNavigation(){
        Intent intent = new Intent(this, Løb.class);
        startActivity(intent);

    }

}