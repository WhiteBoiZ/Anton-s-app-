package com.example.antons;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BordFragment extends Fragment implements AddOrderFragment.OnPassOrder{


    private RecyclerView starterView;
    private RecyclerView mainCourseView;
    private RecyclerView dessertView;
    private DishAdapter starterAdapter;
    private DishAdapter mainCourseAdapter;
    private DishAdapter dessertAdapter;

    private int tableId;

    public static BordFragment newInstance(String table){
        BordFragment bordFragment = new BordFragment();
        Bundle args = new Bundle();
        args.putString("tableID",table);
        bordFragment.setArguments(args);
        return bordFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bord, container, false);
        System.out.println("View created");
        starterView = view.findViewById(R.id.starterOrders);
        starterView.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        starterView.setAdapter(starterAdapter);
        mainCourseView = view.findViewById(R.id.mainCourseOrders);
        mainCourseView.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        mainCourseView.setAdapter(mainCourseAdapter);
        dessertView = view.findViewById(R.id.dessertOrders);
        dessertView.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        dessertView.setAdapter(dessertAdapter);
        System.out.println("View oncreate");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Använda getView().findViewById inom ett fragment
        Button addButton = view.findViewById(R.id.addbutton);
        //TextView fragmentID = view.findViewById(R.id.fragmentID);
        //fragmentID.setText(this.getArguments().getString("tableID"));
        this.tableId = Integer.parseInt(this.getArguments().getString("tableID"));
        Button addStarter = view.findViewById(R.id.addStarter);
        Button addMainCourse = view.findViewById(R.id.addMainCourse);
        Button addDessert = view.findViewById(R.id.addDessert);

        addStarter.setOnClickListener(this::onClick);
        addMainCourse.setOnClickListener(this::onClick);
        addDessert.setOnClickListener(this::onClick);
        addButton.setOnClickListener(this::createOrder);


    }


    public void onClick(View view){
        if(view.getId() == R.id.addStarter){
            System.out.println("Förrätt");
            AddOrderFragment addOrderFragment = new AddOrderFragment("Förrätt", this.getArguments().getString("tableID"),1);
            addOrderFragment.setOnPassOrder(this);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, addOrderFragment)
                    .addToBackStack(null)
                    .commit();
        }
        if(view.getId() == R.id.addMainCourse){
            System.out.println("Varmrätt");
            AddOrderFragment addOrderFragment = new AddOrderFragment("Varmrätt", this.getArguments().getString("tableID"),2);
            addOrderFragment.setOnPassOrder(this);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, addOrderFragment)
                    .addToBackStack(null)
                    .commit();
        }
        if(view.getId() == R.id.addDessert){
            System.out.println("Dessert");
            AddOrderFragment addOrderFragment = new AddOrderFragment("Efterrätt", this.getArguments().getString("tableID"),3);
            addOrderFragment.setOnPassOrder(this);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, addOrderFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void postDishes(int orderId, List<Dish> dishList) {
        ApiService apiService = ApiService.getInstance();
        MyApi myApi = apiService.getMyApi();
        for (Dish dish : dishList) {
            Call<Void> dishCall = myApi.addDishToOrder(dish.getType().getId(), orderId, dish.getId(), "comment");
            dishCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Handle successful response
                        Log.d("ApiService", "POST dish request successful");
                    } else {
                        // Handle unsuccessful response
                        Log.e("ApiService", "POST dish request failed: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Handle failure
                    Log.e("ApiService", "POST dish request failed: " + t.getMessage());
                }
            });
        }

    }

    public void createOrder(View view) {
        ApiService apiService = ApiService.getInstance();
        MyApi myApi = apiService.getMyApi();
        String time = "15:32:00";
        String date = "datum";
        Call<OrderTest> call = myApi.addOrder(date, time, "comment", tableId);

        call.enqueue(new Callback<OrderTest>() {
            @Override
            public void onResponse(Call<OrderTest> call, Response<OrderTest> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    Log.d("ApiService", "Create order POST request successful");
                    int orderId = response.body().getId();
                    if(starterAdapter != null) {
                        postDishes(orderId, starterAdapter.dishList);
                    }
                    if(mainCourseAdapter != null) {
                        postDishes(orderId, mainCourseAdapter.dishList);
                    }
                    if(dessertAdapter != null) {
                        postDishes(orderId, dessertAdapter.dishList);
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("ApiService", "Create order POST request failed: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<OrderTest> call, Throwable t) {
                // Handle failure
                Log.e("ApiService", "Create order POST request failed: " + t.getMessage());
            }
        });

    }

    //Gets the selected data from "AddOrderFragment".
    @Override
    public void onDataPassed(List<Dish> list, String type) {
        if(!list.isEmpty()){
            switch(type){
                case "Förrätt":
                    System.out.println("Sets list");
                    starterAdapter = new DishAdapter(list);
                    break;
                case "Varmrätt":
                    System.out.println("Varmrätt-lista");
                    mainCourseAdapter = new DishAdapter(list);
                    break;
                case "Efterrätt":
                    System.out.println("Dessert-lista");
                    dessertAdapter = new DishAdapter(list);
                    break;
            }
        }
    }
}