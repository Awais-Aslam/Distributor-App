package com.spikotech.sndapp.distributorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopActivity extends AppCompatActivity {
    private TextView textViewShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        getSupportActionBar().setTitle("Shops");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewShop = findViewById(R.id.tv_shop);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://sndwebapi.spikotech.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebappApi webappApi = retrofit.create(WebappApi.class);

        Call<List<Shop>> call = webappApi.getShops();

        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                if (!response.isSuccessful()) {
                    textViewShop.setText("Code: "+response.code());
                    return;
                }

                List<Shop> shops = response.body();

                for (Shop shop: shops) {
                    String content = "";
                    content += "Shop Id : " + shop.getShopId() + "\n";
                    content += "Shop Name : " + shop.getShopName() + "\n";
                    content += "Shop Cnic : " + shop.getShopCnic() + "\n";
                    content += "Shop Phone : " + shop.getShopPhone() + "\n";
                    content += "City : " + shop.getCityName() + "\n";
                    content += "Area : " + shop.getSubCity() + "\n";

                    textViewShop.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                textViewShop.setText(t.getMessage());
            }
        });

    }
}