package com.daiwei.question605;

import java.util.Arrays;

public class Question605 {

    public static void main(String[] args) {
        int[] flowerbed = {1,0,0,0,0,1};
        System.out.println(canPlaceFlowers(flowerbed, 2));
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        // 如果数组长度小于等于0或者n为负数，则直接返回
        if (flowerbed.length <= 0 || n < 0) {
            return false;
        }
        // 从左往右种树
        for (int i = 0, k = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i - 1 < 0 || flowerbed[i - 1] == 0) && (i + 1 > flowerbed.length || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                System.out.println(Arrays.toString(flowerbed));
                n--;
                if (n <= 0) {
                    break;
                }
            }
        }
        // 当n小于等于0时，说明全部种植成功
        if (n <= 0) {
            return true;
        } else{
            return false;
        }
    }
}
