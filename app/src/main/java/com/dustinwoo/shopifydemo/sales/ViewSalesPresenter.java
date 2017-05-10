package com.dustinwoo.shopifydemo.sales;

import android.support.annotation.NonNull;

import com.dustinwoo.shopifydemo.exceptions.EmptyResponseException;
import com.dustinwoo.shopifydemo.sales.models.LineOrder;
import com.dustinwoo.shopifydemo.sales.models.Order;
import com.dustinwoo.shopifydemo.utils.NoopInvocationHandler;
import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

/**
 * Created by dustin on 2017-05-09.
 */

public class ViewSalesPresenter implements ViewSalesContract.Presenter {

    private static final long COTTON_KEYBOARD_PRODUCT_ID = 2759162243L;

    private NoopInvocationHandler<ViewSalesContract.View> mInvocationHandler = new NoopInvocationHandler<>();
    private ViewSalesContract.View mView = mInvocationHandler.newProxyInstance(ViewSalesContract.View.class);

    private Scheduler mUiScheduler;
    private OrdersManager mOrdersManager;
    private int mOrdersPageNum;

    private List<Order> mOrders;
    private double mTotalRevenue;
    private double mTotalUsdRevenue;
    private int mNumKeyboardsSold;

    @Inject
    public ViewSalesPresenter(@Named("ui") Scheduler uiScheduler, OrdersManager ordersManager) {
        mUiScheduler = uiScheduler;
        mOrdersManager = ordersManager;
        mOrdersPageNum = 1;
        mOrders = new ArrayList<>();
        mTotalRevenue = 0D;
        mTotalUsdRevenue = 0D;
        mNumKeyboardsSold = 0;
    }

    @Override
    public void attachView(@NonNull ViewSalesContract.View view) {
        mInvocationHandler.setTarget(Optional.of(view));
    }

    @Override
    public void detachView() {
        mInvocationHandler.setTarget(Optional.<ViewSalesContract.View>absent());
    }

    @Override
    public void fetchOrderInfo() {
        mOrdersManager.fetchOrders(mOrdersPageNum)
                .observeOn(mUiScheduler)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mOrdersPageNum += 1;
                    }
                })
                .subscribe(new Observer<Order>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoadingScreen(isFirstFetch());
                    }

                    @Override
                    public void onNext(@NonNull Order order) {
                        if (!mOrders.contains(order)) { //Avoid double counting
                            mOrders.add(order);
                            mTotalUsdRevenue += order.getUsdTotalPrice();
                            mTotalRevenue += order.getTotalPrice();
                            countSoldKeyboards(order);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        boolean hasFinishedPagination = e instanceof EmptyResponseException;
                        if (hasFinishedPagination) {
                            mView.enableLoadButton(false);
                        }

                        mView.showErrorToast(hasFinishedPagination);
                        mView.showErrorScreen(isFirstFetch());
                    }

                    @Override
                    public void onComplete() {
                        mView.showOrderDetails(mTotalUsdRevenue, mTotalRevenue, mNumKeyboardsSold);
                    }
                });
    }

    //============================================================
    // Private Methods
    //============================================================

    private void countSoldKeyboards(Order order) {
        for (LineOrder lineOrder : order.getLineItems()) {
            if (lineOrder.getProductId() == COTTON_KEYBOARD_PRODUCT_ID) {
                mNumKeyboardsSold += lineOrder.getQuantity();
            }
        }
    }

    private boolean isFirstFetch() {
        return mTotalUsdRevenue == 0
                && mTotalRevenue == 0
                && mNumKeyboardsSold == 0;
    }
}
