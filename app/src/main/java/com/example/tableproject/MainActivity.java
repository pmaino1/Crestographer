package com.example.tableproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //try RecyclerView!!

    Button searchButton;
    Spinner spinner;
    String spinnerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("yeryert");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = findViewById(R.id.searchButton);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String s = adapterView.getItemAtPosition(i).toString();
                    spinnerText = s;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
        }
        );
    }

    /*
    Function that takes parameters entered in this view and sends them to DataActivity to display.
    Button's onClick attribute is set to this function. Send information over with the intent's
    'Extra' functionality.
     */
    public void search(View view) {
        //toasts not working?
        Toast.makeText(this, "Loading from sheets...", Toast.LENGTH_LONG).show();

        //organize data to send over

        Intent dataIntent = new Intent(this, DataActivity.class);
        dataIntent.putExtra("SPINNER_TEXT",spinnerText);

        startActivity(dataIntent);
    }


}
