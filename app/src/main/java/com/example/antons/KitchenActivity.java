package com.example.antons;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalTime;



public class KitchenActivity extends AppCompatActivity implements OrderAdapter.OnTableClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_main);

        Button backButton = findViewById(R.id.backButton);
        Button selectedButton = findViewById(R.id.kokButton);
        selectedButton.setBackgroundResource(R.drawable.selected_button);

        RecyclerView orderView = findViewById(R.id.orderView);


        List<Order> orderList = new ArrayList<Order>(Arrays.asList(
                new Order("1","Fish", "14:10"),
                new Order("2","Meat", "15:40"),
                new Order("3", "Chicken Curry", "18:30"),
                new Order("4", "Vegetarian Pizza", "20:15"),
                new Order("5", "Beef Stir Fry", "16:00"),
                new Order("6", "Shrimp Scampi", "19:20"),
                new Order("7", "Lasagna", "15:55"),
                new Order("8", "Salmon Salad", "17:40"),
                new Order("9", "Vegetable Curry", "21:05")));

        OrderAdapter orderAdapter = new OrderAdapter(orderList);
        orderAdapter.setOnTableClickListener(this);
        orderView.setLayoutManager(new LinearLayoutManager(this));
        orderView.setAdapter(orderAdapter);


        TextView textView = findViewById(R.id.tableText);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This will close the current activity and take you back to the previous activity
                finish();
            }
        });
    }

    @Override
    public void tableOnClick(String table){
        //Visa upp på vyn åt vänster
    }


}
