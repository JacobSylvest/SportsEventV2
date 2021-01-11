package com.example.sportseventv2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Event extends TopMenu {

    private static final String TAG = "Event";
    Button tilmeld_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Log.d(TAG, "onCreate: started");

        tilmeld_btn = findViewById(R.id.tilmeld_btn);
        tilmeld_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilmeldLoeb();
            }
        });

        getIncomingIntent();
    }

    /**
     * Checker om der bliver sendt intents & modtager hvis der kommer nogle
     */
    private void getIncomingIntent(){
        if (getIntent().hasExtra("image_event")&&getIntent().hasExtra("title_event")&&getIntent().hasExtra("description_event")){
            String imageUrl = getIntent().getStringExtra("image_event");
            String eTitle = getIntent().getStringExtra("title_event");
            String description = getIntent().getStringExtra("description_event");

            System.out.println("dette er blevet hentet: "+imageUrl);
            setIntent(imageUrl,eTitle,description);
        }
    }
    private void setIntent(String imageUrl,String title, String description){

        TextView event_title = findViewById(R.id.eventTitle2);
        event_title.setText(title);
        TextView event_description = findViewById(R.id.eventText2);
        event_description.setText(description);

        ImageView image = findViewById(R.id.eventImage2);
        //bruger picasso til at downloade event billede
        Picasso.get().load(imageUrl).into(image);
    }

    public void tilmeldLoeb(){
        //TODO skal tilføje/sende løb til Tilmeldte løb i minprofil
        Toast.makeText(getApplicationContext(),"Tilmeldt: "+getIntent().getStringExtra("title_event"), Toast.LENGTH_SHORT).show();
    }
}