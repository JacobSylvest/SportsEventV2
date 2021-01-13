package com.example.sportseventv2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TilmeldtEvent extends TopBundMenu {

    Button startLoeb_knap;
    TextView fullName, username;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilmeldt_event);

        fullName = findViewById(R.id.full_name2);
        username = findViewById(R.id.user_name2);

        startLoeb_knap = findViewById(R.id.startLoeb_knap);
        startLoeb_knap.setOnClickListener((View.OnClickListener) this);

        getUserData();
    }

    private void getUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);//Bruger nøgle userInfo og henter privat.
        String full_name = sharedPreferences.getString("fullname", "");//henter string med unik nøgle og sætter lig full_name
        user_name = sharedPreferences.getString("username", "");

        fullName.setText(full_name);//tilføjer full_name til UI.
        username.setText(user_name);
    }
}