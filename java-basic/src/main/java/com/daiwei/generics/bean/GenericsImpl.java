package com.daiwei.generics.bean;

public class GenericsImpl<E> implements GenericsInterface<E> {

    @Override
    public E test(E s) {
        return s;
    }
}
