package com.daiwei.functionalinterface;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceTest {

    public static void main(String[] args) {
        String a = "abcd";
        consumerString(a, (b) -> System.out.println(b.toUpperCase()), (b) -> System.out.println(a));
        String[] array = new String[]{"代维,18", "迪丽热巴, 18", "古力拉扎, 19", "马尔扎哈,30"};
        printFormat(array, (b) -> System.out.print("姓名:" + b.split(",")[0] + "。"),
            (b) -> System.out.println("年龄:" + b.split(",")[1] + "。"));
        System.out.println(filterArray(array, b -> b.split(",")[0].length() <= 4, b -> Integer.valueOf(b.split(",")[1].replace(" ", "")) < 20));
        String c = "123";
        System.out.println(functionString(c, d -> Integer.parseInt(c) + 10, d -> String.valueOf(d) + "1"));
    }

    private static void consumerString(String a, Consumer<String> con1, Consumer<String> con2) {
        con1.andThen(con2).accept(a);
    }

    private static void printFormat(String[] a, Consumer<String> con1, Consumer<String> con2) {
        for (String s : a) {
            con1.andThen(con2).accept(s);
        }
    }

    private static ArrayList<String> filterArray(String[] a, Predicate<String> pre1, Predicate<String> pre2) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : a) {
            boolean flag = pre1.and(pre2).test(s);
            if (flag) arrayList.add(s);
        }
        return arrayList;
    }

    private static String functionString(String a, Function<String, Integer> func1, Function<Integer, String> func2) {
        return func1.andThen(func2).apply(a);
    }
}
