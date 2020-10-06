package com.daiwei;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ChildThread childThread = new ChildThread();
        childThread.start();

        new Thread(() -> System.out.println("Runable lambada")).start();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future future = executorService.submit(()-> "代维 不错哦");
        System.out.println(future.get());
    }
}

class ChildThread extends Thread {

    @Override
    public void run() {
        System.out.println("代维 你好啊");
    }
}