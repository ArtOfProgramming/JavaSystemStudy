package com.daiwei.algorithm.kmp;

import java.util.Arrays;

public class KMP {

    public static void main(String[] args) {
        System.out.println(bruteForce("BBC ABCDAB ABCDABCDABDE", "ABCDABD"));
        System.out.println(violenceMatch("BBC ABCDAB ABCDABCDABDE", "ABCDABD"));

        System.out.println(Arrays.toString(kmpNext("aaab")));
        System.out.println(kmpMatch("BBC ABCDAB ABCDABCDABDE", "ABCDABD"));
        System.out.println(kmpMatch1("BBC ABCDAB ABCDABCDABDE", "ABCDABD"));
    }

    /**
     * 暴力匹配
     * @param a
     * @param b
     * @return
     */
    public static int bruteForce(String a, String b) {
        int i = 0;
        int index = -1;
        while (i < (a.length() - b.length() + 1)) {
            boolean flag = false;
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i + j) == b.charAt(j)) {
                    if (j == b.length() - 1) {
                        flag = true;
                    }
                    continue;
                } else {
                    i++;
                    break;
                }
            }
            if (flag) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 暴力匹配
     * @param a
     * @param b
     * @return
     */
    public static int violenceMatch(String a, String b) {
        char[] chars1 = a.toCharArray();
        char[] chars2 = b.toCharArray();
        int i = 0;
        int j = 0;
        while (i < chars1.length && j < chars2.length) {
            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == chars2.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static int kmpMatch(String a, String b) {
        int i = 0;
        int index = -1;
        int[] next = kmpNext(b);
        boolean flag = false;
        while (i < (a.length() - b.length() + 1)) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i + j) == b.charAt(j)) {
                    if (j == b.length() - 1) {
                        flag = true;
                    }
                    continue;
                } else {
                    if (j - 1 > 0) {
                        i += j - next[j - 1];
                    } else {
                        i += 1;
                    }
                    break;
                }
            }

            if (flag) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int kmpMatch1(String a, String b) {
        int[] next = kmpNext(b);
        for (int i = 0, j = 0; i < a.length(); i++) {
            while (j > 0 && a.charAt(i) != b.charAt(j)) {
                j = next[j - 1];
            }

            if (a.charAt(i) == b.charAt(j)) {
                j++;
            }
            if (j == b.length()) {
                return i - j  + 1;
            }
        }
        return -1;
    }

    /**
     * 获取子串部分匹配值表
     * @param sub
     * @return
     */
    public static int[] kmpNext(String sub) {
        int[] next = new int[sub.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < sub.length(); i++) {
            while (j > 0 && sub.charAt(i) != sub.charAt(j)) {
                j = next[j - 1];
            }
            if (sub.charAt(i) == sub.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
