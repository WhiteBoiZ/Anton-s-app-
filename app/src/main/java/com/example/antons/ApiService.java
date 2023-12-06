package com.example.antons;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static final String BASE_URL = "http://10.82.226.253:8080/Antons-Skafferi-Webb-1.0-SNAPSHOT/";
    private static ApiService instance;
    private final MyApi myApi;

    private ApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi = retrofit.create(MyApi.class);
    }

    public static synchronized ApiService getInstance() {
        if(instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public void fetchOrders(Callback<List<Order>> callback) {
        Call<List<Order>> call = myApi.getOrders();
        call.enqueue(callback);
    }

    public void fetchDishes(Callback<List<Dish>> callback) {
        Call<List<Dish>> call = myApi.getDishes();
        call.enqueue(callback);
    }
}
