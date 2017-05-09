package com.dustinwoo.shopifydemo.dependencies.components;

import com.dustinwoo.shopifydemo.ShopifyDemoApplication;
import com.dustinwoo.shopifydemo.dependencies.modules.ApplicationModule;

import dagger.Component;

/**
 * Created by dustin on 2017-05-08.
 */

@Component(modules =  { ApplicationModule.class })
public interface ApplicationComponent {
    void inject(ShopifyDemoApplication application);
}
