package com.example.antons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/*
 * Class for creating a dynamic list using a recyclerview to display data from the class "OrderApi".
 * */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<DishInstance> tableOrders;


    public OrderAdapter(List<DishInstance> tableOrders){
        this.tableOrders = tableOrders;
    }



    /*
     * Custom ViewHolder for the recyclerview.
     * Using the views in the "table_orders" layout.
     * */
    public static class ViewHolder extends RecyclerView.ViewHolder{


        private TextView order;
        private TextView time;
        private TextView table;

        private TextView type;
        public void setOrder(TextView order) {
            this.order = order;
        }

        public void setTime(TextView time) {
            this.time = time;
        }

        public void setTable(TextView table) {
            this.table = table;
        }

        public void setType(TextView type) {
            this.type = type;
        }


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

    /*
     * Method for creating new views in the list.
     * */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_orders, parent, false);

        return new ViewHolder(view);

    }

    /*
     * Sets the content to the textview.
     * */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DishInstance order = tableOrders.get(position);
        holder.order.setText(order.getDish().getTitle());
    }

    @Override
    public int getItemCount() {
        return tableOrders.size();
    }

    public DishInstance getItem(int position){
        return this.tableOrders.get(position);
    }

    /*
    * Clears the list.
    * */
    public void clear(){
        this.tableOrders.clear();
        this.notifyItemRangeRemoved(0,getItemCount());
    }


}
