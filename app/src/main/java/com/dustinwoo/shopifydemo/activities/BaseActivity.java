package com.dustinwoo.shopifydemo.activities;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dustinwoo.shopifydemo.ShopifyDemoApplication;
import com.dustinwoo.shopifydemo.dependencies.components.ApplicationComponent;
import com.dustinwoo.shopifydemo.dependencies.components.PresenterComponent;
import com.dustinwoo.shopifydemo.dependencies.modules.PresenterModule;

public abstract class BaseActivity extends AppCompatActivity {

    @LayoutRes
    protected abstract int getLayoutResId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((ShopifyDemoApplication) getApplication()).getApplicationComponent();
    }

    protected PresenterComponent getPresenterComponent() {
        return getApplicationComponent()
                .newPresenterCompoonentBuilder()
                .presenterModule(new PresenterModule())
                .build();
    }
}
