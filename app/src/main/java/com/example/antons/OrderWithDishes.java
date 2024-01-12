package com.example.antons;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * OrderWithDishes class
 * Class that maps to the API-endpoint for orders with all dishes in that order included
 */
public class OrderWithDishes {
    @SerializedName("bestallning")
    private Order orderInfo;
    @SerializedName("rattInstanser")
    private List<DishInstance> selectedList;

    public Order getOrderInfo() {
        return orderInfo;
    }

    public boolean isDone(){
        return (this.orderInfo.isStartDone() && this.orderInfo.isMainDone() && this.orderInfo.isDessertDone());
    }

    public void setOrderInfo(Order orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<DishInstance> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<DishInstance> selectedList) {
        this.selectedList = selectedList;
    }
}
