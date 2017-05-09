package com.dustinwoo.shopifydemo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dustin on 2017-05-08.
 */

public class LineOrder {

    @SerializedName("id")
    private int mId;
    @SerializedName("variant_id")
    private int mVariantId;
    @SerializedName("product_id")
    private int mProductId;

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

    public LineOrder(int id, int variantId, int productId,
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

    public int getId() {
        return mId;
    }

    public int getVariantId() {
        return mVariantId;
    }

    public int getProductId() {
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
}
