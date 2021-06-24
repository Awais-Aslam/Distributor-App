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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private WebappApi webappApi;
    RecyclerView recyclerCardView;
    int todayCount = 0;
    int monthlyCount = 0;
    int yearlyCount = 0;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerCardView = findViewById(R.id.recyclecardview);
        webappApi = APIDistributor.getRetrofit().create(WebappApi.class);

        MainActivity.TodayRunnable runnableToday = new MainActivity.TodayRunnable();
        new Thread(runnableToday).start();
        MainActivity.MonthlyRunnable runnableMonth = new MainActivity.MonthlyRunnable();
        new Thread(runnableMonth).start();
        MainActivity.YearlyRunnable runnableYear = new MainActivity.YearlyRunnable();
        new Thread(runnableYear).start();

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

    class YearlyRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<Order>> call = webappApi.getOrder();

            call.enqueue(new Callback<List<Order>>() {
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<Order> orders = response.body();

                    for (Order order: orders) {
                        String fullDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        String[] separated = fullDate.split("-");
                        String currentYear = separated[0];
                        String date = order.getOrderDate();
                        String[] separate = date.split("-");
                        String year = separate[0];

                        int e = Integer.parseInt(year);
                        int f = Integer.parseInt(currentYear);
                        if (e == f) {
                            yearlyCount = yearlyCount + 1;
                        }
                    }
                    String countStr = String.valueOf(yearlyCount);
                    mNames.add(countStr);
                    mTitles.add("Yearly Orders");

                    loadData();
                }

                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class MonthlyRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<Order>> call = webappApi.getOrder();

            call.enqueue(new Callback<List<Order>>() {
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<Order> orders = response.body();

                    for (Order order: orders) {
                        String fullDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        String[] separated = fullDate.split("-");
                        String currentMonth = separated[1];
                        String currentYear = separated[0];
                        String date = order.getOrderDate();
                        String[] separate = date.split("-");
                        String month = separate[1];
                        String year = separate[0];

                        int c = Integer.parseInt(month);
                        int d = Integer.parseInt(currentMonth);
                        int e = Integer.parseInt(year);
                        int f = Integer.parseInt(currentYear);
                        if (c == d && e == f) {
                            monthlyCount = monthlyCount + 1;
                        }
                    }
                    String countStr = String.valueOf(monthlyCount);
                    mNames.add(countStr);
                    mTitles.add("Monthly Orders");
                    loadData();
                }

                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class TodayRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<Order>> call = webappApi.getOrder();

            call.enqueue(new Callback<List<Order>>() {
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<Order> orders = response.body();

                    for (Order order: orders) {
                        String fullDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        String[] separated = fullDate.split("-");
                        String currentDay = separated[2];
                        String currentMonth = separated[1];
                        String currentYear = separated[0];
                        String date = order.getOrderDate();
                        String[] separate = date.split("-");
                        String dayTime = separate[2];
                        String month = separate[1];
                        String year = separate[0];
                        String[] sep = dayTime.split("T");
                        String day = sep[0];
                        int a = Integer.parseInt(day);
                        int b = Integer.parseInt(currentDay);
                        int c = Integer.parseInt(month);
                        int d = Integer.parseInt(currentMonth);
                        int e = Integer.parseInt(year);
                        int f = Integer.parseInt(currentYear);
                        if (a == b && c == d && e == f) {
                            todayCount = todayCount + 1;
                        }
                    }
                    String countStr = String.valueOf(todayCount);
                    mNames.add(countStr);
                    //Toast.makeText(MainActivity.this, "Date: " + da, Toast.LENGTH_LONG).show();
                    mTitles.add("Today's Orders");
                    /*mTitles.add("Monthly Orders");
                    mTitles.add("Yearly Orders");*/

                    loadData();
                }

                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void loadData() {
        recyclerCardView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerCardView.setHasFixedSize(true);
        recyclerCardView.setAdapter(new RecyclerViewAdapter(this, mNames, mTitles));
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