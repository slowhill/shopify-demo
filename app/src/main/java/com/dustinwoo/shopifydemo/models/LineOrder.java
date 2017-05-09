package com.dustinwoo.shopifydemo.models;

/**
 * Created by dustin on 2017-05-08.
 */

public class LineOrder {

    private int mId;
    private int mVariantId;
    private int mProductId;

    private String mTitle;
    private String mVariantTitle;
    private String mProductName;

    private int mQuantity;
    private int mFulfillableQuantity;
    private String mPrice;
    private String mDiscountApplied;

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
