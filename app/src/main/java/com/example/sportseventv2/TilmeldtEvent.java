package com.example.sportseventv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportseventv2.model.MapboxMain;
import com.squareup.picasso.Picasso;

public class TilmeldtEvent extends TopBundMenu implements View.OnClickListener{

    private static final String TAG = "Tilmeldt event" ;
    Button startLoeb_knap;
    TextView fullName, username;
    String imageUrl,eTitle,description,eventChild,startLAT,startLNG,via1LAT,via1LNG,via2LAT,via2LNG,via3LAT,via3LNG,endLAT,endLNG;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilmeldt_event);

        fullName = findViewById(R.id.full_name3);
        username = findViewById(R.id.user_name3);

        startLoeb_knap = findViewById(R.id.startLoeb_knap);
        startLoeb_knap.setOnClickListener(this);

        showNavProfil();
        getUserData();
        getIncomingIntent();
    }

    /**
     * Checker om der bliver sendt intents & modtager hvis der kommer nogle.
     */
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checker om der event info(billede, titel, beskrivelse");
        if (getIntent().hasExtra("image_event1")&&getIntent().hasExtra("title_event1")
                &&getIntent().hasExtra("description_event1")&&getIntent().hasExtra("event_Child1")&&getIntent().hasExtra("event_startLat")&&getIntent().hasExtra("event_startLNG1")
                &&getIntent().hasExtra("event_via1LAT1")&&getIntent().hasExtra("event_via1LNG1")&&getIntent().hasExtra("event_via2LAT1")&&getIntent().hasExtra("event_via2LNG1")
                &&getIntent().hasExtra("event_via3LAT1")&&getIntent().hasExtra("event_via3LNG1")&&getIntent().hasExtra("event_endLAT1")&&getIntent().hasExtra("event_endLNG1")){// spørger om der er extra i intent.

            imageUrl = getIntent().getStringExtra("image_event1");
            eTitle = getIntent().getStringExtra("title_event1");
            description = getIntent().getStringExtra("description_event1");
            eventChild = getIntent().getStringExtra("event_Child1");
            startLAT = getIntent().getStringExtra("event_startLat");
            startLNG = getIntent().getStringExtra("event_startLNG1");
            via1LAT = getIntent().getStringExtra("event_via1LAT1");
            via1LNG = getIntent().getStringExtra("event_via1LNG1");
            via2LAT = getIntent().getStringExtra("event_via2LAT1");
            via2LNG = getIntent().getStringExtra("event_via2LNG1");
            via3LAT = getIntent().getStringExtra("event_via3LAT1");
            via3LNG = getIntent().getStringExtra("event_via3LNG1");
            endLAT = getIntent().getStringExtra("event_endLAT1");
            endLNG = getIntent().getStringExtra("event_endLNG1");
            setIntent(imageUrl,eTitle,description);
        }
    }

    private void setCoordinates() {
        Intent intent = new Intent(this, Løb.class);
        intent.putExtra("startLat", startLAT);
        intent.putExtra("startLng", startLNG);
        intent.putExtra("via1Lat", via1LAT);
        intent.putExtra("via1Lng", via1LNG);
        intent.putExtra("via2Lat", via2LAT);
        intent.putExtra("via2Lng", via2LNG);
        intent.putExtra("via3Lat", via3LAT);
        intent.putExtra("via3Lng", via3LNG);
        intent.putExtra("endLat", endLAT);
        intent.putExtra("endLng", endLNG);
        startActivity(intent);
    }

    /**
     * Metode der sætter info fra getIncomingIntent til activity_event.xml.
     * @param imageUrl
     * @param title
     * @param description
     */
    private void setIntent(String imageUrl,String title, String description){
        Log.d(TAG, "setIntent: Sender info til activity_tilmeldt_event.xml");
        TextView event_title = findViewById(R.id.eventTitle3);
        event_title.setText(title);
        TextView event_description = findViewById(R.id.eventText3);
        event_description.setText(description);
        ImageView image = findViewById(R.id.eventImage3);
        Picasso.get().load(imageUrl).into(image);//bruger picasso til at downloade event billede
    }

    /**
     * Henter bruger info der er gemt lokalt.
     */
    private void getUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);//Bruger nøgle userInfo og henter privat.
        String full_name = sharedPreferences.getString("fullname", "");//henter string med unik nøgle og sætter lig full_name
        user_name = sharedPreferences.getString("username", "");

        fullName.setText(full_name);//tilføjer full_name til UI.
        username.setText(user_name);
    }

    @Override
    public void onClick(View v) {
        if(v == startLoeb_knap){
            setCoordinates();
        }
    }
}