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

                if (!validateName() | !validateEmail() | !validatephoneNo() | !validateUsername() | !validatePassword()){
                    return;
                }
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
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

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