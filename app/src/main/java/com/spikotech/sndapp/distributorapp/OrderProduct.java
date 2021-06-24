package com.spikotech.sndapp.distributorapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderProduct {

    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("productName")
    @Expose
    private String name;
    @SerializedName("productCode")
    @Expose
    private String productCode;
    @SerializedName("productPrice")
    @Expose
    private Integer productPrice;
    @SerializedName("productCost")
    @Expose
    private Integer productCost;
    @SerializedName("expireable")
    @Expose
    private String expireable;
    @SerializedName("brandId")
    @Expose
    private Integer brandId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("unit")
    @Expose
    private String unit;

    public OrderProduct() {}

    public OrderProduct(Integer productId, String name, String productCode, Integer productPrice, Integer productCost, String expireable, Integer brandId, Integer quantity, String unit) {
        this.productId = productId;
        this.name = name;
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.productCost = productCost;
        this.expireable = expireable;
        this.brandId = brandId;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductCost() {
        return productCost;
    }

    public void setProductCost(Integer productCost) {
        this.productCost = productCost;
    }

    public String getExpireable() {
        return expireable;
    }

    public void setExpireable(String expireable) {
        this.expireable = expireable;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
