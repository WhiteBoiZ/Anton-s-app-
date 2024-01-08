package com.example.antons;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    // Ersätt IP_ADDRESS i BASE_URL med egen IP här och i network_security_config.xml
    private static final String BASE_URL = "http://192.168.0.176:8080/Antons-Skafferi-Webb-1.0-SNAPSHOT/";
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

    public void fetchOrders(Callback<List<OrderTemp>> callback) {
        Call<List<OrderTemp>> call = myApi.getOrders();
        call.enqueue(callback);
    }

    public void fetchDishes(Callback<List<Dish>> callback) {
        Call<List<Dish>> call = myApi.getDishes();
        call.enqueue(callback);
    }

    public MyApi getMyApi() {
        return myApi;
    }
}
