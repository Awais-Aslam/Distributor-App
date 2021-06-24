package com.spikotech.sndapp.distributorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddShopActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextCnic;
    private EditText editTextPhone;
    private Spinner spinnerArea;
    private Spinner spinnerCity;
    private Button addShop;
    private WebappApi webappApi;
    private int areaId = 0;
    private int cityId = 0;
    private List<City> cityIdList = new ArrayList<>();
    private List<Area> areaIdList = new ArrayList<>();

    private LocationManager locationManager;
    private LocationListener locationListener;
    private double longitude = 0.0;
    private double latitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        getSupportActionBar().setTitle("Add Shop");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerArea = findViewById(R.id.spinner_area);
        spinnerCity = findViewById(R.id.spinner_city);
        editTextName = findViewById(R.id.edt_customer_name);
        editTextCnic = findViewById(R.id.edt_customer_cnic);
        editTextPhone = findViewById(R.id.edt_customer_phone);
        addShop = findViewById(R.id.btn_add_shop);

        editTextName.addTextChangedListener(textWatcherInput);
        editTextPhone.addTextChangedListener(textWatcherInput);
        editTextCnic.addTextChangedListener(textWatcherInput);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);
            return;
        } else {
            configureButton();
        }

        webappApi = APIDistributor.getRetrofit().create(WebappApi.class);

        CityRunnable cityRunnable = new CityRunnable();
        new Thread(cityRunnable).start();
        AreaRunnable areaRunnable = new AreaRunnable();
        new Thread(areaRunnable).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    private void configureButton() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates("gps", 5000, 5, locationListener);
    };

    private TextWatcher textWatcherInput =  new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String name = editTextName.getText().toString().trim();
            String phone = editTextPhone.getText().toString().trim();
            String cnic = editTextCnic.getText().toString().trim();
            addShop.setEnabled(!name.isEmpty() && !phone.isEmpty() && !cnic.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    class AreaRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<Area>> call = webappApi.getAreaName();

            call.enqueue(new Callback<List<Area>>() {
                @Override
                public void onResponse(Call<List<Area>> call, Response<List<Area>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(AddShopActivity.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<Area> areaNames = response.body();

                    for (Area area : areaNames) {
                        int id = area.getAreaId();
                        String name = area.getName();
                        Area area1 = new Area(id, name);
                        areaIdList.add(area1);
                    }

                    ArrayAdapter<Area> adapterArea = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, areaNames);
                    adapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerArea.setAdapter(adapterArea);
                }

                @Override
                public void onFailure(Call<List<Area>> call, Throwable t) {
                    Toast.makeText(AddShopActivity.this, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class CityRunnable implements Runnable {

        @Override
        public void run() {
            Call<List<City>> call = webappApi.getCityName();

            call.enqueue(new Callback<List<City>>() {
                @Override
                public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(AddShopActivity.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<City> cityNames = response.body();

                    for (City city : cityNames) {
                        int id = city.getCityId();
                        String name = city.getName();
                        City city1 = new City(id, name);
                        cityIdList.add(city1);
                    }

                    ArrayAdapter<City> adapterCity = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, cityNames);
                    adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCity.setAdapter(adapterCity);
                }

                @Override
                public void onFailure(Call<List<City>> call, Throwable t) {
                    Toast.makeText(AddShopActivity.this, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void addShop(View view) {
        //Toast.makeText(AddShopActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
        String areaName = spinnerArea.getSelectedItem().toString();
        String cityName = spinnerCity.getSelectedItem().toString();

        for (int i = 0; i < areaIdList.size(); i++) {
            Area area = areaIdList.get(i);
            int id = area.getAreaId();
            String area1 = area.getName();
            if (areaName.contentEquals(area1)) {
                areaId = id;
            }
        }

        for (int i = 0; i < cityIdList.size(); i++) {
            City city = cityIdList.get(i);
            int id = city.getCityId();
            String name = city.getName();
            if (cityName.contentEquals(name)) {
                cityId = id;
            }
        }

        String shopName = editTextName.getText().toString();
        String shopCnic = editTextCnic.getText().toString();
        String shopPhone = editTextPhone.getText().toString();

        PlaceShop shop = new PlaceShop(shopName, shopCnic, shopPhone, cityId, areaId, longitude, latitude);

        Call<String> call = webappApi.createShop(shop);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddShopActivity.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(AddShopActivity.this, "Shop Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddShopActivity.this, ShopActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(AddShopActivity.this, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cancelShop(View view) {
        Intent intent = new Intent(AddShopActivity.this, MainActivity.class);
        startActivity(intent);
    }
}