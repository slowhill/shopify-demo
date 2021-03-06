package com.dustinwoo.shopifydemo;

import android.app.Application;

import com.dustinwoo.shopifydemo.dependencies.components.ApplicationComponent;
import com.dustinwoo.shopifydemo.dependencies.components.DaggerApplicationComponent;
import com.dustinwoo.shopifydemo.dependencies.modules.ApplicationModule;

/**
 * Created by dustin on 2017-05-08.
 */

public class ShopifyDemoApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = buildApplicationComponent();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    //============================================================
    // Private Methods
    //============================================================

    private ApplicationComponent buildApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
