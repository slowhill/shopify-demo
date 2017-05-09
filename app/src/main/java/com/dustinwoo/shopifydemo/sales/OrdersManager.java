package com.dustinwoo.shopifydemo.sales;

import com.dustinwoo.shopifydemo.exceptions.EmptyResponseException;
import com.dustinwoo.shopifydemo.network.OrderService;
import com.dustinwoo.shopifydemo.sales.models.Order;
import com.dustinwoo.shopifydemo.sales.models.OrderResponse;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

/**
 * Created by dustin on 2017-05-09.
 */

public class OrdersManager {

    private static final String ACCESS_TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6";

    private OrderService mOrderService;
    private Scheduler mIoScheduler;
    private int mPageNum;

    @Inject
    public OrdersManager(OrderService orderService, @Named("io") Scheduler ioScheduler) {
        this(orderService, ioScheduler, 1);
    }

    public OrdersManager(OrderService orderService, Scheduler ioScheduler, int pageNum) {
        mOrderService = orderService;
        mIoScheduler = ioScheduler;
        mPageNum = pageNum;
    }

    public Observable<Order> fetchOrders() {
        return fetchOrders(mPageNum);
    }

    public Observable<Order> fetchOrders(final int pageNum) {
        return mOrderService.orderList(pageNum, ACCESS_TOKEN)
                .flatMap(new Function<OrderResponse, ObservableSource<Order>>() {
                    @Override
                    public ObservableSource<Order> apply(@NonNull OrderResponse orderResponse) throws Exception {
                        if (orderResponse.getOrderList().isEmpty()) {
                            throw new EmptyResponseException();
                        }

                        return Observable.fromIterable(orderResponse.getOrderList());
                    }
                }).doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mPageNum = pageNum;
                        mPageNum += 1; //Increment to fetch next list
                    }
                }).subscribeOn(mIoScheduler);
    }
}
