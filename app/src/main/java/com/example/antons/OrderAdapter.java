package com.example.antons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<Order> tableOrders;


    public OrderAdapter(List<Order> tableOrders){
        this.tableOrders = tableOrders;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView order;
        private TextView time;
        private TextView table;

        private TextView type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            order = (TextView) itemView.findViewById(R.id.orderText);
            time = (TextView) itemView.findViewById(R.id.timeText2);
        }


        public TextView getOrder() {
            return order;
        }

        public TextView getTable(){
            return table;
        }

        public TextView getTime() {return time;}

        public TextView getType() {return type;}
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_orders, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = tableOrders.get(position);
        holder.time.setText(order.getTime());
        holder.order.setText(order.getOrder());
        //holder.type.setText(order.getType());
    }

    @Override
    public int getItemCount() {
        return tableOrders.size();
    }

    public Order getItem(int position){
        return this.tableOrders.get(position);
    }

    public void clear(){
        this.tableOrders.clear();
        this.notifyItemRangeRemoved(0,getItemCount());

    }


}
