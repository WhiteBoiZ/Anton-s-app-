package com.example.antons;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<Order> orderList;
    public OrderAdapter(List<Order> orderList){
        this.orderList = orderList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView order;
        private TextView table;

        public ViewHolder(View itemView) {
            super(itemView);
            order = (TextView) itemView.findViewById(R.id.orderText);
            table = (TextView) itemView.findViewById(R.id.tableText);
        }

        public TextView getOrder() {
            return order;
        }

        public TextView getTable(){
            return table;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.table.setText(order.getTable());
        holder.order.setText(order.getOrder());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
