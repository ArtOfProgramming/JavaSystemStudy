package com.daiwei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Greed {

    public static void main(String[] args) {
        HashMap<String, String[]> hashMap = new HashMap<>();
        hashMap.put("K1", new String[]{"北京", "上海", "天津"});
        hashMap.put("K2", new String[]{"广州", "上海", "深圳"});
        hashMap.put("K3", new String[]{"成都", "上海", "杭州"});
        hashMap.put("K4", new String[]{"上海", "天津"});
        hashMap.put("K5", new String[]{"杭州", "大连"});
        System.out.println(Arrays.toString(greed(hashMap).toArray()));
    }

    /**
     * 贪婪算法求解广播覆盖
     * @param hashMap  // set.retainAll(set1) (求交集)
     * @return
     */
    public static List<String> greed(HashMap<String, String[]> hashMap) {
        HashSet<String> address = new HashSet<>();
        for (String[] value : hashMap.values()) {
            for (String s : value) {
                address.add(s);
            }
        }
        List<String> list = new ArrayList<>();
        while (address.size() > 0) {
            int count = 0;
            String maxStation = null;
            for (Entry<String, String[]> entry : hashMap.entrySet()) {
                int tempCount = 0;
                for (String s : entry.getValue()) {
                    if (address.contains(s)) {
                        tempCount++;
                    }
                }
                if (tempCount > count) {
                    count = tempCount;
                    maxStation = entry.getKey();
                }
            }
            if (maxStation != null) {
                list.add(maxStation);
            }
            address.removeAll(Arrays.asList(hashMap.get(maxStation)));
        }
        return list;
    }
}
