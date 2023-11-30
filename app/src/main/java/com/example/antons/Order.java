package com.example.antons;

public class Order {
    private String table;
    private String order;

    public Order(String table, String order){
        this.table = table;
        this.order = order;
    }

    public String getTable(){
        return table;
    }

    public String getOrder(){
        return order;
    }



}
