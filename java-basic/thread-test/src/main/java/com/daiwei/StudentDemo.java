package com.daiwei;

import com.daiwei.bean.SaleTicketRunnableImpl;

public class StudentDemo {
    public static void main(String[] args) {
//        //创建资源
//        Student s = new Student();
//
//        //设置和获取的类
//        SetThread st = new SetThread(s);
//        GetThread gt = new GetThread(s);
//
//        //线程类
//        Thread t1 = new Thread(st);
//        Thread t2 = new Thread(gt);
//
//        //启动线程
//        t1.start();
//        t2.start();

//        for (int i = 0; i < 100; i++) {
//            Thread t3 = new Thread(new TestWait());
//            t3.start();
//        }
//        for (int i = 0; i < 100; i++) {
//            Thread t4 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(  1 * 1000);
//                        System.out.println("1s正在运行");
//                    } catch (Exception e) {
//
//                    }
//                }
//            });
//            t4.start();
//        }
//        while (true) {
//
//        }
//        new MyThread().start();
//        new MyThread().start();
//        new MyThread().start();
//
//        new Thread(new MyRunableImpl()).start();
//        new Thread(new MyRunableImpl()).start();
//        new Thread(new MyRunableImpl()).start();

//        FutureTask<String> futureTask = new FutureTask<String>(new MyCallableImpl<>());
//        new Thread(futureTask).start();
//        try {
//            System.out.println(futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        Runnable t = new SaleTicketRunnableImpl();
//        new Thread(t).start();
//        new Thread(t).start();
//        new Thread(t).start();
        new Thread(new SaleTicketRunnableImpl()).start();
        new Thread(new SaleTicketRunnableImpl()).start();
        new Thread(new SaleTicketRunnableImpl()).start();
    }
}

class TestWait implements Runnable {

    @Override
    public void run() {
        System.out.println("正在运行");
        try {
//            wait(30 * 1000);
            Thread.sleep(  30 * 1000);
            System.out.println("30s正在运行");
        } catch (Exception e) {

        }
        try {
//            wait(30 * 1000);
            Thread.sleep(60 * 1000);
            System.out.println("60s正在运行");
        } catch (Exception e) {

        }
    }
}

//创建对象的时候实现线程安全
class Student {
    private String name;
    private int age;
    private boolean flag; // 默认情况是没有数据，如果是true，说明有数据

    public synchronized void set(String name, int age) {
        // 如果有数据，就等待
        if (this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 设置数据
        this.name = name;
        this.age = age;

        // 修改标记
        this.flag = true;
        this.notify();
    }

    public synchronized void get() {
        // 如果没有数据，就等待
        if (!this.flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 获取数据
        System.out.println(this.name + "---" + this.age);

        // 修改标记
        this.flag = false;
        this.notify();
    }
}

class GetThread implements Runnable {
    private Student s;

    public GetThread(Student s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            s.get();
        }
    }
}

class SetThread implements Runnable {

    private Student s;
    private int x = 0;

    public SetThread(Student s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            if (x % 2 == 0) {
                s.set("林青霞", 27);
            } else {
                s.set("刘意", 30);
            }
            x++;
        }
    }
}