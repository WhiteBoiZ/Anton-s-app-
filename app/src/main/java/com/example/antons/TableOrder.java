package com.example.antons;

import java.util.List;

public class TableOrder {
    private List<Order> orderList;
    
    private String table;

    private String time;

    public TableOrder(List<Order> orderList, String table, String time) {
        this.orderList = orderList;
        this.table = table;
        this.time = time;
    }


    public String getTime() {
        return this.time;
    }

    public String getTable(){
        return this.table;
    }

    public List<Order> getOrderList(){
        return this.orderList;
    }

    public Boolean isEmpty(){
        return orderList.isEmpty();
    }
}

