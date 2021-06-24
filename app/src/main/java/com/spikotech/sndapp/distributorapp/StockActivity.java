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

public class StockActivity extends AppCompatActivity {
    private TextView textViewStock;
    private WebappApi webappApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        getSupportActionBar().setTitle("Stock");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewStock = findViewById(R.id.tv_stock);

        webappApi = APIDistributor.getRetrofit().create(WebappApi.class);
        StockRunnable stockRunnable = new StockRunnable();
        new Thread(stockRunnable).start();
    }

    class StockRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<Stock>> call = webappApi.getStock();

            call.enqueue(new Callback<List<Stock>>() {
                @Override
                public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                    if (!response.isSuccessful()) {
                        textViewStock.setText("Code: "+response.code());
                        return;
                    }

                    List<Stock> stocks = response.body();

                    for (Stock stock: stocks) {
                        String content = "";
                       // content += "Stock Id: " + stock.getStockId() + "\n";
                        content += "Product Name: " + stock.getName() + "\n";
                        content += "Product Quantity: " + stock.getProductQuantity() + "\n";
                        //content += "Price: " + stock.getStockPrice() + "\n";
                        content += "Warehouse Name: " + stock.getWarehouseName() + "\n\n";

                        textViewStock.append(content);
                    }
                }

                @Override
                public void onFailure(Call<List<Stock>> call, Throwable t) {
                    textViewStock.setText(t.getMessage());
                }
            });
        }
    }
}