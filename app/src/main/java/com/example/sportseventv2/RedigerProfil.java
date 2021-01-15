package com.example.sportseventv2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RedigerProfil extends TopBundMenu {

    EditText edit_fullname, edit_password, edit_email, edit_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rediger_profil);
        showNavProfil();

        edit_fullname = findViewById(R.id.full_name_edit);
        edit_email = findViewById(R.id.email_edit);
        edit_phone = findViewById(R.id.phone_edit);
        edit_password = findViewById(R.id.password_edit);

        getUserData();
    }

    /**
     * Henter bruger informationer.
     */
    private void getUserData(){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);//Bruger nøgle userInfo og henter privat.
        String full_name = sharedPreferences.getString("fullname","");//henter string med unik nøgle og sætter lig full_name
        String email = sharedPreferences.getString("email","");
        String phone = sharedPreferences.getString("phoneNo","");
        String password = sharedPreferences.getString("password","");

        edit_fullname.setText(full_name);
        edit_email.setText(email);
        edit_phone.setText(phone);
        edit_password.setText(password);
    }
}