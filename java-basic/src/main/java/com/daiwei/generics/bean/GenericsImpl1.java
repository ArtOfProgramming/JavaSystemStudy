package com.daiwei.generics.bean;

public class GenericsImpl1 implements GenericsInterface<String> {

    @Override
    public String test(String s) {
        return "ab";
    }

    public <Q> Q test1(Q q) {
        return q;
    }
}
