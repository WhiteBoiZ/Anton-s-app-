package com.example.antons;

import java.util.List;

public class TableOrder {
    private List<OrderApi> orderList;
    private int tableID;

    private String time;

    public TableOrder(List<OrderApi> orderList, int tableID, String time) {
        this.orderList = orderList;
        this.tableID = tableID;
        this.time = time;
    }


    public String getTime() {
        return this.time;
    }

    public int getTable(){
        return this.tableID;
    }

    public List<OrderApi> getOrderList(){
        return this.orderList;
    }

    public Boolean isEmpty(){
        return orderList.isEmpty();
    }
}

