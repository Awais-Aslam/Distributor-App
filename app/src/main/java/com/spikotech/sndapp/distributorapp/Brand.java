package com.spikotech.sndapp.distributorapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brand {

    @SerializedName("brandId")
    @Expose
    private Integer brandId;
    @SerializedName("productBrandName")
    @Expose
    private String brandName;
    @SerializedName("categoryName")
    @Expose
    private String category;

    public Brand() {}

    public Brand(Integer brandId, String brandName, String category) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.category = category;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
