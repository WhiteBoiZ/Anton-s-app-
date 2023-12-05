package com.example.antons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{
    private static List<Food> foodList;
    public FoodAdapter(List<Food> foodList){
        this.foodList = foodList;
    }
    public void updateData(List<Food> newData) {
        foodList.clear(); // Rensa den gamla listan
        foodList.addAll(newData); // Lägg till nya data
        notifyDataSetChanged(); // Meddela adaptern att data har ändrats
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food item = foodList.get(position);
        holder.textViewTitle.setText(item.getTitel());
        holder.textViewDescription.setText(item.getBeskrivning());
        // Sätt andra fält som pris här
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        // Andra vyer för objekten

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            // Initiera andra vyer här
        }
    }
}
