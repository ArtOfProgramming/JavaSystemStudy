package com.daiwei.volatileT;

/**
 * 指令重排序证明
 */
public class ReSortProve {

    public static void main(String[] args) {
        m();
    }

    static int x,y,a,b;
    static int count = 0;

    public static void m() {
        for (;;) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            count++;
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (x == 0 && y == 0) {
                System.out.println("第" + count + "次出现");
                break;
            }
        }
    }
}
