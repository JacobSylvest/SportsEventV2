package com.example.sportseventv2;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
    List<String> titles,descriptions,imageUrl,eventChild,startLAT,startLNG,via1LAT,via1LNG,via2LAT,via2LNG,via3LAT,via3LNG,endLAT,endLNG;
    String url = "https://github.com/JacobSylvest/SportsEventV2/blob/master/file.xls?raw=true";// stien til Excel filen.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

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

        readFromExcel();
        showNavKalender();
    }

    /**
     * Ligger event-Titler, billeder, beskrivelser og Childs ind i recyclerView gennem eventAdapter.
     */
    private void showData(){
        recyclerView = findViewById(R.id.eventRecycler);
        eventAdapter = new EventAdapter(this, titles, descriptions, imageUrl, eventChild, startLAT, startLNG, via1LAT, via1LNG, via2LAT, via2LNG, via3LAT, via3LNG, endLAT, endLNG);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // laver recycleren i linearLayout
        recyclerView.setAdapter(eventAdapter);
    }

    /**
     * Henter Events, som er oprettet i excel filen.
     */
    private void readFromExcel() {
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
                if (file != null) {
                    try {
                        workbook = workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        for (int i = 1; i < sheet.getRows(); i++) {

                            Cell[] row = sheet.getRow(i);
                            titles.add(row[0].getContents());
                            descriptions.add(row[1].getContents());
                            imageUrl.add(row[2].getContents());
                            eventChild.add(row[3].getContents());
                            startLAT.add(row[4].getContents());
                            startLNG.add(row[5].getContents());
                            via1LAT.add(row[6].getContents());
                            via1LNG.add(row[7].getContents());
                            via2LAT.add(row[8].getContents());
                            via2LNG.add(row[9].getContents());
                            via3LAT.add(row[10].getContents());
                            via3LNG.add(row[11].getContents());
                            endLAT.add(row[12].getContents());
                            endLNG.add(row[13].getContents());
                        }

                        showData();
                        Log.d("TAG", "onSuccess: " + titles);
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