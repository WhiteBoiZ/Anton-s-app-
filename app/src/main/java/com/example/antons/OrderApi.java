package com.example.antons;

import com.google.gson.annotations.SerializedName;

public class OrderApi {

    private static class Tag {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Tag{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    @SerializedName("alacarte")
    private Dish dish;

    @SerializedName("bestallning")
    private OrderTest order;

    @SerializedName("id")
    private int id;

    @SerializedName("rattPreferenser")
    private String dishPreference;

    @SerializedName("tag")
    private Tag tag;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public OrderTest getOrder() {
        return order;
    }

    public void setOrder(OrderTest order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishPreference() {
        return dishPreference;
    }

    public void setDishPreference(String dishPreference) {
        this.dishPreference = dishPreference;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "OrderApi{" +
                "dish=" + dish +
                ", order=" + order +
                ", id=" + id +
                ", dishPreference='" + dishPreference + '\'' +
                ", tag=" + tag +
                '}';
    }



}
