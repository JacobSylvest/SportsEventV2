package com.example.sportseventv2;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class TopBundMenu extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

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
                        "Har du brug for hjælp, så kontakt os", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Intent intentHelp = new Intent(this, TopHelp.class);
                startActivity(intentHelp);
                return true;
            case R.id.item3:
                Intent intentKontakt = new Intent(this, TopKontakt.class);
                startActivity(intentKontakt);
                return true;
            case R.id.item4:
                Intent intentLogin = new Intent(this, Login.class);
                startActivity(intentLogin);
                return true;
           /* case R.id.item5:
                throw new RuntimeException("Test Crash");*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initBottom(){
        //initialiserer og tilknytter/tildeler variabel
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }
    void showNavKalender() {
        initBottom();
        //sætter kalender:
        bottomNavigationView.setSelectedItemId(R.id.kalender);
        bottomNav();
    }

    void showNavLoeb(){
        initBottom();
        //sætter Løb som hjemmeskærm:
        bottomNavigationView.setSelectedItemId(R.id.løb);
        bottomNav();
    }
    void showNavProfil(){
        initBottom();
        //sætter profil:
        bottomNavigationView.setSelectedItemId(R.id.profil);
        bottomNav();
    }

    private void bottomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.kalender:
                        startActivity(new Intent(getApplicationContext()
                                , Kalender.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.løb:
                        startActivity(new Intent(getApplicationContext()
                                , MapboxMain.class));
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
