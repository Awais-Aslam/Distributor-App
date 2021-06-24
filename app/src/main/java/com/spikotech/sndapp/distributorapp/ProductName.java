package com.spikotech.sndapp.distributorapp;

public class ProductName {

    private int productId;
    private String productName;

    public ProductName() {}

    public ProductName(String name) {
        this.productName = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return productName;
    }

    public void setName(String name) {
        this.productName = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
