package com.daiwei.boxandunbox;

public class BoxAndUnbox {

    public static void main(String[] args) {
        int a = 10;
        int a2 = 200;
        Integer b = new Integer(10);
        Integer c = new Integer(10);
        System.out.println(a == b); // true 自动装拆箱
        System.out.println(b == c); // false
//        Integer b1 = Integer.valueOf(a);
//        int a1 = b.intValue();
        Integer d = 100;
        Integer e = 100;
        Integer f = 200;
        Integer g = 200;
        System.out.println(d == e); // true
        System.out.println(f == g); // false
        System.out.println(a2 == g); // true
    }
}
