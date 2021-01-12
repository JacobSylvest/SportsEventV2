package com.example.sportseventv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.sportseventv2.model.MapboxMain;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Løb extends TopMenu {
    Button button;
    Double startlong;
    Double startlad;
    Double slutlong;
    Double slutlad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loeb);

        startlong = 55.646371;
        startlad = 12.643210;
        slutlong = 55.563156;
        slutlad = 12.587285;

        button = findViewById(R.id.knap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapboxMain();
            }
        });

        //initialiserer og tilknytter/tildeler variabler

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //sætter Løb som hjemmeskærm:
        bottomNavigationView.setSelectedItemId(R.id.løb);

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
    public void openMapboxMain() {
        Intent intent = new Intent(this, MapboxMain.class);
        intent.putExtra("startlong",startlong);
        intent.putExtra("slutlong",slutlong);
        intent.putExtra("startlad",startlad);
        intent.putExtra("slutlad",slutlad);
        startActivity(intent);
    }
}