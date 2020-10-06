package com.daiwei.bean;

public class MyRunnableImpl implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "Runable");
    }
}
