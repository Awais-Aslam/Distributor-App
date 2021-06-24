package com.spikotech.sndapp.distributorapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    List<Shop> shops;

    public static class ShopViewHolder extends RecyclerView.ViewHolder {

        public TextView shopName;
        public TextView shopPhone;
        public TextView shopCnic;
        public TextView shopCity;
        public TextView shopArea;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);

            shopName = itemView.findViewById(R.id.tv_shopName);
            shopPhone = itemView.findViewById(R.id.tv_shopNumber);
            shopCnic = itemView.findViewById(R.id.tv_shopCnic);
            shopCity = itemView.findViewById(R.id.tv_shopCity);
            shopArea = itemView.findViewById(R.id.tv_shopArea);
        }
    }

    public ShopAdapter(List<Shop> shops) {
        this.shops = shops;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        ShopViewHolder holder = new ShopViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Shop shop = shops.get(position);

        holder.shopName.setText(shop.getShopName());
        holder.shopPhone.setText(shop.getShopPhone());
        holder.shopCnic.setText(shop.getShopCnic());
        holder.shopCity.setText(shop.getCity());
        holder.shopArea.setText(shop.getArea());
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }
}
