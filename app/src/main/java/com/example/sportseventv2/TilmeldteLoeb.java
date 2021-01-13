package com.example.sportseventv2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportseventv2.model.EventAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TilmeldteLoeb extends TopBundMenu {

    private static final String TAG = "TilmeldteLøb.";
    TextView fullName, username;
    String imageUrl,eTitle,description;
    TextView eventTitle, eventDescription;
    ImageView eventImage;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilmeldte_loeb);

        //TODO Der skal hentes navn + brugernavn hertil
        fullName = findViewById(R.id.full_name2);
        username = findViewById(R.id.user_name2);

        eventTitle = findViewById(R.id.eventTitle3);
        eventDescription = findViewById(R.id.eventText3);
        eventImage = findViewById(R.id.eventImage3);

        showNavProfil();
        getEventFromDB();
        getUserData();
    }

    private void getEventFromDB(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("events");
        // Attach a listener to read the data at our posts reference
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    imageUrl = dataSnapshot.child("imgUrl").getValue(String.class);
                    eTitle = dataSnapshot.child("title").getValue(String.class);
                    description = dataSnapshot.child("description").getValue(String.class);
                    eventDescription.setText(description);
                    eventTitle.setText(eTitle);
                    Picasso.get().load(imageUrl).into(eventImage);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /**
     * Henter bruger informationer.
     */
    private void getUserData(){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);//Bruger nøgle userInfo og henter privat.
        String full_name = sharedPreferences.getString("fullname","");//henter string med unik nøgle og sætter lig full_name
        String user_name = sharedPreferences.getString("username","");

        fullName.setText(full_name);
        username.setText(user_name);

    }

}