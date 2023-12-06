package com.example.antons;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MyApi {
    @GET("api/bestallning/all")
    Call<List<Order>> getOrders();

    @GET("api/alacarte/all")
    Call<List<Dish>> getDishes();

    //@PUT("/update-endpoint/{id}")
    //Call<Void> updateData(@Path("id") String itemId, @Body YourRequestClass requestData);

}
