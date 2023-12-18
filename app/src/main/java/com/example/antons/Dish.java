package com.example.antons;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Dish {

    public static class Type {
        @SerializedName("id")
        private int id;
        @SerializedName("namn")
        private String name;

        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        @NonNull
        @Override
        public String toString() {
            return "Type{" +
                    "id='" + id + '\'' +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    @SerializedName("beskrivning")
    private String description;
    @SerializedName("id")
    private int id;
    @SerializedName("pris")
    private int price;
    @SerializedName("titel")
    private String title;
    @SerializedName("typ")
    private Type type;
    @SerializedName("vald")
    private boolean picked;

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public boolean getPicked() {
        return picked;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    @NonNull
    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", desc='" + description + '\'' +
                ", title=" + title + '\'' +
                ", price=" + price + '\'' +
                ", type=" + type + '\'' +
                ", picked=" + picked + '\'' +
                '}';
    }
}
