package com.example.sportseventv2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * Viser et Event og gør det muligt at tilmelde sig Eventet.
 */
public class Event extends TopBundMenu implements View.OnClickListener {

    private static final String TAG = "Event";
    Button tilmeld_btn;
    String imageUrl,eTitle,description,eventChild,startLAT,startLNG,via1LAT,via1LNG,via2LAT,via2LNG,via3LAT,via3LNG,endLAT,endLNG;

    FirebaseDatabase rootNode;
    DatabaseReference reference, reference1, reference2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Log.d(TAG, "onCreate: started");

        tilmeld_btn = findViewById(R.id.tilmeld_btn);
        tilmeld_btn.setOnClickListener(this);

        showNavKalender();
        getIncomingIntent();
    }

    /**
     * Checker om der bliver sendt intents & modtager hvis der kommer nogle.
     */
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checker om der event info(billede, titel, beskrivelse");
        if (getIntent().hasExtra("image_event")&&getIntent().hasExtra("title_event")
                &&getIntent().hasExtra("description_event")&&getIntent().hasExtra("event_Child")&&getIntent().hasExtra("event_startLAT")&&getIntent().hasExtra("event_startLNG")
                &&getIntent().hasExtra("event_via1LAT")&&getIntent().hasExtra("event_via1LNG")&&getIntent().hasExtra("event_via2LAT")&&getIntent().hasExtra("event_via2LNG")
                &&getIntent().hasExtra("event_via3LAT")&&getIntent().hasExtra("event_via3LNG")&&getIntent().hasExtra("event_endLAT")&&getIntent().hasExtra("event_endLNG")){// spørger om der er extra i intent.

            imageUrl = getIntent().getStringExtra("image_event");
            eTitle = getIntent().getStringExtra("title_event");
            description = getIntent().getStringExtra("description_event");
            eventChild = getIntent().getStringExtra("event_Child");
            startLAT = getIntent().getStringExtra("event_startLAT");
            startLNG = getIntent().getStringExtra("event_startLNG");
            via1LAT = getIntent().getStringExtra("event_via1LAT");
            via1LNG = getIntent().getStringExtra("event_via1LNG");
            via2LAT = getIntent().getStringExtra("event_via2LAT");
            via2LNG = getIntent().getStringExtra("event_via2LNG");
            via3LAT = getIntent().getStringExtra("event_via3LAT");
            via3LNG = getIntent().getStringExtra("event_via3LNG");
            endLAT = getIntent().getStringExtra("event_endLAT");
            endLNG = getIntent().getStringExtra("event_endLNG");


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
        Picasso.get().load(imageUrl).into(image);//bruger picasso til at downloade event billede
    }

    /**
     * Metode til tilmeld løb knappen.
     * Tilføjer Event til databasen.
     */
    private void tilmeldLoeb(){
        Log.d(TAG, "tilmeldLoeb: der er trykket på tilmeld løb.");
        Toast.makeText(getApplicationContext(),"Tilmeldt: "+getIntent().getStringExtra("title_event"), Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);//Bruger nøgle userInfo og henter privat.
        String user_name = sharedPreferences.getString("username","");

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        reference1 = reference.child(user_name);
        reference2 = reference1.child("events");

        EventHelperClass ehelperClass = new EventHelperClass(eTitle, description, imageUrl,eventChild,startLAT,startLNG,via1LAT,via1LNG,via2LAT,via2LNG,via3LAT,via3LNG,endLAT,endLNG);

        reference2.child(eventChild).setValue(ehelperClass);// OBS Child må IKKE indeholde tegn eller tal - Da nedenstående linje ikke virker.
        reference2.child("ingen").removeValue();//sletter ingen tilmeldte løb fra databasen.
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