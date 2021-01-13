package com.example.sportseventv2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class TilmeldtEvent extends TopBundMenu {

    private static final String TAG = "Tilmeldt event" ;
    Button startLoeb_knap;
    TextView fullName, username;
    String imageUrl,eTitle,description,eventChild;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilmeldt_event);

        fullName = findViewById(R.id.full_name3);
        username = findViewById(R.id.user_name3);

        startLoeb_knap = findViewById(R.id.startLoeb_knap);
        startLoeb_knap.setOnClickListener((View.OnClickListener) this);
        getIncomingIntent();
        getUserData();
        showNavProfil();
    }

    /**
     * Checker om der bliver sendt intents & modtager hvis der kommer nogle.
     */
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checker om der event info(billede, titel, beskrivelse");
        if (getIntent().hasExtra("image_event")&&getIntent().hasExtra("title_event")
                &&getIntent().hasExtra("description_event")&&getIntent().hasExtra("event_Child")){// spørger om der er extra i intent.

            imageUrl = getIntent().getStringExtra("image_event");
            eTitle = getIntent().getStringExtra("title_event");
            description = getIntent().getStringExtra("description_event");
            eventChild = getIntent().getStringExtra("event_Child");
            setIntent(imageUrl,eTitle,description);
        }
    }

    /**
     * Metode der sætter info fra getIncomingIntent til activity_event.xml.
     * @param imageUrl
     * @param title
     * @param description
     */
    private void setIntent(String imageUrl,String title, String description){
        Log.d(TAG, "setIntent: Sender info til activity_event.xml");
        TextView event_title = findViewById(R.id.eventTitle3);
        event_title.setText(title);
        TextView event_description = findViewById(R.id.eventText3);
        event_description.setText(description);
        ImageView image = findViewById(R.id.eventImage3);
        Picasso.get().load(imageUrl).into(image);//bruger picasso til at downloade event billede
    }


    private void getUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);//Bruger nøgle userInfo og henter privat.
        String full_name = sharedPreferences.getString("fullname", "");//henter string med unik nøgle og sætter lig full_name
        user_name = sharedPreferences.getString("username", "");

        fullName.setText(full_name);//tilføjer full_name til UI.
        username.setText(user_name);
    }
}