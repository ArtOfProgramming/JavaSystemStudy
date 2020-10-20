package com.daiwei.string;

public class StringTest {

    public static void main(String[] args) {
        String str = "abc";
        String str1 = new String("abc");
        System.out.println(str == str1); // false
        str1 = str1.intern();
        System.out.println(str == str1); // true
        System.out.println(str.equals(str1));
        System.out.println(str.charAt(0));
        System.out.println(str.concat("efg"));
        System.out.println(str.indexOf("a"));
        // 左闭右开
        System.out.println(str.substring(0,1));

        String t1 = "abc";
        String t2 = "def";
        String t3 = "abc" + "def";
        String t4 = "abcdef";
        String t5 = t1 + t2;
        System.out.println(t3 == t4);
        System.out.println(t3 == t5);
    }
}
