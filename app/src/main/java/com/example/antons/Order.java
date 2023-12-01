package com.example.antons;

public class Order {
    private String table;
    private String order;

    private String time;

    public Order(String table, String order, String time){
        this.table = table;
        this.order = order;
        this.time = time;
    }

    public String getTable(){
        return table;
    }

    public String getOrder(){
        return order;
    }

    public String getTime() {return time;}


}
