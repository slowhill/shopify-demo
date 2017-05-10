package com.dustinwoo.shopifydemo.dependencies.modules;

import com.dustinwoo.shopifydemo.contracts.BasePresenter;
import com.dustinwoo.shopifydemo.dependencies.annotations.ActivityScope;
import com.dustinwoo.shopifydemo.sales.OrdersManager;
import com.dustinwoo.shopifydemo.sales.ViewSalesContract;
import com.dustinwoo.shopifydemo.sales.ViewSalesPresenter;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by dustin on 2017-05-09.
 */

@Module
public class PresenterModule {

    private Map<Class, BasePresenter> mPresenterMap = new HashMap<>();

    @Provides
    @ActivityScope
    ViewSalesContract.Presenter providesViewSalesPresenter(@Named("ui") Scheduler uiScheduler, OrdersManager ordersManager) {
        ViewSalesContract.Presenter presenter = (ViewSalesContract.Presenter) mPresenterMap.get(ViewSalesContract.Presenter.class);
        if (presenter == null) {
            presenter = new ViewSalesPresenter(uiScheduler, ordersManager);
            mPresenterMap.put(ViewSalesContract.Presenter.class, presenter);
        }

        return presenter;
    }
}
