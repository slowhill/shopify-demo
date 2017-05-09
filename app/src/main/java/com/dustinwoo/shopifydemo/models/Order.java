package com.dustinwoo.shopifydemo.models;

import java.util.List;

/**
 * Created by dustin on 2017-05-08.
 */

public class Order {

    private int mId;
    private int mOrderNumber;
    private String mToken;

    private String mTotalPrice;
    private String mSubtotalPrice;
    private String mUsdTotalPrice;
    private String mCurrency;

    private List<LineOrder> mLineItems;

    public Order(int id, int orderNumber, String token, String totalPrice, String subtotalPrice, String usdTotalPrice, String currency, List<LineOrder> lineItems) {
        mId = id;
        mOrderNumber = orderNumber;
        mToken = token;
        mTotalPrice = totalPrice;
        mSubtotalPrice = subtotalPrice;
        mUsdTotalPrice = usdTotalPrice;
        mCurrency = currency;
        mLineItems = lineItems;
    }

    public int getId() {
        return mId;
    }

    public int getOrderNumber() {
        return mOrderNumber;
    }

    public String getToken() {
        return mToken;
    }

    public Double getTotalPrice() {
        return Double.valueOf(mTotalPrice);
    }

    public Double getSubtotalPrice() {
        return Double.valueOf(mSubtotalPrice);
    }

    public Double getUsdTotalPrice() {
        return Double.valueOf(mUsdTotalPrice);
    }

    public String getCurrency() {
        return mCurrency;
    }

    public List<LineOrder> getLineItems() {
        return mLineItems;
    }
}

