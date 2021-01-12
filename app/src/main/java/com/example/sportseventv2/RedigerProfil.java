package com.example.sportseventv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

public class RedigerProfil extends AppCompatActivity {

    //variabler
    TextInputLayout editFullName, editEmail, editPhoneNo, editPassword;

    //TextInputLayout fullName, email, phoneNo, password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rediger_profil);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Hooks
        editFullName = findViewById(R.id.edit_full_name);
        editEmail = findViewById(R.id.edit_email);
        editPhoneNo = findViewById(R.id.edit_phoneNo);
        editPassword = findViewById(R.id.edit_password);

        showAllData();

        //sætter Løb som hjemmeskærm:

        bottomNavigationView.setSelectedItemId(R.id.profil);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.kalender:
                        startActivity(new Intent(getApplicationContext()
                                , Kalender.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.løb:
                        startActivity(new Intent(getApplicationContext()
                                , Løb.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext()
                        , Profil.class));
                        return true;

                }
                return false;
            }
        });

    }
 // det skal være "navnene fra login skærmen"
    private void showAllData() {
        Intent intent = getIntent();
        String edit_user_name= intent.getStringExtra("name");
        String edit_email= intent.getStringExtra("emial");
        String edit_phone_no= intent.getStringExtra("phoneNo");
        String edit_password= intent.getStringExtra("password");

        editFullName.getEditText().setText(edit_user_name);
        editEmail.getEditText().setText(edit_email);
        editPhoneNo.getEditText().setText(edit_phone_no);
        editPassword.getEditText().setText(edit_password);
    }
}