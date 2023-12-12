package com.example.antons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button golvButton = findViewById(R.id.golvButton);
        Button kitchenButton = findViewById(R.id.kokButton);

        ApiService apiService = ApiService.getInstance();
        MyApi myApi = apiService.getMyApi();
        String date = "2023-12-12";
        String time = "12:35:00";
        String comment = "TESTTESTTEST";
        int tableId = 2;

        int orderId = 20;

        Call<Void> call = myApi.deleteOrder(orderId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle successful response (resource deleted)
                    Log.d("ApiConnector", "DELETE request successful");
                } else {
                    // Handle unsuccessful response
                    Log.e("ApiConnector", "DELETE request failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
                Log.e("ApiConnector", "DELETE request failed: " + t.getMessage());
            }
        });

       /* Call<OrderTest> call = myApi.addOrder(date, time, comment, tableId);
        call.enqueue(new Callback<OrderTest>() {
            @Override
            public void onResponse(Call<OrderTest> call, Response<OrderTest> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    Log.d("ApiService", "POST request successful");
                } else {
                    // Handle unsuccessful response
                    Log.e("ApiService", "POST request failed: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<OrderTest> call, Throwable t) {
                // Handle failure
                Log.e("ApiService", "POST request failed: " + t.getMessage());
            }
        }); */



       /* apiService.fetchDishes(new Callback<List<Dish>>() {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                if (response.isSuccessful()) {
                    Log.d("ApiService", "API request successful: " + response);
                    List<Dish> dishList = response.body();
                    System.out.println(dishList);
                    for (Dish dish : dishList) {
                        System.out.println(dish.getTitle());
                    }
                    // Handle the response data here
                } else {
                    Log.e("ApiService", "API request failed: " + response.message());
                    // Handle the error here
                }            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {
                Log.e("ApiService", "API request failed: " + t.getMessage());
                // Handle the failure here
            }
        }); */

        /*apiService.fetchOrders(new Callback<List<OrderApi>>() {
            @Override
            public void onResponse(Call<List<OrderApi>> call, Response<List<OrderApi>> response) {
                if (response.isSuccessful()) {
                    Log.d("ApiService", "API request successful: " + response);
                    List<OrderApi> orderList = response.body();
                    System.out.println(orderList);
                    for (OrderApi order : orderList) {
                        System.out.println(order.getDish().getTitle());
                    }
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
        }); */

        golvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the GolvActivity when the Golv button is clicked
                Intent intent = new Intent(MainActivity.this, GolvActivity.class);
                startActivity(intent);
            }
        });


        kitchenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KitchenActivity.class);
                startActivity(intent);
            }
        });


    }


}