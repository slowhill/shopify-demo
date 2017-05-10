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

    //============================================================
    // Generated Code
    //============================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (mId != order.mId) return false;
        if (mOrderNumber != order.mOrderNumber) return false;
        if (mToken != null ? !mToken.equals(order.mToken) : order.mToken != null) return false;
        if (mTotalPrice != null ? !mTotalPrice.equals(order.mTotalPrice) : order.mTotalPrice != null)
            return false;
        if (mSubtotalPrice != null ? !mSubtotalPrice.equals(order.mSubtotalPrice) : order.mSubtotalPrice != null)
            return false;
        if (mUsdTotalPrice != null ? !mUsdTotalPrice.equals(order.mUsdTotalPrice) : order.mUsdTotalPrice != null)
            return false;
        if (mCurrency != null ? !mCurrency.equals(order.mCurrency) : order.mCurrency != null)
            return false;
        return mLineItems != null ? mLineItems.equals(order.mLineItems) : order.mLineItems == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (int) (mOrderNumber ^ (mOrderNumber >>> 32));
        result = 31 * result + (mToken != null ? mToken.hashCode() : 0);
        result = 31 * result + (mTotalPrice != null ? mTotalPrice.hashCode() : 0);
        result = 31 * result + (mSubtotalPrice != null ? mSubtotalPrice.hashCode() : 0);
        result = 31 * result + (mUsdTotalPrice != null ? mUsdTotalPrice.hashCode() : 0);
        result = 31 * result + (mCurrency != null ? mCurrency.hashCode() : 0);
        result = 31 * result + (mLineItems != null ? mLineItems.hashCode() : 0);
        return result;
    }
}

