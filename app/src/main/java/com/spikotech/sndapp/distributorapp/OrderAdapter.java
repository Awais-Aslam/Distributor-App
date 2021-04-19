package com.spikotech.sndapp.distributorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderList> list;
    private Context context;

    public OrderAdapter(Context context, List<OrderList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderList current = list.get(position);

        holder.shopName.setText(current.getShop());
        holder.agentName.setText(current.getAgent());
        holder.totalAmount.setText(current.getTotalAmount());
        holder.profit.setText(current.getTotalProfit());
        holder.date.setText(current.getOrderDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        private TextView shopName;
        private TextView agentName;
        private TextView totalAmount;
        private TextView profit;
        private TextView date;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            shopName = itemView.findViewById(R.id.tv_shopname);
            agentName = itemView.findViewById(R.id.tv_agentname);
            totalAmount = itemView.findViewById(R.id.tv_totalamount);
            profit = itemView.findViewById(R.id.tv_profit);
            date = itemView.findViewById(R.id.tv_date);
        }
    }
}
