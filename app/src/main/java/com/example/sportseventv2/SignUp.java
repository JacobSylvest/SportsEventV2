package com.example.sportseventv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUp";
    //Variabler:
    TextInputLayout regName, regEmail, regPhoneNo, regUsername, regPassword;
    Button regButton, regToLogInButton;
    //String events = "events";

    FirebaseDatabase rootNode;
    DatabaseReference reference, reference1, reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //Hooks til xml-koden.

        regName = findViewById(R.id.reg_fulde_navn);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_telefon);
        regUsername = findViewById(R.id.reg_brugernavn);
        regPassword = findViewById(R.id.reg_password);
        regButton = findViewById(R.id.reg_registrer_knap);
        regToLogInButton = findViewById(R.id.allerede_bruger);

        //gem data i firebase ved klik på registreringsknap.

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: Der er trykket på Registrer");
                if (!validateName() | !validateEmail() | !validatephoneNo() | !validateUsername() | !validatePassword()){
                    return;
                }
                else{
                    Toast.makeText(SignUp.this, "Oplysninger registreret. Gå tilbage til login side", Toast.LENGTH_LONG).show();
                }
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //get all values from the textfiles shown in .xml/activity_sign_up. (aka. de 5første lige ovenfor ved hooks) ;)
                String name = regName.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();
                String events = "empty";

                UserHelperClass helperClass = new UserHelperClass(name, email, phoneNo,username,password, events);

                reference.child(username).setValue(helperClass);

                /**
                 * Tilføjer et tomt event.
                 */
                reference1 = reference.child(username);
                reference2 = reference1.child("events");

                String eventChild = "ingen";
                String eTitle = "ingen tilmeldte løb";
                String description = "...";
                String imageUrl = "https://3vfjs6e58tj3yfef2wptam15-wpengine.netdna-ssl.com/wp-content/uploads/2019/04/red-x-on-network-icon.png";

                EventHelperClass ehelperClass = new EventHelperClass(eTitle, description, imageUrl,eventChild);

                reference2.child(eventChild).setValue(ehelperClass);

                Log.d(TAG, "onClick: Bruger tilføjet til Databasen med event");
            }
        });

        regToLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    /**
     * Metode til at åbne Login.class
     */
    public void openLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

    private Boolean validateName(){
        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()){
            regName.setError("Feltet må ikke være tomt");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;

        }

    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9.-_]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            regEmail.setError("Feltet må ikke være tomt");
            return false;
        }
        else if (!val.matches(emailPattern)){
            regEmail.setError("Ugyldig Email");
            return false;

        }
        else {
            regEmail.setError(null);
            return true;

        }

    }

    private Boolean validatephoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();
        if (val.isEmpty()){
            regPhoneNo.setError("Feltet må ikke være tomt");
            return false;
        }
        else {
            regPhoneNo.setError(null);
            return true;

        }

    }

    private Boolean validateUsername(){
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace ="\\A\\w{4,20}\\z";  //hvis den ikke virker; brug: "(?=\\s+$)"
        if (val.isEmpty()){
            regUsername.setError("Feltet må ikke være tomt");
            return false;
        }
        else if(val.length()>=20){
            regUsername.setError("Brugernavn er for langt. Maks 20 tegn");
            return false;
        }
        else if (!val.matches(noWhiteSpace)){
            regUsername.setError("Mellemrum er ikke tilladt");
            return false;

        }
        else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;

        }
    }

    private Boolean validatePassword(){
        String val = regPassword.getEditText().getText().toString();
        //String passwordVal = "^" +
                //"(?=.*[a-zA-Z])" +     //Any Letter
               // "(?=\\s+$)" +          //No white space
               // ".{4,}" +              //At least 4 characters
             //   "$";

        if (val.isEmpty()){
            regPassword.setError("Feltet må ikke være tomt");
            return false;
        }
        /*else if (!val.matches(passwordVal)){
            regPassword.setError("Password er for svagt");
            return false;

        }

         */
        else {
            regPassword.setError(null);
            return true;

        }

    }
}