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
import java.util.Map;

public class TilmeldteLoeb extends TopBundMenu {

    private static final String TAG = "TilmeldteLøb.";
    TextView fullName, username;
    String event_imageUrl,event_title,event_description, user_name,event_child,event_startLAT,event_startLNG,event_via1LAT,event_via1LNG,event_via2LAT,event_via2LNG,event_via3LAT,event_via3LNG,event_endLAT,event_endLNG;
    List<String> titles,descriptions,imageUrl,eventChild,startLAT,startLNG,via1LAT,via1LNG,via2LAT,via2LNG,via3LAT,via3LNG,endLAT,endLNG;
    ArrayList<String> eventChilds = new ArrayList<>();
    /*ArrayList<String> startLATs = new ArrayList<>();
    ArrayList<String> startLNGs = new ArrayList<>();
    ArrayList<String> via1LATs = new ArrayList<>();
    ArrayList<String> via1LNGs = new ArrayList<>();
    ArrayList<String> via2LATs = new ArrayList<>();
    ArrayList<String> via2LNGs = new ArrayList<>();
    ArrayList<String> via3LATs = new ArrayList<>();
    ArrayList<String> via3LNGs = new ArrayList<>();
    ArrayList<String> endLATs = new ArrayList<>();
    ArrayList<String> endLNGs = new ArrayList<>();*/


    RecyclerView recyclerView;
    EventAdapter eventAdapter;
    FirebaseDatabase rootNode;
    DatabaseReference reference,reference1,reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilmeldte_loeb);

        fullName = findViewById(R.id.full_name2);
        username = findViewById(R.id.user_name2);

        titles = new ArrayList<>();
        descriptions = new ArrayList<>();
        imageUrl = new ArrayList<>();
        eventChild = new ArrayList<>();
        startLAT = new ArrayList<>();
        startLNG = new ArrayList<>();
        via1LAT = new ArrayList<>();
        via1LNG = new ArrayList<>();
        via2LAT = new ArrayList<>();
        via2LNG = new ArrayList<>();
        via3LAT = new ArrayList<>();
        via3LNG = new ArrayList<>();
        endLAT = new ArrayList<>();
        endLNG = new ArrayList<>();

        showNavProfil();
        getUserData();
    }

    /**
     * Henter events fra databasen.
     */
    private void getEventFromDB(){
        Log.d(TAG, "getEventFromDB: started.");
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");//Stien til databasen.
        reference1 = reference.child(user_name);
        reference2 = reference1.child("events");

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                            collectEventChild((Map<String, Object>) dataSnapshot.getValue());//virker kun når der er events i databasen
                        for (int i = 0; i < eventChilds.size(); i++) {// Looper gennem alle eventChilds.
                             event_imageUrl = dataSnapshot.child(eventChilds.get(i)).child("imgUrl").getValue(String.class);//Henter Billede info/String fra database.
                             event_title = dataSnapshot.child(eventChilds.get(i)).child("eTitle").getValue(String.class);
                             event_description = dataSnapshot.child(eventChilds.get(i)).child("description").getValue(String.class);
                             event_child = dataSnapshot.child(eventChilds.get(i)).child("eventChild").getValue(String.class);
                             event_startLAT = dataSnapshot.child(eventChilds.get(i)).child("startLAT").getValue(String.class);
                             event_startLNG = dataSnapshot.child(eventChilds.get(i)).child("startLNG").getValue(String.class);
                             event_via1LAT = dataSnapshot.child(eventChilds.get(i)).child("via1LAT").getValue(String.class);
                             event_via1LNG = dataSnapshot.child(eventChilds.get(i)).child("via1LNG").getValue(String.class);
                             event_via2LAT = dataSnapshot.child(eventChilds.get(i)).child("via2LAT").getValue(String.class);
                             event_via2LNG = dataSnapshot.child(eventChilds.get(i)).child("via2LNG").getValue(String.class);
                             event_via3LAT = dataSnapshot.child(eventChilds.get(i)).child("via3LAT").getValue(String.class);
                             event_via3LNG = dataSnapshot.child(eventChilds.get(i)).child("via3LNG").getValue(String.class);
                             event_endLAT = dataSnapshot.child(eventChilds.get(i)).child("endLAT").getValue(String.class);
                             event_endLNG = dataSnapshot.child(eventChilds.get(i)).child("endLNG").getValue(String.class);


                             titles.add(event_title);//tilføjer event titel til array
                             descriptions.add(event_description);
                             imageUrl.add(event_imageUrl);
                             eventChild.add(event_child);
                             startLAT.add(event_startLAT);
                             startLNG.add(event_startLNG);
                             via1LAT.add(event_via1LAT);
                             via1LNG.add(event_via1LNG);
                             via2LAT.add(event_via2LAT);
                             via2LNG.add(event_via2LNG);
                             via3LAT.add(event_via3LAT);
                             via3LNG.add(event_via3LNG);
                             endLAT.add(event_endLAT);
                             endLNG.add(event_endLNG);
                        }
                         showData();
                    }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {//i tilfælde af errors.
            }
        });
    }

    /**
     * Henter alle eventChilds fra databasen.
     * @param users
     */
    private void collectEventChild(Map<String,Object> users) {
        for (Map.Entry<String, Object> entry : users.entrySet()) {// itererer gennem alle events og ignorerer deres UID.
            Map userEvents = (Map) entry.getValue();//Henter map.

            eventChilds.add((String) userEvents.get("eventChild"));//Henter eventChilds til map.
        }

        Log.d(TAG, "collectEventChild: "+ eventChilds.toString());
    }

    /**
     * Henter bruger informationer.
     */
    private void getUserData(){
        Log.d(TAG, "getUserData: started.");
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);//Bruger nøgle userInfo og henter privat.
        String full_name = sharedPreferences.getString("fullname","");//henter string med unik nøgle og sætter lig full_name
        user_name = sharedPreferences.getString("username","");

        fullName.setText(full_name);//tilføjer full_name til UI.
        username.setText(user_name);
        getEventFromDB();
    }

    /**
     * Ligger event-Titler, billeder, beskrivelser og Childs ind i recyclerView gennem eventAdapter.
     */
    private void showData(){
        Log.d(TAG, "showData: started.");
        recyclerView = findViewById(R.id.tilmeldtRecycler);
        eventAdapter = new EventAdapter(this, titles, descriptions, imageUrl,eventChild,startLAT,startLNG,via1LAT,via1LNG,via2LAT,via2LNG,via3LAT,via3LNG,endLAT,endLNG);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // laver recycleren i linearLayout
        recyclerView.setAdapter(eventAdapter);
    }

}