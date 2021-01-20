package com.example.sportseventv2;

import androidx.annotation.NonNull;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.sportseventv2.model.MapboxMain;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Løb extends TopBundMenu {
    private static final String TAG = "løb";
    String startlong;
    String startlad;
    String slutlong;
    String slutlad;
    String check1long;
    String check1lad;
    String check2long;
    String check2lad;
    String check3long;
    String check3lad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loeb);
        getCoordinates();
        showNavLoeb();
        openMapboxMain();
    }

    private void openMapboxMain() {
        Intent intent = new Intent(this, MapboxMain.class);
        intent.putExtra("startlong",startlong);
        intent.putExtra("slutlong",slutlong);
        intent.putExtra("startlad",startlad);
        intent.putExtra("slutlad",slutlad);
        intent.putExtra("check1long",check1long);
        intent.putExtra("check2long",check2long);
        intent.putExtra("check3long",check3long);
        intent.putExtra("check1lad",check1lad);
        intent.putExtra("check2lad",check2lad);
        intent.putExtra("check3lad",check3lad);
        startActivity(intent);
    }

    private void getCoordinates(){
        Log.d(TAG, "getIncomingIntent: checker om der event info(billede, titel, beskrivelse");
        if (getIntent().hasExtra("startLat")&&getIntent().hasExtra("startLng")
                &&getIntent().hasExtra("via1Lat")&&getIntent().hasExtra("via1Lng")&&getIntent().hasExtra("via2Lat")&&getIntent().hasExtra("via2Lng")
                &&getIntent().hasExtra("via3Lat")&&getIntent().hasExtra("via3Lng")&&getIntent().hasExtra("endLat")&&getIntent().hasExtra("endLng")) {// spørger om der er extra i intent.

            startlad = getIntent().getStringExtra("startLat");
            startlong = getIntent().getStringExtra("startLng");
            check1lad = getIntent().getStringExtra("via1Lat");
            check1long = getIntent().getStringExtra("via1Lng");
            check2lad = getIntent().getStringExtra("via2Lat");
            check2long = getIntent().getStringExtra("via2Lng");
            check3lad = getIntent().getStringExtra("via3Lat");
            check3long = getIntent().getStringExtra("via3Lng");
            slutlad = getIntent().getStringExtra("endLat");
            slutlong = getIntent().getStringExtra("endLng");
        }
    }
}