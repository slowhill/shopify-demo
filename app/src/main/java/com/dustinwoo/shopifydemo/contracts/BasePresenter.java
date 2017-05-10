package com.dustinwoo.shopifydemo.contracts;

import android.support.annotation.NonNull;

/**
 * Created by dustin on 2017-05-08.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(@NonNull T view);
    void detachView();
}
