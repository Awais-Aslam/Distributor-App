package com.spikotech.sndapp.distributorapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {

    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("Shop")
    @Expose
    private String shop;
    @SerializedName("Agent")
    @Expose
    private String agent;
    @SerializedName("totalAmount")
    @Expose
    private Double totalAmount;
    @SerializedName("totalProfit")
    @Expose
    private Integer totalProfit;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;

    public Order(String shop, String agent, Double totalAmount, Integer totalProfit, String orderDate) {
        this.shop = shop;
        this.agent = agent;
        this.totalAmount = totalAmount;
        this.totalProfit = totalProfit;
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
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
