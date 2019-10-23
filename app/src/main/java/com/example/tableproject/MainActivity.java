package com.example.tableproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.List;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Value;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //try RecyclerView!!

    TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testText = findViewById(R.id.testText);

        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory factory = JacksonFactory.getDefaultInstance();
        final Sheets sheetsService = new Sheets.Builder(transport, factory, null)
                .setApplicationName("TableProject")
                .build();
        final String spreadsheetId = Config.spreadsheet_id;


        new Thread() {
            @Override
            public void run() {
                try {
                    String range = "Characters!A1:B2";
                    ValueRange result = sheetsService.spreadsheets().values()
                            .get(spreadsheetId, range)
                            .setKey(Config.google_api_key)
                            .execute();
                    int numRows = result.getValues() != null ? result.getValues().size() : 0;
                    System.out.println("SUCCESS.\n"+ "rows retrived " + numRows);


                    for(List row:result.getValues()) {
                        System.out.println(row.get(0));
                    }
                }
                catch (IOException e) {
                    System.out.println("Sheets failed" + e.getLocalizedMessage());
                }
            }
        }.start();
    }
}
