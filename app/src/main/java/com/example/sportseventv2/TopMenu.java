package com.example.sportseventv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TopMenu extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this,
                        "Har du brug for hjælp, så ring til en ven eller besøg vores hjemmeside", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Intent intentHelp = new Intent(this, TopHelp.class);
                startActivity(intentHelp);
                return true;
            case R.id.item3:
                Intent intentKontakt = new Intent(this, TopKontakt.class);
                startActivity(intentKontakt);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
