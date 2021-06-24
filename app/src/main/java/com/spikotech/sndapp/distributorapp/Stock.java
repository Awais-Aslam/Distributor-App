package com.spikotech.sndapp.distributorapp;

import com.google.gson.annotations.SerializedName;

public class Stock {

    private int stockId;

    private String productName;

    private int productQuantity;

    @SerializedName("warehouseName")
    private String warehouseName;

    private int stockPrice;

    public int getStockId() {
        return stockId;
    }

    public String getName() {
        return productName;
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
