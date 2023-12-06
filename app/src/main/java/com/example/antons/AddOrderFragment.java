package com.example.antons;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class AddOrderFragment extends Fragment {

    private FoodAdapter foodAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_order, container, false);

        // Initialisera RecyclerView och FoodAdapter för vänstra fältet
        RecyclerView recyclerView = view.findViewById(R.id.starterOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(foodAdapter);

        // Hämta data från servern
        FetchMenuTask task = new FetchMenuTask(); // Se till att FetchMenuTask är anpassad för att arbeta med fragment
        task.execute();

        return view;
    }
    public void updateData(ArrayList<Food> foodList) {
        foodAdapter.updateData(foodList);
    }


}

/*



 */