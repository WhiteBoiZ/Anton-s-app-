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


public class KitchenActivity extends AppCompatActivity implements TableOrderAdapter.OnTableClickListener {

    private TableOrderAdapter tableOrderAdapter;

    private OrderAdapter starterOrderAdapter;
    private OrderAdapter mainCourseOrderAdapter;

    private OrderAdapter dessertOrderAdapter;
    private RecyclerView orderView;
    private RecyclerView starterView;
    private RecyclerView mainCourseView;
    private RecyclerView dessertView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_main);

        Button backButton = findViewById(R.id.backButton);
        //Button selectedButton = findViewById(R.id.kokButton);
        Button finishStartersButton = findViewById(R.id.finishStarters);
        Button finishMainCoursesButton = findViewById(R.id.finishMainCourse);
        Button finishDessertButton = findViewById(R.id.finishDesserts);
        //selectedButton.setBackgroundResource(R.drawable.selected_button);


        orderView = findViewById(R.id.orderView);
        List<Order> orderList1 = new ArrayList<Order>(Arrays.asList(
                new Order("1","Fisk", "14:10", "Förrätt"),
                new Order("1","Kött", "15:40", "Varmrätt"),
                new Order("1", "Kyckling och curry", "15:40", "Varmrätt"),
                new Order("1", "Kyckling och curry", "15:40", "Varmrätt"),
                new Order("1", "Kyckling och curry", "15:40", "Varmrätt"),
                new Order("1", "Kyckling och curry", "15:40", "Varmrätt"),
                new Order("1", "Kyckling och curry", "15:40", "Varmrätt"),
                new Order("1", "Kyckling och curry", "15:40", "Varmrätt"),
                new Order("1", "Kyckling och curry", "15:40", "Varmrätt")));

        List<Order> orderList2 = new ArrayList<Order>(Arrays.asList(
                new Order("2", "Kyckling och curry", "15:40", "Varmrätt"),
                new Order("2", "Pizza", "20:15", "Förrätt"),
                new Order("2", "Spaghetti och köttfärssås", "16:00", "Varmrätt"),
                new Order("2", "Glass", "19:20", "Dessert"),
                new Order("2", "Lax", "17:40", "Varmrätt"),
                new Order("2", "Vegetariskt", "15:40", "Varmrätt"
        )));

        List<TableOrder> tableOrderList = new ArrayList<TableOrder>(Arrays.asList(
                new TableOrder(orderList1,"1","14:30"),
                new TableOrder(orderList2,"2","15:30")
        ));


        tableOrderAdapter = new TableOrderAdapter(tableOrderList);
        tableOrderAdapter.setOnTableClickListener(this);
        orderView.setLayoutManager(new LinearLayoutManager(this));
        orderView.setAdapter(tableOrderAdapter);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This will close the current activity and take you back to the previous activity
                finish();
            }
        });



        finishStartersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(starterOrderAdapter.getItemCount() != 0){
                    Order order = starterOrderAdapter.getItem(0);
                    starterOrderAdapter.clear();
                    starterView.setLayoutManager(null);
                    starterView.setAdapter(null);
                    tableOrderAdapter.removeOrder(order.getTable(), order.getType());
                    System.out.println("Clicked");
                    if(!tableOrderAdapter.isEmpty()){
                        orderView.setAdapter(tableOrderAdapter);
                    }else{
                        orderView.setLayoutManager(null);
                        orderView.setAdapter(null);
                    }

                }
            }
        });

        finishMainCoursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mainCourseOrderAdapter.getItemCount() != 0){
                    Order order = mainCourseOrderAdapter.getItem(0);
                    mainCourseOrderAdapter.clear();
                    mainCourseView.setLayoutManager(null);
                    mainCourseView.setAdapter(null);
                    tableOrderAdapter.removeOrder(order.getTable(), order.getType());
                    orderView.setAdapter(tableOrderAdapter);
                    System.out.println("Clicked");
                    if(!tableOrderAdapter.isEmpty()){
                        orderView.setAdapter(tableOrderAdapter);
                    }else{
                        orderView.setLayoutManager(null);
                        orderView.setAdapter(null);
                    }

                }
            }
        });


        finishDessertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dessertOrderAdapter.getItemCount() != 0){
                    Order order = dessertOrderAdapter.getItem(0);
                    dessertOrderAdapter.clear();
                    dessertView.setLayoutManager(null);
                    dessertView.setAdapter(null);
                    tableOrderAdapter.removeOrder(order.getTable(), order.getType());
                    if(!tableOrderAdapter.isEmpty()){
                        orderView.setAdapter(tableOrderAdapter);
                    }else{
                        orderView.setLayoutManager(null);
                        orderView.setAdapter(null);
                    }
                    System.out.println("Clicked");

                }
            }
        });



    }

    /*
    * Implementation of the "On click method" for the TableOrderAdapter.
    * Divides the orders that the table has ordered in to different recyclerview's depending on its type.
    * Connects the adapters to each view.
    * */
    @Override
    public void tableOnClick(String table, List<Order> orderList){
        //List<Order> tableClicked = new ArrayList<Order>();
        List<Order> starterList = new ArrayList<>();
        List<Order> mainCourseList = new ArrayList<>();
        List<Order> dessertList = new ArrayList<>();
        for(int i = 0; i<orderList.size();++i){
            if(orderList.get(i).getTable().equals(table)){
                System.out.println("Bord" + table);
                switch(orderList.get(i).getType()){
                    case "Varmrätt":
                        mainCourseList.add(orderList.get(i));
                        break;
                    case "Förrätt":
                        starterList.add(orderList.get(i));
                        break;
                    case "Dessert":
                        dessertList.add(orderList.get(i));
                        break;
                }
            }
        }

        if(!orderList.isEmpty()){
            //tableOrderView = findViewById(R.id.tableOrders);
            TextView tableLabel = findViewById(R.id.tableText2);
            tableLabel.setText("Bord: " + table);

            starterView = findViewById(R.id.starterOrders);
            starterOrderAdapter = new OrderAdapter(starterList);
            starterView.setLayoutManager(new LinearLayoutManager(this));
            starterView.setAdapter(starterOrderAdapter);
            mainCourseView = findViewById(R.id.mainCourseOrders);
            mainCourseOrderAdapter = new OrderAdapter(mainCourseList);
            mainCourseView.setLayoutManager(new LinearLayoutManager(this));
            mainCourseView.setAdapter(mainCourseOrderAdapter);
            dessertView = findViewById(R.id.dessertOrders);
            dessertOrderAdapter = new OrderAdapter(dessertList);
            dessertView.setLayoutManager(new LinearLayoutManager(this));
            dessertView.setAdapter(dessertOrderAdapter);


        }

    }




}
