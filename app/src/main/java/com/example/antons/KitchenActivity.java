package com.example.antons;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KitchenActivity extends AppCompatActivity implements TableOrderAdapter.OnTableClickListener {
    private TableOrderAdapter tableOrderAdapter;

    private OrderAdapter starterOrderAdapter;
    private OrderAdapter mainCourseOrderAdapter;

    private OrderAdapter dessertOrderAdapter;
    private RecyclerView orderView;
    private RecyclerView starterView;
    private RecyclerView mainCourseView;
    private RecyclerView dessertView;

    //private List<OrderApi> orderList;

    private List<OrderTemp> orderApiList;

    private Handler handler = new Handler();
    private final int delayMillis = 1000;

    private void deleteDish(int id, int orderId, int dishId) {
        ApiService apiService = ApiService.getInstance();
        MyApi myApi = apiService.getMyApi();

        Call<Void> call = myApi.deleteDishFromOrder(id, orderId, dishId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle successful response (resource deleted)
                    Log.d("ApiService", "DELETE dish request successful");
                } else {
                    // Handle unsuccessful response
                    Log.e("ApiService", "DELETE dish request failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
                Log.e("ApiService", "DELETE dish request failed: " + t.getMessage());
            }
        });
    }

    private void deleteOrder(int orderId) {
        ApiService apiService = ApiService.getInstance();
        MyApi myApi = apiService.getMyApi();
        Call<Void> call = myApi.deleteOrder(orderId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle successful response (resource deleted)
                    Log.d("ApiService", "DELETE request successful");
                } else {
                    // Handle unsuccessful response
                    Log.e("ApiService", "DELETE request failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
                Log.e("ApiService", "DELETE request failed: " + t.getMessage());
            }
        });
    }

    private void setDishesAsFinished(int orderId, int typeId) {

        ApiService apiService = ApiService.getInstance();
        MyApi myApi = apiService.getMyApi();
        Call<Void> call = null;
        switch (typeId) {
            case 1:
                call = myApi.markStartersAsFinished(orderId, true);
                break;
            case 2:
                call = myApi.markMainsAsFinished(orderId, true);
                break;
            case 3:
                call = myApi.markDessertsAsFinished(orderId, true);
                break;

        }
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle successful response (resource deleted)
                    Log.d("ApiService", "POST request successful");
                } else {
                    // Handle unsuccessful response
                    Log.e("ApiService", "POST 1 request failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
                Log.e("ApiService", "POST request failed: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_main);

        Button backButton = findViewById(R.id.backButton);
        //Button selectedButton = findViewById(R.id.kokButton);
        Button finishStartersButton = findViewById(R.id.finishStarters);
        Button finishMainCoursesButton = findViewById(R.id.finishMainCourse);
        Button finishDessertButton = findViewById(R.id.finishDesserts);
        //selectedButton.setBackgroundResource(R.drawable.selected_button);

;
        startFetchingData();

        orderView = findViewById(R.id.orderView);


        //List<TableOrder> tableOrderList = new ArrayList<>();






        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This will close the current activity and take you back to the previous activity
                finish();
            }
        });



        finishStartersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(starterOrderAdapter == null){return;}
                if(starterOrderAdapter.getItemCount() != 0){
                    OrderApi order = starterOrderAdapter.getItem(0);
                    starterOrderAdapter.clear();
                    System.out.println(order.getTagID());
                    setDishesAsFinished(order.getOrder().getId(), order.getTagID());
                    starterView.setLayoutManager(null);
                    starterView.setAdapter(null);
                    tableOrderAdapter.removeOrder(order.getOrder().getTableID(), order.getTagID(), order.getOrder().getTime());
                    System.out.println("Clicked");
                    if(!tableOrderAdapter.isEmpty()){
                        orderView.setAdapter(tableOrderAdapter);
                    }else{
                        orderView.setLayoutManager(null);
                        orderView.setAdapter(null);
                    }

                }
            }
        });

        finishMainCoursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mainCourseOrderAdapter == null){return;}
                if(mainCourseOrderAdapter.getItemCount() != 0){
                    OrderApi order = mainCourseOrderAdapter.getItem(0);
                    mainCourseOrderAdapter.clear();
                    System.out.println(order.getTagID());

                    setDishesAsFinished(order.getOrder().getId(), order.getTagID());
                    mainCourseView.setLayoutManager(null);
                    mainCourseView.setAdapter(null);
                    tableOrderAdapter.removeOrder(order.getOrder().getTableID(), order.getTagID(), order.getOrder().getTime());
                    orderView.setAdapter(tableOrderAdapter);
                    System.out.println("Clicked");
                    if(!tableOrderAdapter.isEmpty()){
                        orderView.setAdapter(tableOrderAdapter);
                    }else{
                        orderView.setLayoutManager(null);
                        orderView.setAdapter(null);
                    }

                }
            }
        });


        finishDessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dessertOrderAdapter == null){return;}
                if(dessertOrderAdapter.getItemCount() != 0){
                    OrderApi order = dessertOrderAdapter.getItem(0);
                    dessertOrderAdapter.clear();
                    System.out.println(order.getTagID());
                    setDishesAsFinished(order.getOrder().getId(), order.getTagID());
                    dessertView.setLayoutManager(null);
                    dessertView.setAdapter(null);
                    tableOrderAdapter.removeOrder(order.getOrder().getTableID(), order.getTagID(), order.getOrder().getTime());
                    if(!tableOrderAdapter.isEmpty()){
                        orderView.setAdapter(tableOrderAdapter);
                    }else{
                        orderView.setLayoutManager(null);
                        orderView.setAdapter(null);
                    }
                    System.out.println("Clicked");

                }
            }
        });


    }

    private void startFetchingData() {
        // Create a runnable to perform the data-fetching task
        Runnable fetchDataRunnable = new Runnable() {
            @Override
            public void run() {
                fetchOrders(); // Implement this method to fetch data from the API

                // Schedule the next execution after the delay
                handler.postDelayed(this, delayMillis);
            }
        };

        // Post the runnable for the first time
        handler.post(fetchDataRunnable);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }


    /*
     * Attach the fetched orders to the recyclerview.
     * */
    private void setOrderList(List<OrderTemp> orderApiList){
        if(orderApiList != null) {
            if (!orderApiList.isEmpty()) {
                List<TableOrder> tableOrderList = new ArrayList<>();
                for (OrderTemp orderTemp : orderApiList) {
                    if(!orderTemp.isDone()) {
                        tableOrderList.add(new TableOrder(orderTemp.getSelectedList(), orderTemp.getOrderInfo().getTableID(), orderTemp.getOrderInfo().getTime()));
                    }
                }
                tableOrderAdapter = new TableOrderAdapter(tableOrderList);
                tableOrderAdapter.setOnTableClickListener(this);
                orderView.setLayoutManager(new LinearLayoutManager(this));
                orderView.setAdapter(tableOrderAdapter);
            }
        }
    }

    /*
    * Fetches orders from the API
    * */
    private void fetchOrders(){
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
                    // Handle the error here
                }            }

            @Override
            public void onFailure(Call<List<OrderTemp>> call, Throwable t) {
                Log.e("ApiService", "API request failed: " + t.getMessage());
                // Handle the failure here
            }
        });



    }

    /*
    * Implementation of the "On click method" for the TableOrderAdapter.
    * Divides the orders that the table has ordered in to different recyclerview's depending on its type.
    * Connects the adapters to each view.
    * */
    @Override
    public void tableOnClick(int tableID, List<OrderApi> orderList){
        List<OrderApi> starterList = new ArrayList<>();
        List<OrderApi> mainCourseList = new ArrayList<>();
        List<OrderApi> dessertList = new ArrayList<>();
        for(int i = 0; i<orderList.size();++i){
            if(orderList.get(i).getOrder().getTableID() == tableID){
                System.out.println("Bord" + tableID);
                System.out.println(orderList.get(i).getId());
                switch(orderList.get(i).getTagID()){
                    case 1:
                        if(!orderList.get(i).getOrder().isStartDone()){
                            starterList.add(orderList.get(i));
                        }
                        break;
                    case 2:
                        if(!orderList.get(i).getOrder().isMainDone()) {
                            mainCourseList.add(orderList.get(i));
                        }
                        break;
                    case 3:
                        if(!orderList.get(i).getOrder().isDessertDone()) {
                            dessertList.add(orderList.get(i));
                        }
                        break;
                }
            }
        }

        if(!orderList.isEmpty()){
            //tableOrderView = findViewById(R.id.tableOrders);
            TextView tableLabel = findViewById(R.id.tableText2);
            tableLabel.setText("Bord: " + tableID);

            starterView = findViewById(R.id.starterOrders);
            starterOrderAdapter = new OrderAdapter(starterList);
            starterView.setLayoutManager(new LinearLayoutManager(this));
            starterView.setAdapter(starterOrderAdapter);
            mainCourseView = findViewById(R.id.mainCourseOrders);
            mainCourseOrderAdapter = new OrderAdapter(mainCourseList);
            mainCourseView.setLayoutManager(new LinearLayoutManager(this));
            mainCourseView.setAdapter(mainCourseOrderAdapter);
            dessertView = findViewById(R.id.dessertOrders);
            dessertOrderAdapter = new OrderAdapter(dessertList);
            dessertView.setLayoutManager(new LinearLayoutManager(this));
            dessertView.setAdapter(dessertOrderAdapter);


        }

    }

    @Override
    protected void onDestroy() {
        // Remove callbacks when the activity is destroyed to avoid memory leaks
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }




}
