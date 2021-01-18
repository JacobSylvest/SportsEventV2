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

public class Løb extends TopBundMenu {
    Button button;
    Double startlong;
    Double startlad;
    Double slutlong;
    Double slutlad;
    Double check1long;
    Double check1lad;
    Double check2long;
    Double check2lad;
    Double check3long;
    Double check3lad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loeb);

        startlong = 12.44869;
        startlad = 55.73940;
        slutlong = 12.450843;
        slutlad = 55.746287;
        check1long = 12.396373;
        check1lad = 55.731104;
        check2long = 12.335487;
        check2lad = 55.755271;
        check3long = 12.378231;
        check3lad = 55.794664;


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
        intent.putExtra("check1long",check1long);
        intent.putExtra("check2long",check2long);
        intent.putExtra("check3long",check3long);
        intent.putExtra("check1lad",check1lad);
        intent.putExtra("check2lad",check2lad);
        intent.putExtra("check3lad",check3lad);
        startActivity(intent);
    }
}