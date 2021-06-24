package com.spikotech.sndapp.distributorapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shop {

    @SerializedName("shopId")
    @Expose
    private Integer shopId;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("shopCnic")
    @Expose
    private String shopCnic;
    @SerializedName("shopPhone")
    @Expose
    private String shopPhone;
    @SerializedName("cityName")
    @Expose
    private String city;
    @SerializedName("areaName")
    @Expose
    private String area;

    public Shop(String shopName, String shopCnic, String shopPhone, String city, String area) {
        this.shopName = shopName;
        this.shopCnic = shopCnic;
        this.shopPhone = shopPhone;
        this.city = city;
        this.area = area;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopCnic() {
        return shopCnic;
    }

    public void setShopCnic(String shopCnic) {
        this.shopCnic = shopCnic;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return getShopName();
    }
}
