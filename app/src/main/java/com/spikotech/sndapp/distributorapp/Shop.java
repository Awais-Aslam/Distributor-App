package com.spikotech.sndapp.distributorapp;

import com.google.gson.annotations.SerializedName;

public class Shop {

    private int shopId;

    @SerializedName("name")
    private String shopName;

    private String shopCnic;

    private String shopPhone;

    @SerializedName("name1")
    private String cityName;

    @SerializedName("name2")
    private String subCity;

    public int getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopCnic() {
        return shopCnic;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public String getCityName() {
        return cityName;
    }

    public String getSubCity() {
        return subCity;
    }
}
