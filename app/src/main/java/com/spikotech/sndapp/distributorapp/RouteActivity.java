package com.spikotech.sndapp.distributorapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RouteActivity extends AppCompatActivity {

    EditText etSource, etDestination;
    Button btTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        getSupportActionBar().setTitle("Route");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //assign Variables
        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.bt_track);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET VALUE FROM EDIT TEXT
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                if(sSource.equals("") &&  sDestination.equals("")){
                    //when both are empty
                    Toast.makeText(getApplicationContext(), "Enter Both Locations", Toast.LENGTH_SHORT).show();

                }else
                {
                    //when both value filled
                    //Display Track
                    DisplayTrack(sSource, sDestination);
                }
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
        //redirect to google store if have not map installed

        try {
            //when it is istalled.
            //initialize Uri.

            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource + "/"
                    + sDestination);

            //Initialize Intent
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            //set package.
            intent.setPackage("com.google.android.apps.maps");

            //set Flag
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);

            //start Activity
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            //when google map in not installed
            //initilize uri.

            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");

            //Initialize Intent
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            //set Flag
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);

            //start Activity
            startActivity(intent);
        }
    }

}