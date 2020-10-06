package com.daiwei.reference;

/**
 *  ThreadLocal
 *  1.一个对象一个ThreadLocal
 *  2.线程中有一个ThreadLocalMap类型的对象
 *  3.t1强引用指向 ThreadLocal , map中的key弱引用指向ThreadLocal, t1置空,gc后,map中的key被回收
 *  4.key回收，但是value没被回收，会产生内存泄漏，应该t1.remove() 不应该直接置空
 */
public class ThreadLocalTest {

    static ThreadLocal<Person> t1 = new ThreadLocal<>();
    static ThreadLocal<Person> t2 = new ThreadLocal<>();
    static ThreadLocal<Person> t3 = new ThreadLocal<>();
    static Person p;
    public static void main(String[] args) {

        new Thread(() -> {
            t1.set(new Person("daiwei"));
//            t1 = null;
            t1.remove();
            System.gc();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t2.set(new Person("daiwei2"));
            p = new Person("daiwei");
            System.out.println(t1.get());
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println(t1.get());
            System.out.println(p);
        }).start();

    }
}
