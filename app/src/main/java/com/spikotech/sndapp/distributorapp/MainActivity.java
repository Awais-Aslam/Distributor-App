package com.spikotech.sndapp.distributorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    RecyclerView recyclecardview, list_recycler;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mTitles = new ArrayList<>();

    private ArrayList<String> mShopNames = new ArrayList<>();
    private ArrayList<String> mBrands = new ArrayList<>();
    private ArrayList<String> mPrices = new ArrayList<>();
    private ArrayList<String> mTime = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclecardview = (RecyclerView) findViewById(R.id.recyclecardview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclecardview.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mTitles);
        recyclecardview.setAdapter(adapter);

        mNames.add("$3450");
        mNames.add("$8740");
        mNames.add("$8930");


        mTitles.add("Today's Sales");
        mTitles.add("Monthly Sales");
        mTitles.add("Yearly Sales");


        list_recycler = (RecyclerView) findViewById(R.id.list_recycler);
        LinearLayoutManager list = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list_recycler.setLayoutManager(list);
        ListAdapter adapter1 = new ListAdapter(this, mShopNames, mBrands, mPrices, mTime);
        list_recycler.setAdapter(adapter1);

        mShopNames.add("Ali store");
        mShopNames.add("Raza store");
        mShopNames.add("Lhe store");
        mShopNames.add("Loqa store");
        mShopNames.add("Ali store");
        mShopNames.add("Raza store");
        mShopNames.add("Lhe store");
        mShopNames.add("Loqa store");

        mBrands.add("Amazon");
        mBrands.add("Apple");
        mBrands.add("Samsung");
        mBrands.add("Vivo");
        mBrands.add("Amazon");
        mBrands.add("Apple");
        mBrands.add("Samsung");
        mBrands.add("Vivo");

        mPrices.add("$345");
        mPrices.add("$3456");
        mPrices.add("$3745");
        mPrices.add("$3945");
        mPrices.add("$345");
        mPrices.add("$3456");
        mPrices.add("$3745");
        mPrices.add("$3945");

        mTime.add("5m ago");
        mTime.add("9m ago");
        mTime.add("15m ago");
        mTime.add("2m ago");
        mTime.add("5m ago");
        mTime.add("9m ago");
        mTime.add("15m ago");
        mTime.add("2m ago");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent home = new Intent(MainActivity.this, MainActivity.class);
                startActivity(home);
                break;
            case R.id.nav_route:
                Intent route = new Intent(MainActivity.this, RouteActivity.class);
                startActivity(route);
                break;
            case R.id.nav_order:
                Intent order = new Intent(MainActivity.this, MyOrdersActivity.class);
                startActivity(order);
                break;
            case R.id.nav_stock:
                Intent stock = new Intent(MainActivity.this, StockActivity.class);
                startActivity(stock);
                break;
            case R.id.nav_shop:
                Intent shop = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(shop);
                break;
            case R.id.nav_account:
                Intent account = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(account);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}