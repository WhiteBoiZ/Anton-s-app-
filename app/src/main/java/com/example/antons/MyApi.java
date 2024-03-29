package com.example.antons;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApi {
    @GET("api/alacarte/all")
    Call<List<Dish>> getDishes();

    @GET("api/bestallning/all/withrattinstans")
    Call<List<OrderWithDishes>> getOrders();


    @POST("api/bestallning")
    Call<Order> addOrder(
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

    @POST("api/bestallning/forrattKlar")
    Call<Void> markStartersAsFinished(
            @Query("id") int id,
            @Query("forrattKlar") boolean starterDone
    );

    @POST("api/bestallning/varmrattKlar")
    Call<Void> markMainsAsFinished(
            @Query("id") int id,
            @Query("varmrattKlar") boolean mainDone
    );

    @POST("api/bestallning/efterrattKlar")
    Call<Void> markDessertsAsFinished(
            @Query("id") int id,
            @Query("efterrattKlar") boolean dessertDone
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
