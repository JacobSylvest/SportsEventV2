package com.example.sportseventv2;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profil extends TopMenu implements View.OnClickListener {

    private static final String TAG = "Profil";
    TextView fullName, username;

    private Button redigerBtn, tilmeldte_loeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        redigerBtn = findViewById(R.id.rediger_profil);
        redigerBtn.setOnClickListener(this);

        tilmeldte_loeb = findViewById(R.id.tilmeldte_løb);
        tilmeldte_loeb.setOnClickListener(this);



        //initialiserer og tilknytter/tildeler variabler

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //sætter Løb som hjemmeskærm:

        bottomNavigationView.setSelectedItemId(R.id.profil);

        //Laver itemSelectedListener
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

                    case R.id.profil:
                        return true;

                }
                return false;
            }
        });

        //Hooks
        fullName = findViewById(R.id.full_name);
        username = findViewById(R.id.user_name);

        showAllUserData();

    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");

        fullName.setText(user_name);
        username.setText(user_username);
    }

    public void openRedigerProfil(){
        Log.d(TAG, "openRedigerProfil: started.");
        Intent intent = new Intent(this, RedigerProfil.class);
        startActivity(intent);
    }

    public void openTilmeldteLoeb(){
        Intent intent = new Intent(this, TilmeldteLoeb.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v==tilmeldte_loeb){
            openTilmeldteLoeb();
        }if (v == redigerBtn){
            openRedigerProfil();
        }
    }
}