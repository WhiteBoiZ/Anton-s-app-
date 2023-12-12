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

    private String table;



    private OnPassOrder onPassOrder;

    private OrderAdapter orderAdapter;

    private List<Dish> dishList = new ArrayList<Dish>();

    private ListPopupWindow starterListPopupWindow;
    private ListPopupWindow maincourseListPopupWindow;
    private ListPopupWindow dessertListPopupWindow;

    private ListAdapter starterListAdapter;
    private ListAdapter mainCourseAdapter;
    private ListAdapter dessertAdapter;


    public AddOrderFragment(String type, String table) {
        this.type = type;
        this.table = table;
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






        List<Order> orderList = new ArrayList<Order>(Arrays.asList(new Order("1","Fisk","15:50", type),
                new Order("1","Kött","15:50", type)));

        this.orderAdapter = new OrderAdapter(orderList){

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.add_order, parent, false);

                return new ViewHolder(view);

            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position){
                Order order = orderList.get(position);
                holder.getOrder().setText(order.getOrder());
                holder.setType(holder.itemView.findViewById(R.id.typeText));
                holder.getType().setText(order.getType());
            }
        };

        RecyclerView selectedView = view.findViewById(R.id.selectedOrders);
        selectedView.setLayoutManager(new LinearLayoutManager(this.requireContext()));
        selectedView.setAdapter(orderAdapter);



        //Shows currently selected table.
        TextView textView = view.findViewById(R.id.preOrderText);
        textView.setText(this.type);


        starterListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = starterListAdapter.getItem(position);
                System.out.println(selectedItem);
                orderAdapter.add(new Order(table,selectedItem,"Tid",type));
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
                String selectedItem = mainCourseAdapter.getItem(i);
                orderAdapter.add(new Order(table,selectedItem,"Tid",type));
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
                String selectedItem = dessertAdapter.getItem(i);
                orderAdapter.add(new Order(table,selectedItem,"Tid",type));
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
        void onDataPassed(List<Order> list, String type);
    }


    private void passData(){
        if(onPassOrder != null){
            onPassOrder.onDataPassed(orderAdapter.getTableOrders(), this.type);
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
        List<String> starters = new ArrayList<>();
        List<String> mainCourse = new ArrayList<>();
        List<String> dessert = new ArrayList<>();
        for (Dish item: this.dishList){
            switch(item.getType().getName()){
                case "Förrätt":
                    starters.add(item.getTitle());
                    break;
                case "Varmrätt":
                    mainCourse.add(item.getTitle());
                    break;
                case "Dessert":
                    dessert.add(item.getTitle());
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