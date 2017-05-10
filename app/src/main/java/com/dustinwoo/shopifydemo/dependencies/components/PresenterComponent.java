package com.dustinwoo.shopifydemo.dependencies.components;


import com.dustinwoo.shopifydemo.dependencies.annotations.ActivityScope;
import com.dustinwoo.shopifydemo.dependencies.modules.PresenterModule;
import com.dustinwoo.shopifydemo.sales.ViewSalesActivity;

import dagger.Subcomponent;

/**
 * Created by dustin on 2017-05-09.
 */

@ActivityScope
@Subcomponent(modules = {
        PresenterModule.class })
public interface PresenterComponent {

    void inject(ViewSalesActivity activity);

    @Subcomponent.Builder
    public interface Builder {
        Builder presenterModule(PresenterModule module);
        PresenterComponent build();
    }
}
