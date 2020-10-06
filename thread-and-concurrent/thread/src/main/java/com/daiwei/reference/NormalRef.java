package com.daiwei.reference;

import java.io.IOException;

public class NormalRef {

    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc();
        System.out.println(m);

        System.in.read(); // 阻塞
    }
}

class M {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }
}
