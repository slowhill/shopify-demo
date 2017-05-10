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

    //============================================================
    // Generated Code
    //============================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineOrder lineOrder = (LineOrder) o;

        if (mId != lineOrder.mId) return false;
        if (mVariantId != lineOrder.mVariantId) return false;
        if (mProductId != lineOrder.mProductId) return false;
        if (mQuantity != lineOrder.mQuantity) return false;
        if (mFulfillableQuantity != lineOrder.mFulfillableQuantity) return false;
        if (mProductExists != lineOrder.mProductExists) return false;
        if (mTitle != null ? !mTitle.equals(lineOrder.mTitle) : lineOrder.mTitle != null)
            return false;
        if (mVariantTitle != null ? !mVariantTitle.equals(lineOrder.mVariantTitle) : lineOrder.mVariantTitle != null)
            return false;
        if (mProductName != null ? !mProductName.equals(lineOrder.mProductName) : lineOrder.mProductName != null)
            return false;
        if (mPrice != null ? !mPrice.equals(lineOrder.mPrice) : lineOrder.mPrice != null)
            return false;
        return mDiscountApplied != null ? mDiscountApplied.equals(lineOrder.mDiscountApplied) : lineOrder.mDiscountApplied == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (int) (mVariantId ^ (mVariantId >>> 32));
        result = 31 * result + (int) (mProductId ^ (mProductId >>> 32));
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mVariantTitle != null ? mVariantTitle.hashCode() : 0);
        result = 31 * result + (mProductName != null ? mProductName.hashCode() : 0);
        result = 31 * result + mQuantity;
        result = 31 * result + mFulfillableQuantity;
        result = 31 * result + (mPrice != null ? mPrice.hashCode() : 0);
        result = 31 * result + (mDiscountApplied != null ? mDiscountApplied.hashCode() : 0);
        result = 31 * result + (mProductExists ? 1 : 0);
        return result;
    }
}
