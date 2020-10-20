package com.daiwei.string;

import java.util.ArrayList;

public class StringT {

    public static void main(String[] args) {
        String a = "ab";
        String b = "bc";
        System.out.println(a.compareTo(b));
        ArrayList<T> list = new ArrayList<>();
        list.add(new T(a));
        list.add(new T(b));
        System.out.println(list.toString());
    }
}

class T implements Comparable<T> {
    String name;

    public T(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(T o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "T{" +
            "name='" + name + '\'' +
            '}';
    }
}