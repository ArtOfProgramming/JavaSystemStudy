package com.daiwei.bean;

public class MyThread extends Thread {

    public MyThread(Runnable target) {
        super(target);
    }

    public MyThread() {
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
