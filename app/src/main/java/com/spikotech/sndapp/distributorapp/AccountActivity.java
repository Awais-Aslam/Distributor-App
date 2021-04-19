package com.spikotech.sndapp.distributorapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    Dialog mydialog, mydialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().setTitle("Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mydialog = new Dialog(this);
        mydialog1 = new Dialog(this);
    }

    public void Contactus(View v){
        TextView txtclose;
        Button btn1;
        mydialog.setContentView(R.layout.activity_pop_up_1);
        txtclose = (TextView) mydialog.findViewById(R.id.cross);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.dismiss();
            }
        });
        mydialog.show();
    }

    public void AboutApp(View v){
        TextView txtclose1;
        Button btn2;
        mydialog1.setContentView(R.layout.activity_pop_up_2);
        txtclose1 = (TextView) mydialog1.findViewById(R.id.cross1);
        txtclose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                mydialog1.dismiss();
            }
        });
        mydialog1.show();
    }
    public void refresh(View view){          //refresh is onClick name
        onRestart();
    }

    @Override
    protected void onRestart() {

        // TODO Auto-generated method stub
        super.onRestart();
        Intent i = new Intent(AccountActivity.this, AccountActivity.class);  //your class
        startActivity(i);
        finish();

    }
    public void logout(View v) {

        Intent it = new Intent(AccountActivity.this, LoginActivity.class);
        startActivity(it);

    }
}