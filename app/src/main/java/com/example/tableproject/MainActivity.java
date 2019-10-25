package com.example.tableproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    //try RecyclerView!!

    Button searchButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("yeryert");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = findViewById(R.id.searchButton);
    }

    /*
    Function that takes parameters entered in this view and sends them to DataActivity to display.
    Button's onClick attribute is set to this function. Send information over with the intent's
    'Extra' functionality.
     */
    public void search(View view) {
        //organize data to send over

        Intent dataIntent = new Intent(this, DataActivity.class);
        // use 'dataIntent.putExtra(<key>,<data>)' to send info in the intent.
        startActivity(dataIntent);
    }

}
