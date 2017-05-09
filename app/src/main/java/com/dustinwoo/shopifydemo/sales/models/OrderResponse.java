package com.dustinwoo.shopifydemo.sales.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dustin on 2017-05-09.
 */

public class OrderResponse {

    @SerializedName("orders")
    private List<Order> mOrderList;

    public OrderResponse(List<Order> orderList) {
        mOrderList = orderList;
    }

    public List<Order> getOrderList() {
        return mOrderList;
    }
}
