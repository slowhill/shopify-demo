package com.dustinwoo.shopifydemo.network;

import com.dustinwoo.shopifydemo.sales.models.OrderResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dustin on 2017-05-08.
 */

public interface OrderService {

    @GET("admin/orders.json")
    Observable<OrderResponse> orderList(@Query("page") int pageNum, @Query("access_token") String token);
}
