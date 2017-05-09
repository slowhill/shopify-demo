package com.dustinwoo.shopifydemo.dependencies.modules;

import android.content.Context;

import com.dustinwoo.shopifydemo.ShopifyDemoApplication;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by dustin on 2017-05-08.
 */

@Module
public class ApplicationModule {

    private ShopifyDemoApplication mApplication;

    public ApplicationModule(ShopifyDemoApplication application) {
        mApplication = application;
    }

    @Provides
    public Context providesApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient() {
        return new OkHttpClient();
    }
}
