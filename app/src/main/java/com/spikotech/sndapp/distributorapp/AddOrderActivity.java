package com.spikotech.sndapp.distributorapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddOrderActivity extends AppCompatActivity {
    private Spinner spinnerShop;
    private Spinner spinnerAgent;
    private Spinner spinnerProduct;
    private EditText editTextQuantity;
    private TextView textViewProfit;
    private TextView textViewAmount;
    private Button addProduct;
    private Button placeOrder;
    int previousTotal = 0;
    int previousProfit = 0;
    int shopId=0;
    int agentId = 0;
    int brandId = 0;
    private WebappApi webappApi;
    private TextView tvDate;
    private List<OrderProduct> orderProductList = new ArrayList<>();
    private List<AgentName> agentIdList = new ArrayList<>();
    private List<ShopName> shopIdList = new ArrayList<>();
    private List<Brand> brandList = new ArrayList<>();

    //Date Picker variables
    private static final String TAG = "AddOrder";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        getSupportActionBar().setTitle("Add New Order");

        spinnerShop = findViewById(R.id.spinner_shop);
        spinnerAgent = findViewById(R.id.spinner_agent);
        spinnerProduct = findViewById(R.id.spinner_product);
        editTextQuantity = findViewById(R.id.edt_quantity);
        textViewAmount = findViewById(R.id.txt_totalAmount);
        textViewProfit = findViewById(R.id.txt_totalProfit);
        tvDate = findViewById(R.id.tvDate);
        addProduct = findViewById(R.id.btn_add_product);
        placeOrder = findViewById(R.id.btn_placeOrder);

        editTextQuantity.addTextChangedListener(textWatcherQuantity);
        textViewAmount.addTextChangedListener(textWatcherInput);
        textViewProfit.addTextChangedListener(textWatcherInput);
        tvDate.addTextChangedListener(textWatcherInput);

        webappApi = APIDistributor.getRetrofit().create(WebappApi.class);

        ShopRunnable shopRunnable = new ShopRunnable();
        new Thread(shopRunnable).start();
        AgentRunnable agentRunnable = new AgentRunnable();
        new Thread(agentRunnable).start();
        ProductNameRunnable productNameRunnable = new ProductNameRunnable();
        new Thread(productNameRunnable).start();
        BrandListRunnable brandListRunnable = new BrandListRunnable();
        new Thread(brandListRunnable).start();

        //Date Picker
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal  = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        AddOrderActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListner,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();
            }
        });
        mDateSetListner  = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG, "onDateSet: mm/dd/yy: " + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    private TextWatcher textWatcherQuantity = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String quantity = editTextQuantity.getText().toString().trim();
            addProduct.setEnabled(!quantity.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher textWatcherInput = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String amount = textViewAmount.getText().toString().trim();
            String profit = textViewProfit.getText().toString().trim();
            String date = tvDate.getText().toString().trim();
            if (date.contentEquals("Order Date")) {
                placeOrder.setEnabled(false);
            } else {
                placeOrder.setEnabled(!amount.isEmpty() && !date.isEmpty());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    class BrandListRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<Brand>> call = webappApi.getAllBrands();

            call.enqueue(new Callback<List<Brand>>() {
                @Override
                public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(AddOrderActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    }

                    List<Brand> brands = response.body();
                    for (Brand brand : brands) {
                        int id = brand.getBrandId();
                        String name = brand.getBrandName();
                        String category = brand.getCategory();
                        Brand brand1 = new Brand(id, name, category);
                        brandList.add(brand1);
                    }
                }

                @Override
                public void onFailure(Call<List<Brand>> call, Throwable t) {
                    Toast.makeText(AddOrderActivity.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ShopRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<ShopName>> call = webappApi.getShopName();

            call.enqueue(new Callback<List<ShopName>>() {
                @Override
                public void onResponse(Call<List<ShopName>> call, Response<List<ShopName>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(AddOrderActivity.this, "Some error", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<ShopName> shopNames = response.body();

                    for (ShopName shopName : shopNames) {
                        int id = shopName.getShopId();
                        String name = shopName.getName();
                        ShopName shopName1 = new ShopName(id, name);
                        shopIdList.add(shopName1);
                    }

                    ArrayAdapter<ShopName> adapterShop = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, shopNames);
                    adapterShop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerShop.setAdapter(adapterShop);
                }

                @Override
                public void onFailure(Call<List<ShopName>> call, Throwable t) {
                    Toast.makeText(AddOrderActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class AgentRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<AgentName>> call = webappApi.getAgentName();

            call.enqueue(new Callback<List<AgentName>>() {
                @Override
                public void onResponse(Call<List<AgentName>> call, Response<List<AgentName>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(AddOrderActivity.this, "Some error", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<AgentName> agentNames = response.body();

                    for (AgentName agentName : agentNames) {
                        int id = agentName.getAgentId();
                        String name = agentName.getName();
                        AgentName agentName1 = new AgentName(id, name);
                        agentIdList.add(agentName1);
                    }

                    ArrayAdapter<AgentName> adapterAgent = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, agentNames);
                    adapterAgent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerAgent.setAdapter(adapterAgent);
                }

                @Override
                public void onFailure(Call<List<AgentName>> call, Throwable t) {
                    Toast.makeText(AddOrderActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ProductNameRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<ProductName>> call = webappApi.getProductName();

            call.enqueue(new Callback<List<ProductName>>() {
                @Override
                public void onResponse(Call<List<ProductName>> call, Response<List<ProductName>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(AddOrderActivity.this, "Some error", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<ProductName> productNames = response.body();

                    ArrayAdapter<ProductName> adapterProduct = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, productNames);
                    adapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProduct.setAdapter(adapterProduct);
                }

                @Override
                public void onFailure(Call<List<ProductName>> call, Throwable t) {
                    Toast.makeText(AddOrderActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void addProduct(View view) {
        ProductRunnable productRunnable = new ProductRunnable();
        new Thread(productRunnable).start();
    }

    class ProductRunnable implements Runnable {

        @Override
        public void run() {
            String productName = spinnerProduct.getSelectedItem().toString();
            int quantity = Integer.parseInt(editTextQuantity.getText().toString());

            Call<List<Product>> call = webappApi.getProductList();

            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(AddOrderActivity.this, "Some error", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<Product> products = response.body();

                    for (Product product : products) {
                        String name = product.getProductName();

                        if (productName.contentEquals(name)) {

                            int productId  = product.getProductId();
                            String productCode = product.getProductCode();
                            double dubPrice = product.getProductPrice();
                            int price = (int) dubPrice;
                            double dubCost = product.getProductCost();
                            int cost = (int) dubCost;
                            String exp = product.getExpireable();
                            String unit = product.getUnit();
                            String brandName = product.getProductBrand();

                            for (int i = 0; i<brandList.size(); i++) {
                                Brand brand = brandList.get(i);
                                String brandName1 = brand.getBrandName();
                                int bId = brand.getBrandId();
                                if (brandName.contentEquals(brandName1)) {
                                    brandId = bId;
                                }
                            }

                            double productPrice = product.getProductPrice();
                            double productCost = product.getProductCost();
                            double profit = productPrice - productCost;
                            Double totalAmount = quantity * productPrice;
                            Double totalProfit = quantity * profit;

                            int i = Integer.valueOf(totalAmount.intValue());
                            int j = Integer.valueOf(totalProfit.intValue());
                            previousTotal = previousTotal + i;
                            previousProfit = previousProfit + j;

                            String tot = new Double(previousTotal).toString();
                            String pro = new Double(previousProfit).toString();

                            textViewAmount.setText( "Total Amount : " + tot);
                            //textViewProfit.setText( "Total Profit : " + pro);

                            OrderProduct product1 = new OrderProduct(productId, productName, productCode, price, cost, exp, brandId, quantity, unit);
                            orderProductList.add(product1);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Toast.makeText(AddOrderActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void placeOrder(View view) {
        createOrder();
    }

    private void createOrder() {

        String shopName = spinnerShop.getSelectedItem().toString();
        String agentName = spinnerAgent.getSelectedItem().toString();
        String date = tvDate.getText().toString();

        for (int i = 0; i<agentIdList.size(); i++) {
            AgentName agentName1 = agentIdList.get(i);
            String agent1 = agentName1.getName();
            int id = agentName1.getAgentId();
            if (agentName.contentEquals(agent1)) {
                agentId = id;
            }
        }

        for (int i = 0; i<shopIdList.size(); i++) {
            ShopName shopName1 = shopIdList.get(i);
            String shop1 = shopName1.getName();
            int id = shopName1.getShopId();
            if (shopName.contentEquals(shop1)) {
                shopId = id;
            }
        }

        PlaceOrder order = new PlaceOrder(shopId, agentId, orderProductList, previousTotal, previousProfit, date);

        Call<String> call = webappApi.createOrder(order);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddOrderActivity.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(AddOrderActivity.this, "Order Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddOrderActivity.this, MyOrdersActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(AddOrderActivity.this, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void cancelOrder(View view) {
        Intent intent = new Intent(AddOrderActivity.this, MainActivity.class);
        startActivity(intent);
    }
}