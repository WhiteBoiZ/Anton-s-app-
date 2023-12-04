package com.example.antons;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalTime;



public class KitchenActivity extends AppCompatActivity implements OrderAdapter.OnTableClickListener {

    private OrderAdapter orderAdapter;
    private TableOrder tableOrderAdapter;

    private TableOrder starterOrderAdapter;
    private TableOrder mainCourseOrderAdapter;
    private RecyclerView orderView;
    private RecyclerView tableOrderView;
    private RecyclerView starterView;
    private RecyclerView mainCourseView;
    private RecyclerView dessertView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_main);

        Button backButton = findViewById(R.id.backButton);
        Button selectedButton = findViewById(R.id.kokButton);
        Button finishOrderButton = findViewById(R.id.finishOrder);
        selectedButton.setBackgroundResource(R.drawable.selected_button);


        orderView = findViewById(R.id.orderView);
        List<Order> orderList = new ArrayList<Order>(Arrays.asList(
                new Order("1","Fisk", "14:10", "Förrätt"),
                new Order("2","Kött", "15:40", "Varmrätt"),
                new Order("2", "Kyckling och curry", "15:40", "Varmrätt"),
                new Order("4", "Pizza", "20:15", "Varmrätt"),
                new Order("5", "Spaghetti och köttfärssås", "16:00", "Varmrätt"),
                new Order("6", "Lasagne", "19:20", "Varmrätt"),
                new Order("8", "Lax", "17:40", "Varmrätt"),
                new Order("9", "Vegetariskt", "21:05", "Varmrätt")));


        orderAdapter = new OrderAdapter(orderList);
        orderAdapter.setOnTableClickListener(this);
        orderView.setLayoutManager(new LinearLayoutManager(this));
        orderView.setAdapter(orderAdapter);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This will close the current activity and take you back to the previous activity
                finish();
            }
        });

        finishOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tableOrderAdapter.getItemCount() != 0){
                    Order order = tableOrderAdapter.getItem(0);
                    TextView orderText = findViewById(R.id.tableText2);
                    orderText.setText("");
                    tableOrderAdapter.clear();
                    tableOrderView.setLayoutManager(null);
                    tableOrderView.setAdapter(null);
                    orderAdapter.removeTableOrder(order.getTable());
                    orderView.setAdapter(orderAdapter);
                    System.out.println("Clicked");

                }
            }
        });



    }


    @Override
    public void tableOnClick(String table, List<Order> orderList){
        List<Order> tableClicked = new ArrayList<Order>();
        List<Order> starterList = new ArrayList<>();
        List<Order> mainCourseList = new ArrayList<>();
        for(int i = 0; i<orderList.size();++i){
            if(orderList.get(i).getTable().equals(table)){
                System.out.println("Bord" + table);
                tableClicked.add(orderList.get(i));
                switch(orderList.get(i).getType()){
                    case "Varmrätt":
                        mainCourseList.add(orderList.get(i));
                        break;
                    case "Förrätt":
                        starterList.add(orderList.get(i));
                        break;
                }
            }
        }

        if(!tableClicked.isEmpty()){
            //tableOrderView = findViewById(R.id.tableOrders);
            TextView tableLabel = findViewById(R.id.tableText2);
            tableLabel.setText("Bord: " + table);
            /*
            tableOrderAdapter = new TableOrder(tableClicked);
            tableOrderView.setLayoutManager(new LinearLayoutManager(this));
            tableOrderView.setAdapter(tableOrderAdapter);
            */

            starterView = findViewById(R.id.starterOrders);
            starterOrderAdapter = new TableOrder(starterList);
            starterView.setLayoutManager(new LinearLayoutManager(this));
            starterView.setAdapter(starterOrderAdapter);
            mainCourseView = findViewById(R.id.mainCourseOrders);
            mainCourseOrderAdapter = new TableOrder(mainCourseList);
            mainCourseView.setLayoutManager(new LinearLayoutManager(this));
            mainCourseView.setAdapter(mainCourseOrderAdapter);

        }

    }




}
