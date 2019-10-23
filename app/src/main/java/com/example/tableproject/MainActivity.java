package com.example.tableproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    //try RecyclerView!!

    AssetManager am;
    InputStream is;
    JSONObject fejson;
    TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testText = findViewById(R.id.testText);

        try {
            am = getAssets();
            is = am.open("fesheet.json");

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            System.out.println("its goin");

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            fejson = new JSONObject(responseStrBuilder.toString());

            testText.setText(fejson.getString("Name"));
        }
        catch(Exception e) {
            System.out.println("Json parsing ERROR!");
            System.exit(-1);
        }



    }
}
