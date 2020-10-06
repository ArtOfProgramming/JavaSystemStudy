package com.daiwei.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class CasTest {

    long count1 = 0;
    AtomicLong count = new AtomicLong(0);
    LongAdder count2 = new LongAdder(); // 分段锁

    public void m() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
            count1++;
        }
    }

    public static void main(String[] args) {
        CasTest t = new CasTest();
        for (int i = 0; i < 10; i++) {
            new Thread(t::m, "thread" + i).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.count);
        System.out.println(t.count1);
    }
}
