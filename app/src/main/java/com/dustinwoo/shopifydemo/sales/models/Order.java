package com.dustinwoo.shopifydemo.sales.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dustin on 2017-05-08.
 */

public class Order {

    @SerializedName("id")
    private long mId;
    @SerializedName("order_number")
    private long  mOrderNumber;
    @SerializedName("token")
    private String mToken;

    @SerializedName("total_price")
    private String mTotalPrice;
    @SerializedName("subtotal_price")
    private String mSubtotalPrice;
    @SerializedName("total_price_usd")
    private String mUsdTotalPrice;
    @SerializedName("currency")
    private String mCurrency;

    @SerializedName("line_items")
    private List<LineOrder> mLineItems;

    public Order(long id, long orderNumber, String token, String totalPrice, String subtotalPrice, String usdTotalPrice, String currency, List<LineOrder> lineItems) {
        mId = id;
        mOrderNumber = orderNumber;
        mToken = token;
        mTotalPrice = totalPrice;
        mSubtotalPrice = subtotalPrice;
        mUsdTotalPrice = usdTotalPrice;
        mCurrency = currency;
        mLineItems = lineItems;
    }

    public long getId() {
        return mId;
    }

    public long getOrderNumber() {
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
