package com.example.antons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*
 * Class for creating a dynamic list using a recyclerview to display data from the class "Dish".
 * */
public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder>{


    private List<Dish> dishList;

    public DishAdapter(){this.dishList = new ArrayList<Dish>();};

    public DishAdapter(List<Dish> dishList){
        this.dishList = dishList;
    }

    /*
     * Custom ViewHolder for the recyclerview.
     * Using the views in the "add_order" layout.
     * */
    public static class ViewHolder extends RecyclerView.ViewHolder{


        private TextView title;

        private TextView type;
        public void setTitle(TextView title) {
            this.title = title;
        }

        public void setType(TextView type) {
            this.type = type;
        }


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.orderText);
        }


        public TextView getTitle() {
            return title;
        }
        public TextView getType() {return type;}



    }

    /*
     * Method for creating new views in the list.
     * */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_order, parent, false);

        return new ViewHolder(view);

    }

    /*
     * Sets the content to the textview.
     * */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Dish dish = dishList.get(position);
        holder.getTitle().setText(dish.getTitle());
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public void add(Dish dish){
        this.dishList.add(dish);
        this.notifyItemInserted(dishList.size()-1);
    }

    public List<Dish> getSelectedDishes(){
        return this.dishList;
    }


    public void clear(){
        this.dishList.clear();
        notifyItemRangeRemoved(0,dishList.size()-1);
    }

}
