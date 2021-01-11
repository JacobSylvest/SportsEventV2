package com.example.sportseventv2;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TopBundMenu extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this,
                        "Har du brug for hjælp, så ring til en ven eller besøg vores hjemmeside", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Intent intentHelp = new Intent(this, TopHelp.class);
                startActivity(intentHelp);
                return true;
            case R.id.item3:
                Intent intentKontakt = new Intent(this, TopKontakt.class);
                startActivity(intentKontakt);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    void showNavKalender() {
        //initialiserer og tilknytter/tildeler variabler
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //sætter kalender:
        bottomNavigationView.setSelectedItemId(R.id.kalender);

        //Laver itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
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

    void showNavLoeb(){
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
    void showNavProfil(){
        //initialiserer og tilknytter/tildeler variabler

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //sætter profil:
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
                        return true;

                    case R.id.profil:
                        return true;

                }
                return false;
            }
        });
    }
}
