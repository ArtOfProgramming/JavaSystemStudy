package com.daiwei.thread.synchronize.concurrent;

/**
 * 线程同步问题
 * 锁 细化 粗化
 * 锁对象final
 */

public class ConcurrentTest {

    private static int count = 0;

    private static final Object o = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }
        });

        try {
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
