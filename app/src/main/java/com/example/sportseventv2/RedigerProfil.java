package com.example.sportseventv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RedigerProfil extends TopBundMenu {

    private static final String TAG = "redigerProfil";
    EditText edit_fullname, edit_password, edit_email, edit_phone;

    String full_name, email, phone, password, username;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rediger_profil);
        showNavProfil();

        reference = FirebaseDatabase.getInstance().getReference("users");

        //Hooks
        edit_fullname = findViewById(R.id.full_name_edit);
        edit_email = findViewById(R.id.email_edit);
        edit_phone = findViewById(R.id.phone_edit);
        edit_password = findViewById(R.id.password_edit);


        //Viser al Data
        getUserData();
    }

    //Henter bruger informationer.
    private void getUserData(){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);//Bruger nøgle userInfo og henter privat.
        full_name = sharedPreferences.getString("fullname","");//henter string med unik nøgle og sætter lig full_name
        email = sharedPreferences.getString("email","");
        Log.d(TAG, "getUserData: "+ sharedPreferences.getString("email",""));
        phone = sharedPreferences.getString("phoneno","");
        password = sharedPreferences.getString("passwordpass","");
        username = sharedPreferences.getString("username","");

        edit_fullname.setText(full_name);
        edit_email.setText(email);
        edit_phone.setText(phone);
        edit_password.setText(password);
    }
    //update knap i xml.
    public void update(View view){
        if (isInfoChanged()){
            Toast.makeText(this,"Information er blevet opdateret", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"Information er er den samme", Toast.LENGTH_LONG).show();

    }

//metodernde nedenfor burde kalde frem og tilbage med databasen, men der er noget galt so far.
    private boolean isInfoChanged() {
        if(!full_name.equals(edit_fullname.getText().toString())){
            reference.child(username).child("name").setValue(edit_fullname.getText().toString());
            return true;
        }
        if(!email.equals(edit_email.getText().toString())){
            reference.child(username).child("emial").setValue(edit_email.getText().toString());
            return true;
        }
        if(!phone.equals(edit_phone.getText().toString())) {
            reference.child(username).child("phoneNo").setValue(edit_phone.getText().toString());
            return true;
        }
        if(!password.equals(edit_password.getText().toString())) {
            reference.child(username).child("password").setValue(edit_password.getText().toString());
            return true;
        }
        else{
            return false;
        }

    }
}