package com.daiwei.volatileT;

import java.io.IOException;

/**
 * volatile测试
 * 保证线程可见性(问题，当while循环里面有其他代码时，线程内会结束 未知原因）
 * 禁止指令重排序
 */

public class VolatileTest {

    boolean running = true;

    private void m() {
        System.out.println("m start");
        while (running) {
//            System.out.println(running);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        VolatileTest t = new VolatileTest();
        Thread t1 = new Thread(t::m, "t1");
        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
