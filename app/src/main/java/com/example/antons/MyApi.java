package com.example.antons;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi {
    @GET("api/alacarte/all")
    Call<List<Dish>> getDishes();

    @GET("api/bestallning/rattinstans/all")
    Call<List<OrderApi>> getOrders();


    @POST("api/bestallning")
    Call<Void> addOrder(
            @Query("datum") String date,
            @Query("tid") String time,
            @Query("kommentar") String comment,
            @Query("bordId") int bordId
    );

}
