package com.example.antons;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi {
    @GET("api/alacarte/all")
    Call<List<Dish>> getDishes();

    @GET("api/bestallning/all/withrattinstans")
    Call<List<OrderTemp>> getOrders();


    @POST("api/bestallning")
    Call<OrderTest> addOrder(
            @Query("datum") String date,
            @Query("tid") String time,
            @Query("kommentar") String comment,
            @Query("bordId") int bordId
    );

    @POST("api/bestallning/rattinstans")
    Call<Void> addDishToOrder(
            @Query("tagId") int tagId,
            @Query("bestallningId") int orderId,
            @Query("alacarteId") int alacarteId,
            @Query("ratt_preferenser") String dishPreference
    );

    @PATCH("api/bestallning")
    Call<Void> markStartersAsFinished(
            @Query("id") int id,
            @Query("forratKlar") boolean starterDone
    );

    @PATCH("api/bestallning")
    Call<Void> markMainsAsFinished(
            @Query("id") int id,
            @Query("varmrattKlar") boolean starterDone
    );

    @PATCH("api/bestallning")
    Call<Void> markDessertsAsFinished(
            @Query("id") int id,
            @Query("efterrattKlar") boolean starterDone
    );

    @DELETE("api/bestallning/delete/{id}")
    Call<Void> deleteOrder(@Path("id") int orderId);

    @DELETE("api/bestallning/rattinstans/delete")
    Call<Void> deleteDishFromOrder(
            @Query("id") int id,
            @Query("alacarteId") int dishId,
            @Query("bestallningsId") int orderId
    );

    @DELETE("api/bestallning/rattinstans/delete/{id}")
    Call<Void> deleteAllDishesFromOrder(@Path("id") int orderId);

}
