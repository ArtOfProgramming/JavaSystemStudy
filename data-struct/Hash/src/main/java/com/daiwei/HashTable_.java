package com.daiwei;

import java.util.Hashtable;

public class HashTable_ {

    public static void main(String[] args) {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        for (int i = 0; i < 10; i++) {
            hashtable.put(i, i);
        }

    }
}
