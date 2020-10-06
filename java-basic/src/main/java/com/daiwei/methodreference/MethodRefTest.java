package com.daiwei.methodreference;

import com.daiwei.methodreference.functionInterface.ArrayBuilder;
import com.daiwei.methodreference.functionInterface.MethodRefClass;
import com.daiwei.methodreference.functionInterface.PrintString;

public class MethodRefTest {

    public static void main(String[] args) {
        pringString("daiwei", MethodRefClass::print);
        int[] a = arrayBuilder(10, int[]::new);
        System.out.println(a.length);
    }

    public static void pringString(String name, PrintString p) {
        p.print(name);
    }

    public static int[] arrayBuilder(int len, ArrayBuilder builder) {
        return builder.arrayBuilder(len);
    }
}
