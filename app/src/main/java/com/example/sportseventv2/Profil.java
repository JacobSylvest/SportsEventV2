package com.example.sportseventv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Profil extends TopBundMenu implements View.OnClickListener {


    private static final String TAG = "Profil.";
    private Button button, tilmeldte_btn;
    TextView fullName, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        button = findViewById(R.id.rediger_profil);
        button.setOnClickListener(this);

        tilmeldte_btn = findViewById(R.id.tilmeldte_løb);
        tilmeldte_btn.setOnClickListener(this);


        showAllUserData();
        showNavProfil();
    }


    /**
     * Henter bruger informationer.
     */
    private void showAllUserData() {
        Log.d(TAG, "showAllUserData: started.");

        //Hooks
        fullName = findViewById(R.id.full_name);
        username = findViewById(R.id.user_name);

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);//Bruger nøgle userInfo og henter privat.
        String full_name = sharedPreferences.getString("fullname","");//henter string med unik nøgle og sætter lig full_name
        String user_name = sharedPreferences.getString("username","");

        fullName.setText(full_name);
        username.setText(user_name);
        Log.d(TAG, "Fullname: "+full_name);
        Log.d(TAG, "Username: "+user_name);
    }

    public void openRedigerProfil(){
        Intent intent = new Intent(this, RedigerProfil.class);
        startActivity(intent);

    }
    public void openTilmeldteLoeb (){
        Intent intent = new Intent(this, TilmeldteLoeb.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        if(v == button){
            openRedigerProfil();
        }
        if (v == tilmeldte_btn){
            openTilmeldteLoeb();
        }
    }
}