package com.example.tableproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataActivity extends AppCompatActivity {

    public static String stat;

    private TextView testText;
    private RecyclerView recView;
    private Intent mainIntent;
    private RecyclerView.Adapter rva;
    private RecyclerView.LayoutManager layoutManager;

    private List<Pair<String,String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        //View Setup
        testText = findViewById(R.id.testText);
        recView = findViewById(R.id.recView);

        //Get intent
        mainIntent = getIntent();
        stat = mainIntent.getStringExtra("SPINNER_TEXT");
        testText.setText(stat);

        //string setup
        data = new ArrayList<>();


        try {
            data = new sheetThread().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //recycler view setup
        recView.setHasFixedSize(false);                          //helps performance, if context changes dont change size of data
        layoutManager = new LinearLayoutManager(this);  //linear layout manager
        recView.setLayoutManager(layoutManager);
        rva = new RVAdapter(data);
        recView.setAdapter(rva);
        recView.requestDisallowInterceptTouchEvent(true);
    }

    //accessing google sheets
    private List<Pair<String,String>> accessData() {

        final List<Pair<String,String>> ret = new ArrayList<>();

        //setup columns to search
        final String[] columns = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
                "U","V","W","X","Y","Z","AA"};

        //Connect to Sheet
        final HttpTransport transport = AndroidHttp.newCompatibleTransport();
        final JsonFactory factory = JacksonFactory.getDefaultInstance();
        final Sheets sheetsService = new Sheets.Builder(transport, factory, null)
                .setApplicationName("TableProject")
                .build();
        final String spreadsheetId = Config.spreadsheet_id;



                try {
                    //finds the column were looking for
                    String foundColumn = "";
                    for(String column:columns) {
                        String s = "Characters!"+column+ "1:"+column+"2";
                        ValueRange result = sheetsService.spreadsheets().values()
                                .get(spreadsheetId, s)
                                .setKey(Config.google_api_key)
                                .execute();
                        List<List<Object>> values = result.getValues();
                        for (List row : values) {
                            if(row.get(0).equals(stat)) {
                                foundColumn = column;
                            }
                        }

                    }
                    if(foundColumn == "") System.out.println("Welp, something went wrong.");

                    System.out.println("found column: "+foundColumn);


                    //We know the stat we want is in foundColumn, and the names are in A. Assemble the pair.
                    String range1 = "Characters!A2:A100";
                    String range2 = "Characters!" + foundColumn + "2:" + foundColumn + "100";
                    ValueRange result1 = sheetsService.spreadsheets().values()
                            .get(spreadsheetId, range1)
                            .setKey(Config.google_api_key)
                            .execute();

                    ValueRange result2 = sheetsService.spreadsheets().values()
                            .get(spreadsheetId, range2)
                            .setKey(Config.google_api_key)
                            .execute();



                    Iterator<List<Object>> it1 = result1.getValues().iterator();
                    Iterator<List<Object>> it2 = result2.getValues().iterator();

                    while(it1.hasNext() && it2.hasNext()) {
                        ret.add(new Pair<>(it1.next().get(0).toString(), it2.next().get(0).toString()));
                        System.out.println("Checkpoint");
                    }
                }
                catch (IOException e) {
                    System.out.println("Sheets failed" + e.getLocalizedMessage());

                }


        System.out.println("Ret:\n  " +ret);
        return ret;
    }

    private class sheetThread extends AsyncTask<Void,Void,List<Pair<String,String>>> {

        @Override
        protected List<Pair<String, String>> doInBackground(Void... voids) {
            final List<Pair<String,String>> ret = new ArrayList<>();

            //setup columns to search
            final String[] columns = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T",
                    "U","V","W","X","Y","Z","AA"};

            //Connect to Sheet
            final HttpTransport transport = AndroidHttp.newCompatibleTransport();
            final JsonFactory factory = JacksonFactory.getDefaultInstance();
            final Sheets sheetsService = new Sheets.Builder(transport, factory, null)
                    .setApplicationName("TableProject")
                    .build();
            final String spreadsheetId = Config.spreadsheet_id;



            try {
                //finds the column were looking for
                String foundColumn = "";
                for(String column:columns) {
                    String s = "Characters!"+column+ "1:"+column+"2";
                    ValueRange result = sheetsService.spreadsheets().values()
                            .get(spreadsheetId, s)
                            .setKey(Config.google_api_key)
                            .execute();
                    List<List<Object>> values = result.getValues();
                    for (List row : values) {
                        if(row.get(0).equals(stat)) {
                            foundColumn = column;
                        }
                    }

                }
                if(foundColumn == "") System.out.println("Welp, something went wrong.");



                //We know the stat we want is in foundColumn, and the names are in A. Assemble the pair.
                String range1 = "Characters!A2:A100";
                String range2 = "Characters!" + foundColumn + "2:" + foundColumn + "100";
                ValueRange result1 = sheetsService.spreadsheets().values()
                        .get(spreadsheetId, range1)
                        .setKey(Config.google_api_key)
                        .execute();

                ValueRange result2 = sheetsService.spreadsheets().values()
                        .get(spreadsheetId, range2)
                        .setKey(Config.google_api_key)
                        .execute();



                Iterator<List<Object>> it1 = result1.getValues().iterator();
                Iterator<List<Object>> it2 = result2.getValues().iterator();

                while(it1.hasNext() && it2.hasNext()) {
                    ret.add(new Pair<>(it1.next().get(0).toString(), it2.next().get(0).toString()));
                }
            }
            catch (IOException e) {
                System.out.println("Sheets failed" + e.getLocalizedMessage());

            }


            return ret;
        }

        protected void onPostExecute(List<Pair<String,String>> result) {
            data = result;
        }

    }
}
