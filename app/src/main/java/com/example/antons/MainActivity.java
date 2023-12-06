package com.example.antons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
        Button bord1Button = findViewById(R.id.bord1Button);
        Button bord2Button = findViewById(R.id.bord2Button);
        Button kitchenButton = findViewById(R.id.kokButton);

        ApiService apiService = ApiService.getInstance();
        apiService.fetchDishes(new Callback<List<Dish>>() {
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
        });

        golvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the GolvActivity when the Golv button is clicked
                Intent intent = new Intent(MainActivity.this, GolvActivity.class);
                startActivity(intent);
            }
        });
        bord1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the GolvActivity when the Golv button is clicked
                Intent intent = new Intent(MainActivity.this, Bord1Activity.class);
                startActivity(intent);
            }
        });
        bord2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the GolvActivity when the Golv button is clicked
                Intent intent = new Intent(MainActivity.this, Bord2Activity.class);
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