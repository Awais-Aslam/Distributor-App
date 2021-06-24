package com.spikotech.sndapp.distributorapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    List<Order> orders;

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        public TextView shop;
        public TextView agent;
        public TextView totalAmount;
        public TextView profit;
        public TextView date;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            shop = itemView.findViewById(R.id.tv_shop);
            agent = itemView.findViewById(R.id.tv_agent);
            totalAmount = itemView.findViewById(R.id.tv_total_amount);
            profit = itemView.findViewById(R.id.tv_Profit);
            date = itemView.findViewById(R.id.tv_date);
        }
    }

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(view);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.shop.setText(order.getShop());
        holder.agent.setText(order.getAgent());
        double b = order.getTotalAmount();
        String amount = String.valueOf(b);
        holder.totalAmount.setText(amount);
        int a = order.getTotalProfit();
        String profit = String.valueOf(a);
        //holder.profit.setText(profit);
        holder.date.setText(order.getOrderDate());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
