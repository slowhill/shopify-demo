package com.dustinwoo.shopifydemo.utils;

import com.google.common.base.Defaults;
import com.google.common.base.Optional;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dustin on 2017-05-09.
 */

public class NoopInvocationHandler<T> implements InvocationHandler {

    Optional<T> mSubject;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (mSubject.isPresent()) {
            try {
                return method.invoke(mSubject.get(), args);
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }

        return Defaults.defaultValue(method.getReturnType());
    }

    public void setTarget(Optional<T> target) {
        mSubject = target;
    }

    @SuppressWarnings("unchecked")
    public T newProxyInstance(Class clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
}
