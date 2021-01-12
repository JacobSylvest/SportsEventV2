package com.example.sportseventv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profil extends TopBundMenu implements View.OnClickListener {


    private Button button, tilmeldte_btn;

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

    TextView fullName, username;
    private void showAllUserData() {

        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");

        //Hooks
        fullName = findViewById(R.id.full_name);
        username = findViewById(R.id.user_name);

        fullName.setText(user_name);
        username.setText(user_username);
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