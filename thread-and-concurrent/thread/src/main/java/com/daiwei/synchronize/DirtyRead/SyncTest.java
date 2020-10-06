package com.daiwei.synchronize.DirtyRead;

/**
 * 同步锁测试
 * 同步方法 和 非同步方法 （脏读问题）
 * 可重入
 * 异常不捕获会释放锁（数据不一致问题）
 * 锁升级 偏向锁 自旋锁 系统锁 执行时间长，线程多 用系统锁
 */
public class SyncTest {

    private String name;
    private double balance;

    private static Object o = new Object();

    public synchronized void m1(String name, double balance) {
        try {
            this.name = name;
            Thread.sleep(2000);
            this.balance = balance;
            this.m2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2() {
        System.out.println(this);

    }

    public static void main(String[] args) {
        SyncTest t = new SyncTest();
        new Thread(() -> t.m1("daiwei", 1000), "m1").start();
//        new Thread(t::m2, "m2").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.m2();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.m2();
    }

    @Override
    public String toString() {
        return "SyncTest{" +
            "name='" + name + '\'' +
            ", balance=" + balance +
            '}';
    }
}
