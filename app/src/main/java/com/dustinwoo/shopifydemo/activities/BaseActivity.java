package com.dustinwoo.shopifydemo.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.dustinwoo.shopifydemo.ShopifyDemoApplication;
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

    protected PresenterComponent getPresenterComponent() {
        return ((ShopifyDemoApplication) getApplication()).getApplicationComponent()
                .newPresenterCompoonentBuilder()
                .presenterModule(new PresenterModule())
                .build();
    }
}
