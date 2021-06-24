package com.spikotech.sndapp.distributorapp;

public class ShopName {

    private int shopId;
    private String shopName;

    public ShopName() {}

    public ShopName(String shop) {
        shopName = shop;
    }

    public ShopName(int shopId, String name) {
        this.shopId = shopId;
        this.shopName = name;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return shopName;
    }

    public void setName(String name) {
        this.shopName = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
