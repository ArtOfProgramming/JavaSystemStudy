package com.daiwei.collection;

import com.daiwei.collection.bean.Laptop;
import com.daiwei.collection.bean.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class CollectionTest {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<Object>(); // 底层插入指定位置，通过将index后的值拷贝到后面，然后赋值index 数组 线程不安全 扩容1.5倍
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

        LinkedList linkedList = new LinkedList(); // 双向链表 线程不安全
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(1, 2); // 先判断是否索引小于size的一半，如果小于 从头遍历，如果大于，从尾遍历

        Vector vector = new Vector(); // 线程安全 数组 扩容指定 或者2倍
        vector.add(1);

        HashSet set = new HashSet(); // 唯一 无序 底层HashMap
        set.add(null); //ok
        set.add(1);
        System.out.println(set);
        HashSet set1 = new HashSet();
        set1.add(new Person("hello", 12));
        set1.add(new Person("hello1", 13));
        set1.add(new Person("hello2", 14));
        set1.add(new Person("hello2", 14));
        System.out.println(set1);

        TreeSet treeSet = new TreeSet(); // 底层是TreeMap (底层红黑树) 有序 类型必须一致(内部自动排序) 不能有null，编译不报错，运行时错误
        treeSet.add(2);
        treeSet.add(1);
        System.out.println(treeSet);
        TreeSet<Person> treeSet1 = new TreeSet<>();
//        TreeSet<Person> treeSet1 = new TreeSet<>(new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o2.getAge() - o1.getAge(); // 倒序
//            }
//        }); // 1.内部排序，实现Comparable接口。2.外部排序，传入Comparator实现类 从小到大排序 前面减后面 // 两者都存在 则默认使用传入的。源码可以看到 当时用内、外部比较器时，不会使用equals;
        treeSet1.add(new Person("hello", 12));
        treeSet1.add(new Person("hello1", 13));
        treeSet1.add(new Person("hello2", 14));
        treeSet1.add(new Person("hello2", 14));
        System.out.println(treeSet1);
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
