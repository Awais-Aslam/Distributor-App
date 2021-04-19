package com.spikotech.sndapp.distributorapp;

import com.google.gson.annotations.SerializedName;

public class Stock {

    private int stockId;

    private String name;

    private int productQuantity;

    @SerializedName("name1")
    private String warehouseName;

    private int stockPrice;

    public int getStockId() {
        return stockId;
    }

    public String getName() {
        return name;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public int getStockPrice() {
        return stockPrice;
    }
}
