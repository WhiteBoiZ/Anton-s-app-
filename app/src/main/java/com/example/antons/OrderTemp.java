package com.example.antons;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderTemp {
    @SerializedName("bestallning")
    private OrderTest orderInfo;
    @SerializedName("rattInstanser")
    private List<OrderApi> selectedList;

    public OrderTest getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderTest orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderApi> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<OrderApi> selectedList) {
        this.selectedList = selectedList;
    }
}
