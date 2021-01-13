package com.example.sportseventv2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportseventv2.model.EventAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TilmeldteLoeb extends TopBundMenu {

    private static final String TAG = "TilmeldteLøb.";
    TextView fullName, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilmeldte_loeb);

        //TODO Der skal hentes navn + brugernavn hertil
        fullName = findViewById(R.id.full_name2);
        username = findViewById(R.id.user_name2);

        showNavProfil();
    }

}