package com.example.antons;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddOrderFragment extends Fragment {

    private FoodAdapter foodAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_order);

        // Initialisera RecyclerView och FoodAdapter
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        foodAdapter = new FoodAdapter(new ArrayList<>()); // Starta med en tom lista
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodAdapter);

        // Hämta data från servern
        FetchMenuTask task = new FetchMenuTask();
        task.execute();

    }

    public static AddOrderFragment newInstance(String param1, String param2) {
        AddOrderFragment fragment = new AddOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_order, container, false);
    }
}

/*
RecyclerView recyclerView = findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));

List<Food> foodList = // Hämta lista av Alacarte objekt här
FoodAdapter adapter = new FoodAdapter(foodList);
recyclerView.setAdapter(adapter);

@Override
protected void onPostExecute(List<Food> result) {
    if (result != null) {
        myFoodAdapter.updateData(result); // Antag att 'FoodAdapter' är en instans av FoodAdapter
    } else {
        // Visa felmeddelande eller försök hämta data igen
    }
}
 */