package com.daiwei.thread.reference;

import java.lang.ref.SoftReference;

public class SoftRef {

    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        byte[] t = new byte[1024*1024*12];
        System.out.println(m.get());
    }
}
