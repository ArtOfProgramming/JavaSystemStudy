package com.daiwei.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class MapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
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
    }
}
