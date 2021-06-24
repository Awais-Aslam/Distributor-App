package com.spikotech.sndapp.distributorapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productCode")
    @Expose
    private String productCode;
    @SerializedName("productPrice")
    @Expose
    private Double productPrice;
    @SerializedName("productCost")
    @Expose
    private Double productCost;
    @SerializedName("expireable")
    @Expose
    private String expireable;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("name")
    @Expose
    private String productBrand;

    public Product() {}

    public Product(String productName, String productCode, Double productPrice, Double productCost, String expireable, String unit, String productBrand) {
        this.productName = productName;
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.productCost = productCost;
        this.expireable = expireable;
        this.unit = unit;
        this.productBrand = productBrand;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductCost() {
        return productCost;
    }

    public void setProductCost(Double productCost) {
        this.productCost = productCost;
    }

    public String getExpireable() {
        return expireable;
    }

    public void setExpireable(String expireable) {
        this.expireable = expireable;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }
}