package com.example.antons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.List;

public class TableOrder extends RecyclerView.Adapter<TableOrder.ViewHolder> {
    private List<Order> tableOrders;

    public TableOrder(List<Order> tableOrders){
        this.tableOrders = tableOrders;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView order;
        private TextView time;
        private TextView table;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.timeText2);
        }


        public TextView getOrder() {
            return order;
        }

        public TextView getTable(){
            return table;
        }

        public TextView getTime() {return time;}
    }

    @NonNull
    @Override
    public TableOrder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_orders, parent, false);
        return new TableOrder.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableOrder.ViewHolder holder, int position) {
        Order order = tableOrders.get(position);
        //holder.table.setText("Ruta 2: " + order.getTable());
        //holder.order.setText(order.getOrder());
        holder.time.setText(order.getTime());
    }

    @Override
    public int getItemCount() {
        return tableOrders.size();
    }
}
