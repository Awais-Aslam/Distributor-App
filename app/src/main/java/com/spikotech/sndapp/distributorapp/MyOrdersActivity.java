package com.spikotech.sndapp.distributorapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyOrdersActivity extends AppCompatActivity {

    private WebappApi webappApi;
    private RecyclerView orderListRecycler;
    private List<Order> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        getSupportActionBar().setTitle("My Orders");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        orderListRecycler = findViewById(R.id.order_list);

        webappApi = APIDistributor.getRetrofit().create(WebappApi.class);

        OrdersRunnable runnable = new OrdersRunnable();
        new Thread(runnable).start();

        FloatingActionButton fab = findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrdersActivity.this, AddOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    class OrdersRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<Order>> call = webappApi.getOrder();

            call.enqueue(new Callback<List<Order>>() {
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(MyOrdersActivity.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<Order> orders = response.body();

                    for (Order order: orders) {
                        String shopName = order.getShop();
                        String agentName = order.getAgent();
                        double totalAmount = order.getTotalAmount();
                        int profit = order.getTotalProfit();
                        String dateTime = order.getOrderDate();
                        String[] separated = dateTime.split("T");
                        String date = separated[0];
                        Order order1 = new Order(shopName, agentName, totalAmount, profit, date);
                        orderList.add(order1);
                    }

                    loadData();
                }

                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    Toast.makeText(MyOrdersActivity.this, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void loadData() {
        orderListRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        orderListRecycler.setHasFixedSize(true);
        orderListRecycler.setAdapter(new OrderAdapter(orderList));
    }
}