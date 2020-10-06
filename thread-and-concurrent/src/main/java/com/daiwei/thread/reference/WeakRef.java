package com.daiwei.thread.reference;

import java.lang.ref.WeakReference;

public class WeakRef {

    public static void main(String[] args) {
        WeakReference<byte[]> m = new WeakReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());
    }
}
