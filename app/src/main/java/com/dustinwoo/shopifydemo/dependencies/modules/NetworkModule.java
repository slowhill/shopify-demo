package com.dustinwoo.shopifydemo.dependencies.modules;

import com.dustinwoo.shopifydemo.network.OrderService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dustin on 2017-05-08.
 */

@Module
public class NetworkModule {

    private static final String BASE_ENDPOINT_URL = "https://shopicruit.myshopify.com";

    @Provides
    @Singleton
    public Retrofit providesRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public OrderService providesOrderService(Retrofit retrofit) {
        return retrofit.create(OrderService.class);
    }
}
