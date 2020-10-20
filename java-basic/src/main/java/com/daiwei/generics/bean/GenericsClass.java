package com.daiwei.generics.bean;

/**
 * 泛型类
 * @param <E>
 */
public class GenericsClass<E> {

    private E e;

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }
}
