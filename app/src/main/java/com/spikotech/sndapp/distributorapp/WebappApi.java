package com.spikotech.sndapp.distributorapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebappApi {

    @GET("stock")
    Call<List<Stock>> getStock();

    @GET("order")
    Call<List<Order>> getOrder();

    @GET("shop")
    Call<List<Shop>> getShops();

    @GET("shop")
    Call<List<ShopName>> getShopName();

    @GET("agent")
    Call<List<AgentName>> getAgentName();

    @GET("product")
    Call<List<ProductName>> getProductName();

    @GET("product")
    Call<List<Product>> getProductList();

    @GET("brand")
    Call<List<Brand>> getAllBrands();

    @GET("area")
    Call<List<Area>> getAreaName();

    @GET("city")
    Call<List<City>> getCityName();

    @Headers({"Content-Type: application/json"})
    @POST("order")
    Call<String> createOrder(@Body PlaceOrder order);

    @Headers({"Content-Type: application/json"})
    @POST("shop")
    Call<String> createShop(@Body PlaceShop shop);

    @Headers({"Content-Type: application/json"})
    @POST("account/register")
    Call<String> createUser(@Body RegisterUser user);
}
