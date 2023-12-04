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


    private OnTableClickListener onClickListener;

    public OrderAdapter(List<Order> orderList){
        this.orderList = orderList;
    }



    /*
    * Custom ViewHolder for the recyclerview.
    * Using the views in thex "order_list" layout.
    * */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView order;
        private TextView table;

        private TextView time;



        public ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.timeText);
            table = (TextView) itemView.findViewById(R.id.tableText);
        }

        public TextView getOrder() {
            return order;
        }

        public TextView getTable(){
            return table;
        }

        public TextView getTime() {return time;}
    }

    /*
    * Function for creating new views in the list.
    * */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list, parent, false);
        return new ViewHolder(view);
    }

    /*
    * Sets the content to the textview.
    * Creates an on-click listener.
    * */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.table.setText("Bord: " + order.getTable());
        //holder.order.setText(order.getOrder());
        holder.time.setText(order.getTime());

        holder.table.setOnClickListener(view -> {
            if(onClickListener != null) {
                onClickListener.tableOnClick(order.getTable(), orderList);
            }
        });
    }


    public interface OnTableClickListener{
        void tableOnClick(String table, List<Order> orderList);
    }

    public void setOnTableClickListener(OnTableClickListener onClickListener){
        this.onClickListener = onClickListener;
    }



    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void removeTableOrder(String table, String type){
        this.orderList.removeIf(item -> (item.getTable().equals(table) && item.getType().equals(type)));
    }
}
