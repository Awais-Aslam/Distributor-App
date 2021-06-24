package com.spikotech.sndapp.distributorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Start_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);
    }

    public void loginPage(View view) {
        Intent intent  = new Intent(Start_Activity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        Intent intent  = new Intent(Start_Activity.this, SignUp_Activity.class);
        startActivity(intent);
    }
}