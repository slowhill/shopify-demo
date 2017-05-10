package com.dustinwoo.shopifydemo.dependencies.components;

import com.dustinwoo.shopifydemo.dependencies.modules.ApplicationModule;
import com.dustinwoo.shopifydemo.dependencies.modules.NetworkModule;
import com.dustinwoo.shopifydemo.dependencies.modules.SchedulerModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dustin on 2017-05-08.
 */
@Singleton
@Component(modules =  {
        ApplicationModule.class,
        NetworkModule.class,
        SchedulerModule.class })
public interface ApplicationComponent {

    PresenterComponent.Builder newPresenterCompoonentBuilder();
}
