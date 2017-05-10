package com.dustinwoo.shopifydemo.dependencies.modules;

import android.content.Context;

import com.dustinwoo.shopifydemo.ShopifyDemoApplication;
import com.dustinwoo.shopifydemo.dependencies.components.PresenterComponent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dustin on 2017-05-08.
 */

@Module(subcomponents = {
        PresenterComponent.class})
public class ApplicationModule {

    private ShopifyDemoApplication mApplication;

    public ApplicationModule(ShopifyDemoApplication application) {
        mApplication = application;
    }

    @Provides
    public Context providesApplicationContext() {
        return mApplication;
    }
}
