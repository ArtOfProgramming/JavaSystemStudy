package com.daiwei.bean;

import java.util.concurrent.Callable;

public class MyCallableImpl<V> implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "代维真棒";
    }
}
