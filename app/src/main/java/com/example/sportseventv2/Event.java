package com.example.sportseventv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Event extends TopMenu {

    private static final String TAG = "Event";
    Button tilmeld_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Log.d(TAG, "onCreate: started");

        getIncomingIntent();
    }

    /**
     * Checker om der bliver sendt intents
     */
    private void getIncomingIntent(){
        if (getIntent().hasExtra("image_event")&&getIntent().hasExtra("title_event")&&getIntent().hasExtra("descbription_event")){
            String imageUrl = getIntent().getStringExtra("image");
            String title = getIntent().getStringExtra("title_event");
            String description = getIntent().getStringExtra("descbription_event");

            setIntent(imageUrl,title,description);
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
}