package com.spikotech.sndapp.distributorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopActivity extends AppCompatActivity {
    private WebappApi webappApi;
    private List<Shop> shopList = new ArrayList<>();
    private RecyclerView shopRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        getSupportActionBar().setTitle("Shops");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        shopRecyclerView = findViewById(R.id.shop_list);


        FloatingActionButton fab = findViewById(R.id.fab_btn_shop);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, AddShopActivity.class);
                startActivity(intent);
            }
        });

        webappApi = APIDistributor.getRetrofit().create(WebappApi.class);

        ShopRunnable shopRunnable = new ShopRunnable();
        new Thread(shopRunnable).start();

    }

    class ShopRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<Shop>> call = webappApi.getShops();

            call.enqueue(new Callback<List<Shop>>() {
                @Override
                public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(ShopActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<Shop> shops = response.body();

                    for (Shop shop: shops) {
                        String shopName = shop.getShopName();
                        String shopPhone = shop.getShopPhone();
                        String cnic = shop.getShopCnic();
                        String city = shop.getCity();
                        String area = shop.getArea();

                        Shop shop1 = new Shop(shopName, cnic, shopPhone, city, area);
                        shopList.add(shop1);
                    }

                    loadData();
                }

                @Override
                public void onFailure(Call<List<Shop>> call, Throwable t) {
                    Toast.makeText(ShopActivity.this, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void loadData() {
        shopRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        shopRecyclerView.setHasFixedSize(true);
        shopRecyclerView.setAdapter(new ShopAdapter(shopList));
    }
}