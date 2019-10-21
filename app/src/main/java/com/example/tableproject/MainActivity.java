package com.example.tableproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;

import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    AssetManager am;
    InputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            am = getAssets();
            is = am.open("fesheet.json");
        }
        catch(Exception e) {
            System.out.println("IO ERROR!");
            System.exit(-1);
        }

    }
}
