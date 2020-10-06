package com.daiwei.stringbuilder;

public class StringBuilderTest {

    public static void main(String[] args) {
        String str = "daiwei";
        StringBuilder sb = new StringBuilder(str);
//        System.out.println(str.hashCode());
//        System.out.println(sb.toString().hashCode());
        System.out.println(str == sb.toString());
//        System.out.println(str.toString());
        Integer a = 1;
        boolean b = false;
        String c = b + "";
        System.out.println(c);
        String d = "true";
        boolean e = Boolean.parseBoolean(d);
        System.out.println(e);
    }
}
