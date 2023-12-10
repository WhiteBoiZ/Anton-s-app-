package com.example.antons;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class BordFragment extends Fragment implements AddOrderFragment.OnPassOrder{


    private RecyclerView starterView;
    private RecyclerView mainCourseView;
    private RecyclerView dessertView;
    private OrderAdapter starterAdapter;
    private OrderAdapter mainCourseAdapter;
    private OrderAdapter dessertAdapter;

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

        Button addStarter = view.findViewById(R.id.addStarter);
        Button addMainCourse = view.findViewById(R.id.addMainCourse);
        Button addDessert = view.findViewById(R.id.addDessert);

        addStarter.setOnClickListener(this::onClick);
        addMainCourse.setOnClickListener(this::onClick);
        addDessert.setOnClickListener(this::onClick);



    }


    public void onClick(View view){
        if(view.getId() == R.id.addStarter){
            System.out.println("Förrätt");
            AddOrderFragment addOrderFragment = new AddOrderFragment("Förrätt", this.getArguments().getString("tableID"));
            addOrderFragment.setOnPassOrder(this);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, addOrderFragment)
                    .addToBackStack(null)
                    .commit();
        }
        if(view.getId() == R.id.addMainCourse){
            System.out.println("Varmrätt");
            AddOrderFragment addOrderFragment = new AddOrderFragment("Varmrätt", this.getArguments().getString("tableID"));
            addOrderFragment.setOnPassOrder(this);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, addOrderFragment)
                    .addToBackStack(null)
                    .commit();
        }
        if(view.getId() == R.id.addDessert){
            System.out.println("Dessert");
            AddOrderFragment addOrderFragment = new AddOrderFragment("Dessert", this.getArguments().getString("tableID"));
            addOrderFragment.setOnPassOrder(this);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, addOrderFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    //Gets the selected data from "AddOrderFragment".
    @Override
    public void onDataPassed(List<Order> list, String type) {
        if(!list.isEmpty()){
            switch(type){
                case "Förrätt":
                    System.out.println("Sets list");
                    starterAdapter = new OrderAdapter(list);
                    break;
                case "Varmrätt":
                    System.out.println("Varmrätt-lista");
                    mainCourseAdapter = new OrderAdapter(list);
                    break;
                case "Dessert":
                    System.out.println("Dessert-lista");
                    dessertAdapter = new OrderAdapter(list);
                    break;
            }
        }
    }
}