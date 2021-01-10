package com.example.sportseventv2;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class TilmeldteLoeb extends TopMenu {

    TextView fullName, username;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilmeldte_loeb);

        //TODO Der skal hentes navn + brugernavn hertil
        fullName = findViewById(R.id.full_name2);
        username = findViewById(R.id.user_name2);

        listView = findViewById(R.id.tilmeldt_list);


    }
}