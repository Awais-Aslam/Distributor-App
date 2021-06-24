package com.spikotech.sndapp.distributorapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceShop {
    @SerializedName("shopId")
    @Expose
    private Integer shopId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shopCnic")
    @Expose
    private String shopCnic;
    @SerializedName("shopPhone")
    @Expose
    private String shopPhone;
    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("areaId")
    @Expose
    private Integer areaId;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;

    public PlaceShop() {}

    public PlaceShop(String name, String shopCnic, String shopPhone, Integer cityId, Integer areaId, Double longitude, Double latitude) {
        this.name = name;
        this.shopCnic = shopCnic;
        this.shopPhone = shopPhone;
        this.cityId = cityId;
        this.areaId = areaId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
