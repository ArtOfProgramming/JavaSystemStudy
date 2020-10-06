package com.daiwei.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁测试
 * lock(), tryLock(), lockInterruptibly()
 * 公平锁 ReentrantLock(true)
 */
public class ReentrantLockTest {

    Lock lock = new ReentrantLock();
    boolean locked = false;

    public void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

    }
}
