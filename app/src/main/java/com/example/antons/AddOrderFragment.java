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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//Fragment to add dishes to a table order.
public class AddOrderFragment extends Fragment {

    private String type;

    private int typeID;
    private String table;

    private OnPassOrder onPassOrder;

    private DishAdapter dishAdapter;

    private List<Dish> dishList = new ArrayList<Dish>();

    private ListPopupWindow starterListPopupWindow;
    private ListPopupWindow maincourseListPopupWindow;
    private ListPopupWindow dessertListPopupWindow;

    private ListAdapter starterListAdapter;
    private ListAdapter mainCourseAdapter;
    private ListAdapter dessertAdapter;


    public AddOrderFragment(String type, String table, int typeID) {
        this.type = type;
        this.table = table;
        this.typeID = typeID;
    }


    //Sets an instance to the interface.
    public void setOnPassOrder(OnPassOrder onPassOrder) {
        this.onPassOrder = onPassOrder;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_order, container, false);

        dishAdapter = new DishAdapter();
        
        starterListPopupWindow = new ListPopupWindow(requireContext());
        maincourseListPopupWindow = new ListPopupWindow(requireContext());
        dessertListPopupWindow = new ListPopupWindow(requireContext());
        starterListPopupWindow.setAnchorView(view.findViewById(R.id.starterSelector));
        maincourseListPopupWindow.setAnchorView(view.findViewById(R.id.mainCourseSelector));
        dessertListPopupWindow.setAnchorView(view.findViewById(R.id.dessertSelector));

        starterListPopupWindow.setHeight(200);
        maincourseListPopupWindow.setHeight(200);
        dessertListPopupWindow.setHeight(200);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button finishOrderButton = view.findViewById(R.id.addToListButton);
        finishOrderButton.setOnClickListener(this::addToOrder);


        ApiService apiService = ApiService.getInstance();
        MyApi myApi = apiService.getMyApi();
        apiService.fetchDishes(new Callback<List<Dish>>() {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                if (response.isSuccessful()) {
                    Log.d("ApiService", "API request successful: " + response);
                    setDishList(response.body());
                    System.out.println(getDishList());
                    for (Dish dish : getDishList()) {
                        System.out.println(dish.getTitle());
                    }
                    //Create the list of dishes.
                    createList();
                } else {
                    Log.e("ApiService", "API request failed: " + response.message());
                    // Handle the error here
                }            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {
                Log.e("ApiService", "API request failed: " + t.getMessage());
                // Handle the failure here
            }
        });













        RecyclerView selectedView = view.findViewById(R.id.selectedOrders);
        selectedView.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        selectedView.setAdapter(dishAdapter);



        //Shows currently selected table.
        TextView textView = view.findViewById(R.id.preOrderText);
        textView.setText(this.type);


        starterListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dish selectedDish = starterListAdapter.getItem(position);
                selectedDish.getType().setName(type);
                selectedDish.getType().setId(typeID);
                System.out.println(selectedDish.getTitle());
                dishAdapter.add(selectedDish);
                starterListPopupWindow.dismiss();
            }
        });

        // Show the starters when the anchor view is clicked
        view.findViewById(R.id.starterSelector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starterListPopupWindow.show();
            }
        });

        maincourseListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dish selectedDish = mainCourseAdapter.getItem(i);
                selectedDish.getType().setName(type);
                selectedDish.getType().setId(typeID);
                System.out.println(selectedDish.getTitle());
                dishAdapter.add(selectedDish);
                maincourseListPopupWindow.dismiss();
            }
        });

        view.findViewById(R.id.mainCourseSelector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maincourseListPopupWindow.show();
            }
        });

        dessertListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dish selectedDish = dessertAdapter.getItem(i);
                selectedDish.getType().setName(type);
                selectedDish.getType().setId(typeID);
                dishAdapter.add(selectedDish);
                dessertListPopupWindow.dismiss();
            }
        });

        view.findViewById(R.id.dessertSelector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dessertListPopupWindow.show();
            }
        });

    }

    //Interface for passing data
    public interface OnPassOrder {
        void onDataPassed(List<Dish> list, String type);
    }


    private void passData(){
        if(onPassOrder != null){
            onPassOrder.onDataPassed(dishAdapter.getSelectedDishes(), this.type);
        }
    }


    //When the button to add the order is pressed.
    //Navigates to the previous fragment.
    public void addToOrder(View view){
        //Pass the selected data in the orderList
        passData();
        System.out.println("Going back");
        this.getParentFragmentManager().popBackStack();
    }

    private void createList(){
        if(this.dishList == null){
            return;
        }
        List<Dish> starters = new ArrayList<>();
        List<Dish> mainCourse = new ArrayList<>();
        List<Dish> dessert = new ArrayList<>();
        for (Dish item: this.dishList){
            switch(item.getType().getName()){
                case "Förrätt":
                    starters.add(item);
                    break;
                case "Varmrätt":
                    mainCourse.add(item);
                    break;
                case "Efterrätt":
                    dessert.add(item);
                    break;

            }
        }

        starterListAdapter = new ListAdapter(requireContext(),R.layout.simple_spinner_item, starters);
        mainCourseAdapter = new ListAdapter(requireContext(),R.layout.simple_spinner_item, mainCourse);
        dessertAdapter = new ListAdapter(requireContext(),R.layout.simple_spinner_item, dessert);
        starterListPopupWindow.setAdapter(starterListAdapter);
        maincourseListPopupWindow.setAdapter(mainCourseAdapter);
        dessertListPopupWindow.setAdapter(dessertAdapter);
    }


    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }






}