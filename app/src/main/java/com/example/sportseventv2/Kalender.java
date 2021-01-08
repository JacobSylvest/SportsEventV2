package com.example.sportseventv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sportseventv2.model.EventAdapter;
import com.example.sportseventv2.model.EventModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Kalender extends AppCompatActivity {

    RecyclerView recyclerView;
    EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        recyclerView = findViewById(R.id.eventRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // laver recycleren i linearLayout

        eventAdapter = new EventAdapter(this,getMyList());
        recyclerView.setAdapter(eventAdapter);



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

    //TODO Denne Metode laver inhold til Enventlisten
    private ArrayList<EventModel> getMyList(){
        ArrayList<EventModel> eventModels = new ArrayList<>();

        EventModel m = new EventModel();
        m.setEventTitle("Mongol Ræs");
        m.setEventText("Et ræs for Jacob & Nikolaj");
        m.setImage(R.drawable.niko_jaco);
        eventModels.add(m);

        m = new EventModel();
        m.setEventTitle("Abe Ræs");
        m.setEventText("Et ræs for Jacob & Nikolaj");
        m.setImage(R.drawable.niko_jaco);
        eventModels.add(m);

        m = new EventModel();
        m.setEventTitle("Røv Ræs");
        m.setEventText("Et ræs for Jacob & Nikolaj");
        m.setImage(R.drawable.niko_jaco);
        eventModels.add(m);

        return eventModels;
    }
}