package com.example.antons;

import android.content.Intent;
import android.os.Bundle;
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

    private List<OrderApi> orderList;

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


        orderView = findViewById(R.id.orderView);


        List<TableOrder> tableOrderList = new ArrayList<>();

        tableOrderAdapter = new TableOrderAdapter(tableOrderList);
        tableOrderAdapter.setOnTableClickListener(this);
        orderView.setLayoutManager(new LinearLayoutManager(this));
        orderView.setAdapter(tableOrderAdapter);



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
                    starterView.setLayoutManager(null);
                    starterView.setAdapter(null);
                    tableOrderAdapter.removeOrder(order.getOrder().getTableID(), order.getTagName());
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
                    mainCourseView.setLayoutManager(null);
                    mainCourseView.setAdapter(null);
                    tableOrderAdapter.removeOrder(order.getOrder().getTableID(), order.getTagName());
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
                    dessertView.setLayoutManager(null);
                    dessertView.setAdapter(null);
                    tableOrderAdapter.removeOrder(order.getOrder().getTableID(), order.getTagName());
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


    public void fetchOrders(){
        ApiService apiService = ApiService.getInstance();
        apiService.fetchOrders(new Callback<List<OrderApi>>() {
            @Override
            public void onResponse(Call<List<OrderApi>> call, Response<List<OrderApi>> response) {
                if (response.isSuccessful()) {
                    Log.d("ApiService", "API request successful: " + response);
                    List<OrderApi> orderApiList = response.body();
                    System.out.println(orderApiList);
                    // Handle the response data here
                } else {
                    Log.e("ApiService", "API request failed: " + response.message());
                    // Handle the error here
                }            }

            @Override
            public void onFailure(Call<List<OrderApi>> call, Throwable t) {
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
        //List<Order> tableClicked = new ArrayList<Order>();
        List<OrderApi> starterList = new ArrayList<>();
        List<OrderApi> mainCourseList = new ArrayList<>();
        List<OrderApi> dessertList = new ArrayList<>();
        for(int i = 0; i<orderList.size();++i){
            if(orderList.get(i).getOrder().getTableID() == tableID){
                System.out.println("Bord" + tableID);
                switch(orderList.get(i).getTagName()){
                    case "Varmrätt":
                        mainCourseList.add(orderList.get(i));
                        break;
                    case "Förrätt":
                        starterList.add(orderList.get(i));
                        break;
                    case "Dessert":
                        dessertList.add(orderList.get(i));
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




}
