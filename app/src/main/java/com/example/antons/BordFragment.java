package com.example.antons;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
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

    private Button golvStarterViewTextButton;
    private Button golvMainCourseViewTextButton;
    private Button golvDessertViewTextButton;

    private List<OrderTemp> orderApiList;
    private Handler handler = new Handler();
    private final int delayMillis = 1000;

    private Boolean toogledone = false;


    private int tableId;

    private RecyclerView golvStarterView, golvMainCourseView, golvDessertView;
    private OrderAdapter golvStarterAdapter, golvMainCourseAdapter, golvDessertAdapter;

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

        golvStarterView = view.findViewById(R.id.golvStarterOrders);
        golvStarterAdapter = new OrderAdapter(new ArrayList<>());
        golvStarterView.setLayoutManager(new LinearLayoutManager(getContext()));
        golvStarterView.setAdapter(golvStarterAdapter);

        golvMainCourseView = view.findViewById(R.id.golvMainCourseOrders);
        golvMainCourseAdapter = new OrderAdapter(new ArrayList<>());
        golvMainCourseView.setLayoutManager(new LinearLayoutManager(getContext()));
        golvMainCourseView.setAdapter(golvMainCourseAdapter);

        golvDessertView = view.findViewById(R.id.golvDessertOrders);
        golvDessertAdapter = new OrderAdapter(new ArrayList<>());
        golvDessertView.setLayoutManager(new LinearLayoutManager(getContext()));
        golvDessertView.setAdapter(golvDessertAdapter);

        golvStarterViewTextButton = view.findViewById(R.id.buttonGolvStarterText);
        golvMainCourseViewTextButton = view.findViewById(R.id.buttonGolvMainCourseText);
        golvDessertViewTextButton = view.findViewById(R.id.buttonGolvDessertText);

        tableId = Integer.parseInt(getArguments().getString("tableID"));
        startFetchingData(); // Implement this method to fetch data for both columns
        return view;
    }

    private void fetchDataForTable(int tableId) {
        ApiService apiService = ApiService.getInstance();

        apiService.fetchOrders(new Callback<List<OrderTemp>>() {
            @Override
            public void onResponse(Call<List<OrderTemp>> call, Response<List<OrderTemp>> response) {
                if (response.isSuccessful()) {
                    Log.d("ApiService", "API request successful: " + response);
                    orderApiList = response.body();
                    System.out.println(orderApiList);
                    setOrderList(orderApiList);
                } else {
                    Log.e("ApiService", "API request failed: " + response.message());

                }
            }

            @Override
            public void onFailure(Call<List<OrderTemp>> call, Throwable t) {
                Log.e("ApiService", "API request failed: " + t.getMessage());

            }

        });
    }
    private void setOrderList(List<OrderTemp> orderApiList){

        if(orderApiList != null) {
            if (!orderApiList.isEmpty()) {
                List<TableOrder> tableOrderList = new ArrayList<>();
                for (OrderTemp orderTemp : orderApiList) {
                    tableOrderList.add(new TableOrder(orderTemp.getSelectedList(), orderTemp.getOrderInfo().getTableID(), orderTemp.getOrderInfo().getTime()));
                }
                for (TableOrder tableOrder : tableOrderList) {
                    if (tableOrder.getTable() == tableId) {
                            List<OrderApi> starterList = new ArrayList<>();
                            List<OrderApi> mainCourseList = new ArrayList<>();
                            List<OrderApi> dessertList = new ArrayList<>();
                            for (OrderApi orderApi : tableOrder.getOrderList()) {
                                switch (orderApi.getTagID()) {
                                    case 1:
                                        if(orderApi.getOrder().isStartDone() ==true && toogledone == false){
                                            golvStarterViewTextButton.setBackground(getResources().getDrawable(R.drawable.state_nejdone));
                                            starterList.add(orderApi);
                                        }
                                        break;
                                    case 2:
                                        if(!orderApi.getOrder().isMainDone()){
                                            mainCourseList.add(orderApi);
                                        }

                                        break;
                                    case 3:
                                        if(!orderApi.getOrder().isDessertDone()){
                                            dessertList.add(orderApi);
                                        }

                                        break;
                                }
                            }

                            golvStarterAdapter = new OrderAdapter(starterList){
                                @Override
                                public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                                    OrderApi order = starterList.get(position);
                                    holder.getOrder().setText(order.getDish().getTitle());
                                }
                            };
                            golvStarterView.setAdapter(golvStarterAdapter);
                            golvMainCourseAdapter = new OrderAdapter(mainCourseList){
                                @Override
                                public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                                    OrderApi order = mainCourseList.get(position);
                                    holder.getOrder().setText(order.getDish().getTitle());
                                }
                            };
                            golvMainCourseView.setAdapter(golvMainCourseAdapter);
                            golvDessertAdapter = new OrderAdapter(dessertList){
                                @Override
                                public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                                    OrderApi order = dessertList.get(position);
                                    holder.getOrder().setText(order.getDish().getTitle());
                                }
                            };
                            golvDessertView.setAdapter(golvDessertAdapter);
                    }
                }
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Använda getView().findViewById inom ett fragment
        Button addButton = view.findViewById(R.id.addbutton);
        Button backButton = view.findViewById(R.id.backButton);
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

        backButton.setOnClickListener(v -> {
            ((GolvActivity)getActivity()).goBack();
        });


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
        ZoneId zoneId = ZoneId.of("Europe/Stockholm");
        LocalDate currentDate = LocalDate.now(zoneId);
        LocalTime currentTime = LocalTime.now(zoneId);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String date = currentDate.format(dateFormatter);
        String time = currentTime.format(timeFormatter);
        Call<OrderTest> call = myApi.addOrder(date, time, "comment", tableId);
        call.enqueue(new Callback<OrderTest>() {
            @Override
            public void onResponse(Call<OrderTest> call, Response<OrderTest> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    Log.d("ApiService", "Create order POST request successful");
                    int orderId = response.body().getId();
                    if(starterAdapter != null) {
                        postDishes(orderId, starterAdapter.getSelectedDishes());
                        starterAdapter.clear();
                        starterView.setAdapter(starterAdapter);
                    }
                    if(mainCourseAdapter != null) {
                        postDishes(orderId, mainCourseAdapter.getSelectedDishes());
                        mainCourseAdapter.clear();
                        mainCourseView.setAdapter(mainCourseAdapter);
                    }
                    if(dessertAdapter != null) {
                        postDishes(orderId, dessertAdapter.getSelectedDishes());
                        dessertAdapter.clear();
                        dessertView.setAdapter(dessertAdapter);
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

    private void startFetchingData() {
        // Create a runnable to perform the data-fetching task
        Runnable fetchDataRunnable = new Runnable() {
            @Override
            public void run() {
                fetchDataForTable(tableId); // Implement this method to fetch data from the API

                // Schedule the next execution after the delay
                handler.postDelayed(this, delayMillis);
            }
        };

        // Post the runnable for the first time
        handler.post(fetchDataRunnable);
    }
}