package com.example.antons;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KitchenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_main);

        Button backButton = findViewById(R.id.backButton);
        Button selectedButton = findViewById(R.id.kokButton);
        selectedButton.setBackgroundResource(R.drawable.selected_button);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Order order1 = new Order("1","Fisk");
        Order order2 = new Order("2","Kött");
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(order1);
        orderList.add(order2);
        OrderAdapter orderAdapter = new OrderAdapter(orderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderAdapter);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This will close the current activity and take you back to the previous activity
                finish();
            }
        });
    }
}
