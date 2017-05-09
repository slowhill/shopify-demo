package com.dustinwoo.shopifydemo.sales.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dustin on 2017-05-08.
 */

public class LineOrder {

    @SerializedName("id")
    private long mId;
    @SerializedName("variant_id")
    private long mVariantId;
    @SerializedName("product_id")
    private long mProductId;

    @SerializedName("title")
    private String mTitle;
    @SerializedName("variant_title")
    private String mVariantTitle;
    @SerializedName("name")
    private String mProductName;

    @SerializedName("quantity")
    private int mQuantity;
    @SerializedName("fulfillable_quantity")
    private int mFulfillableQuantity;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("total_discount")
    private String mDiscountApplied;

    @SerializedName("product_exists")
    private boolean mProductExists;

    public LineOrder(long id, long variantId, long productId,
                     String title, String variantTitle, String productName,
                     int quantity, int fulfillableQuantity,
                     String price, String discountApplied, boolean productExists) {
        mId = id;
        mVariantId = variantId;
        mProductId = productId;
        mTitle = title;
        mVariantTitle = variantTitle;
        mProductName = productName;
        mQuantity = quantity;
        mFulfillableQuantity = fulfillableQuantity;
        mPrice = price;
        mDiscountApplied = discountApplied;
        mProductExists = productExists;
    }

    public long getId() {
        return mId;
    }

    public long getVariantId() {
        return mVariantId;
    }

    public long getProductId() {
        return mProductId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getVariantTitle() {
        return mVariantTitle;
    }

    public String getProductName() {
        return mProductName;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public int getFulfillableQuantity() {
        return mFulfillableQuantity;
    }

    public Double getPrice() {
        return Double.valueOf(mPrice);
    }

    public Double getDiscountApplied() {
        return Double.valueOf(mDiscountApplied);
    }

    public Double getDiscountedPrice() {
        return getPrice() - getDiscountApplied();
    }

    public boolean isProductExists() {
        return mProductExists;
    }

    @Override
    public String toString() {
        return "LineOrder{" +
                "mId=" + mId +
                ", mVariantId=" + mVariantId +
                ", mProductId=" + mProductId +
                ", mTitle='" + mTitle + '\'' +
                ", mVariantTitle='" + mVariantTitle + '\'' +
                ", mProductName='" + mProductName + '\'' +
                ", mQuantity=" + mQuantity +
                ", mFulfillableQuantity=" + mFulfillableQuantity +
                ", mPrice='" + mPrice + '\'' +
                ", mDiscountApplied='" + mDiscountApplied + '\'' +
                ", mProductExists=" + mProductExists +
                '}';
    }
}
