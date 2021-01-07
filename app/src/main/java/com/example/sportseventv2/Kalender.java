package com.example.sportseventv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Kalender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        //initialiserer og tilknytter/tildeler variabler

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //sætter Løb som hjemmeskærm:

        bottomNavigationView.setSelectedItemId(R.id.kalender);

        //Laver itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.kalender:
                        return true;

                    case R.id.løb:
                        startActivity(new Intent(getApplicationContext()
                                , Løb.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext()
                                , Profil.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }
}