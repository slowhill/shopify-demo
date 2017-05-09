package com.dustinwoo.shopifydemo;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dustinwoo.shopifydemo.dependencies.components.ApplicationComponent;

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
}
