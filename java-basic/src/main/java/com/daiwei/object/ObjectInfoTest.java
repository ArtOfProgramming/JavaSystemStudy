package com.daiwei.object;

import org.openjdk.jol.info.ClassLayout;

public class ObjectInfoTest {

    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        M m = new M();
        System.out.println(ClassLayout.parseInstance(m).toPrintable());
        synchronized (m) {
            System.out.println(ClassLayout.parseInstance(m).toPrintable());
        }
    }
}

class M {
    int a;
}
