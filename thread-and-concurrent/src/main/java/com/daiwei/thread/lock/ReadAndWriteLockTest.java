package com.daiwei.thread.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReadAndWriteLockTest {
    ReentrantLock reentrantLock = new ReentrantLock();
    CountDownLatch countDownLatch = new CountDownLatch(20);
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    Lock readLock = reentrantReadWriteLock.readLock();
    Lock writeLock = reentrantReadWriteLock.writeLock();


    public void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over");
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void write(Lock lock, int value) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("write over");
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadAndWriteLockTest t = new ReadAndWriteLockTest();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 18; i++) {
            new Thread(() -> t.read(t.readLock)).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> t.write(t.writeLock, 1)).start();
        }
        try {
            t.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
