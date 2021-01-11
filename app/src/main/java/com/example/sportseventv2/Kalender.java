package com.example.sportseventv2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sportseventv2.model.EventAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class Kalender extends TopBundMenu {

    RecyclerView recyclerView;
    EventAdapter eventAdapter;
    AsyncHttpClient client;
    Workbook workbook;
    List<String> titles,descriptions,imageUrl;
    String url = "https://github.com/NikolajMorgen/skedehans/blob/main/file.xls?raw=true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        titles = new ArrayList<>();
        descriptions = new ArrayList<>();
        imageUrl = new ArrayList<>();
        readFromExcel();
        showNavKalender();
    }

    private void showData(){
        recyclerView = findViewById(R.id.eventRecycler);
        eventAdapter = new EventAdapter(this, titles, descriptions, imageUrl);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // laver recycleren i linearLayout
        recyclerView.setAdapter(eventAdapter);
    }
    private void readFromExcel(){
        client = new AsyncHttpClient();
        client.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(Kalender.this, "Download Failed.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                if(file != null) {
                    try {
                        workbook = workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        for(int i = 0 ; i < sheet.getRows();i++){

                            Cell[] row = sheet.getRow(i);
                            titles.add(row[0].getContents());
                            descriptions.add(row[1].getContents());
                            imageUrl.add(row[2].getContents());

                        }
                        showData();
                        Log.d("TAG", "onSuccess: "+ titles);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}