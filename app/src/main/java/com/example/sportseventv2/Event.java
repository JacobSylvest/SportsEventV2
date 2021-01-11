package com.example.sportseventv2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Viser et Event og gør det muligt at tilmelde sig Eventet.
 */
public class Event extends TopBundMenu implements View.OnClickListener{

    private static final String TAG = "Event";
    Button tilmeld_btn;
    String imageUrl,eTitle,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Log.d(TAG, "onCreate: started");

        tilmeld_btn = findViewById(R.id.tilmeld_btn);
        tilmeld_btn.setOnClickListener(this);

        getIncomingIntent();
    }

    /**
     * Checker om der bliver sendt intents & modtager hvis der kommer nogle.
     */
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checker om der event info(billede, titel, beskrivelse");
        // spørger om der er
        if (getIntent().hasExtra("image_event")&&getIntent().hasExtra("title_event")&&getIntent().hasExtra("description_event")){

            imageUrl = getIntent().getStringExtra("image_event");
            eTitle = getIntent().getStringExtra("title_event");
            description = getIntent().getStringExtra("description_event");
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
        TextView event_title = findViewById(R.id.eventTitle2);
        event_title.setText(title);
        TextView event_description = findViewById(R.id.eventText2);
        event_description.setText(description);

        ImageView image = findViewById(R.id.eventImage2);
        //bruger picasso til at downloade event billede
        Picasso.get().load(imageUrl).into(image);
    }

    /**
     * Metode til tilmeld løb knappen.
     */
    private void tilmeldLoeb(){
        Log.d(TAG, "tilmeldLoeb: der er trykket på tilmeld løb.");
        //TODO skal tilføje/sende løb til Tilmeldte løb i minprofil
        Toast.makeText(getApplicationContext(),"Tilmeldt: "+getIntent().getStringExtra("title_event"), Toast.LENGTH_SHORT).show();
    }

    /**
     * Metode der styrer hvad der sker når der bliver trykket på noget.
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v == tilmeld_btn){
            tilmeldLoeb();
        }
    }
}