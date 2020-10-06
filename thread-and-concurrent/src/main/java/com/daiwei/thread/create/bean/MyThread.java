package com.daiwei.thread.create.bean;

public class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("代维你好-1");
    }
}
