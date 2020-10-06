package com.daiwei;

import com.daiwei.bean.Laptop;
import com.daiwei.bean.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionTest {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>();
        list.add(new Person());
        list.add(new Laptop());
        System.out.println(list);
        for (Object o : list) {

        }
        int l = 1 + 2;
        int a = 10;
        double b = 20.0;
        testGeneric(a);
        testGeneric(b);
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        ArrayList<Double> list2 = new ArrayList<>();
        list2.add(3.0);
        list2.add(4.0);
        testGeneric1(list1);
        testGeneric1(list2);

        testExtendsGeneric(list1);
        testExtendsGeneric(list2);

//        testSuperGeneric(list1);
        testSuperGeneric(list2);
        List<Integer> list3 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list3.add(i);
        }
        Iterator<Integer> it = list3.iterator();
        while (it.hasNext()) {
            if (it.next() == 5) {
                it.next();
                it.remove();
            }
        }
        System.out.println(list3);
        String y = "daiwei";
//        String u = "daiwei";
        String u = new String("daiwei").intern();
        System.out.println(y == u);
        System.out.println(u.equals(u));

    }

    public static void testGeneric(Number a) {
        System.out.println(a);
    }

    public static void testGeneric1(ArrayList<?> list) {
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void testExtendsGeneric(Collection<? extends Number> list) {
        Iterator<? extends Number> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void testSuperGeneric(Collection<? super Double> list) {
        Iterator<? super Double> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
