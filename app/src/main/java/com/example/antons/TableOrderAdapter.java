package com.example.antons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
* Class for creating a dynamic list using a recyclerview to display data from the class "TableOrder".
* */
public class TableOrderAdapter extends RecyclerView.Adapter<TableOrderAdapter.ViewHolder> {
    private List<TableOrder> orderList;


    private OnTableClickListener onClickListener;

    public TableOrderAdapter(List<TableOrder> orderList){
        this.orderList = orderList;
    }



    /*
    * Custom ViewHolder for the recyclerview.
    * Using the views in the "order_list" layout.
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
    * Method for creating new views in the list.
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
        TableOrder tableOrder = orderList.get(position);
        holder.table.setText("Bord: " + tableOrder.getTable());
        //holder.order.setText(order.getOrder());
        holder.time.setText(tableOrder.getTime());

        holder.table.setOnClickListener(view -> {
            if(onClickListener != null) {
                onClickListener.tableOnClick(tableOrder.getTable(), tableOrder.getOrderList());
            }
        });
    }


    /*
    * Method for the function that handles the click on an item in the recyclerview.
    * */
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


    /*
    * Removes orders with a specific type from a table.
    * */
    public void removeOrder(String table, String type){
        for(TableOrder tableOrder: orderList){
            tableOrder.getOrderList().removeIf(item -> (item.getTable().equals(table) && item.getType().equals(type)));
        }
        orderList.removeIf(TableOrder::isEmpty);

    }

    public Boolean isEmpty(){
        return orderList.isEmpty();
    }


}
