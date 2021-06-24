package com.spikotech.sndapp.distributorapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceOrder {

    @SerializedName("shopId")
    @Expose
    private Integer shopId;
    @SerializedName("agentId")
    @Expose
    private Integer agentId;
    @SerializedName("orderedProducts")
    @Expose
    private List<OrderProduct> orderedProducts = null;
    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("totalProfit")
    @Expose
    private Integer totalProfit;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;

    public PlaceOrder() {}

    public PlaceOrder(Integer shopId, Integer agentId, List<OrderProduct> orderedProducts, Integer totalAmount, Integer totalProfit, String orderDate) {
        this.shopId = shopId;
        this.agentId = agentId;
        this.orderedProducts = orderedProducts;
        this.totalAmount = totalAmount;
        this.totalProfit = totalProfit;
        this.orderDate = orderDate;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public List<OrderProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Integer totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
