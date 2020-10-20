package com.daiwei.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class MapTest {

    public static void main(String[] args) {
        Hashtable<String,String> hashtable = new Hashtable<>();
//        hashtable.put(null,null); // hashtable 不允许Null
//        hashtable.put("abc",null);
//        hashtable.put(null,"abc");
        System.out.println(hashtable);
        Map<String, String> map = new HashMap<>(11);
        map.put("代维", "111");
        map.put("忘语", "222");
        map.put("真棒", "333");

        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            System.out.println(s + ":" + map.get(s));
        }

        Set<Entry<String, String>> set = map.entrySet();
        for (Entry<String, String> stringStringEntry : set) {
            System.out.println(stringStringEntry.getKey() + ":" + stringStringEntry.getValue());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        String a = scanner.next();
        Map<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            if (map1.containsKey(a.charAt(i))) {
                map1.put(a.charAt(i), map1.get(a.charAt(i)) + 1);
            } else {
                map1.put(a.charAt(i), 1);
            }
        }
        System.out.println(map1);
        System.out.println(highestOneBit(20));

        TreeMap<String, String> treeMap = new TreeMap<>(); // 红黑树
        treeMap.put("hello", "1");

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("hello", "2");
    }

    public static int highestOneBit(int i) {
        // HD, Figure 3-1
        i |= (i >>  1);
        i |= (i >>  2);
        i |= (i >>  4);
        i |= (i >>  8);
        i |= (i >> 16);
        return i - (i >>> 1);
    }

}
