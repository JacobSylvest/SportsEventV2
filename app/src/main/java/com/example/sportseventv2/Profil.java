package com.example.sportseventv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profil extends TopBundMenu implements View.OnClickListener {


    private static final String TAG = "Profil.";
    private Button button, tilmeldte_btn;
    TextView fullName, username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        button = findViewById(R.id.rediger_profil);
        button.setOnClickListener(this);

        tilmeldte_btn = findViewById(R.id.tilmeldte_løb);
        tilmeldte_btn.setOnClickListener(this);

        fullName = findViewById(R.id.full_name);
        username = findViewById(R.id.user_name);
        showAllUserData();
        showNavProfil();
    }

    private void showAllUserData() {
        Log.d(TAG, "showAllUserData: started.");

        if (getIntent().hasExtra("name")&&getIntent().hasExtra("username")) {
            Intent intent = getIntent();
            setUserInfo(intent.getStringExtra("username"),intent.getStringExtra("name"));
            fullName.setText(getFullName());
            username.setText(getUserName());
        }

        System.out.println(getFullName());
    }

    public void openRedigerProfil(){
        Intent intent = new Intent(this, RedigerProfil.class);
        startActivity(intent);

    }
    public void openTilmeldteLoeb (){
        Intent intent = new Intent(this, TilmeldteLoeb.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        if(v == button){
            openRedigerProfil();
        }
        if (v == tilmeldte_btn){
            openTilmeldteLoeb();
        }
    }
}