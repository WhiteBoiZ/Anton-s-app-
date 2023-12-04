package com.example.antons;

public class Order {
    private String table;
    private String order;

    private String time;

    private String type;

    public Order(String table, String order, String time, String type){
        this.table = table;
        this.order = order;
        this.time = time;
        this.type = type;
    }

    public String getTable(){
        return table;
    }

    public String getOrder(){
        return order;
    }

    public String getTime() {return time;}

    public String getType() {return type;}


}
