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
    String event_imageUrl,event_title,event_description;

    RecyclerView recyclerView;
    EventAdapter eventAdapter;
    List<String> titles,descriptions,imageUrl,eventChild;
    ArrayList<String> eventChilds = new ArrayList<>();

    FirebaseDatabase rootNode;
    DatabaseReference reference;
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


        showNavProfil();
        getEventFromDB();
        getUserData();
    }

    /**
     * Henter events fra databasen.
     */
    private void getEventFromDB(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("events");//Stien til databasen.

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    collectEventChild((Map<String,Object>) dataSnapshot.getValue());

                    for (int i = 0; i < eventChilds.size() ; i++) {// Looper gennem alle eventChilds.
                        event_imageUrl = dataSnapshot.child(eventChilds.get(i)).child("imgUrl").getValue(String.class);//Henter Billede info/String fra database.
                        event_title = dataSnapshot.child(eventChilds.get(i)).child("eTitle").getValue(String.class);
                        event_description = dataSnapshot.child(eventChilds.get(i)).child("description").getValue(String.class);

                        titles.add(event_title);//tilføjer event titel til UI.
                        descriptions.add(event_description);
                        imageUrl.add(event_imageUrl);
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
        for (Map.Entry<String, Object> entry : users.entrySet()){// itererer gennem alle events og ignorerer deres UID.
            Map singleUser = (Map) entry.getValue();//Henter map.
            eventChilds.add((String) singleUser.get("eventChild"));//Henter eventChilds  til map.
        }
        Log.d(TAG, "collectEventChild: "+eventChilds.toString());
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

    /**
     * Ligger event-Titler, billeder, beskrivelser og Childs ind i recyclerView gennem eventAdapter.
     */
    private void showData(){
        recyclerView = findViewById(R.id.tilmeldtRecycler);
        eventAdapter = new EventAdapter(this, titles, descriptions, imageUrl,eventChild);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // laver recycleren i linearLayout
        recyclerView.setAdapter(eventAdapter);
    }

}