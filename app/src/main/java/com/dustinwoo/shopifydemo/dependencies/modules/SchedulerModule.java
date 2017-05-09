package com.dustinwoo.shopifydemo.dependencies.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dustin on 2017-05-08.
 */

@Module
public class SchedulerModule {

    @Provides
    @Named("io")
    public Scheduler providesIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named("ui")
    public Scheduler providesUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
