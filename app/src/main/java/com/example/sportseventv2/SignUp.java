package com.example.sportseventv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    //Variabler:

    TextInputLayout regName, regEmail, regPhoneNo, regUsername, regPassword;
    Button regButton, regToLogInButton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;




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
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");


                //get all values from the textfiles shown in .xml/activity_sign_up. (aka. de 5første lige ovenfor ved hooks) ;)

                String name = regName.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, email, phoneNo,username,password);

                reference.child(phoneNo).setValue(helperClass);

            }
        });
    }
}