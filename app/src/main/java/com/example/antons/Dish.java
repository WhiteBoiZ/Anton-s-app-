package com.example.antons;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Dish {

    public static class Type {
        @SerializedName("id")
        private Integer id;
        @SerializedName("namn")
        private String name;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
        public void setId(Integer id) {
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
    private Integer id;
    @SerializedName("pris")
    private Integer price;
    @SerializedName("titel")
    private String title;
    @SerializedName("typ")
    private Type type;
    @SerializedName("vald")
    private Integer picked;

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public Integer getPicked() {
        return picked;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPicked(Integer picked) {
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
