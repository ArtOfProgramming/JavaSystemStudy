package com.daiwei.sort.radixsort;

public class RadixSort {
    /**
     * (7)基数排序
     * 桶
     * @param arr
     */
    public static void radixSort(int[] arr) {
        // 定义桶
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCount = new int[10];
        int maxBit = 1;
        int max = arr[0];
        // 找到最大值的位数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        while ((max / 10) != 0) {
            maxBit++;
            max /= 10;
        }


        for (int i = 0; i < maxBit; i++) {
            // 装桶
            for (int j = 0; j < arr.length; j++) {
                int index = arr[j] / (int)Math.pow(10, i) % 10;
                bucket[index][bucketElementCount[index]] = arr[j];
                bucketElementCount[index]++;
            }
            // 取出
            int k = 0;
            for (int m = 0; m < bucketElementCount.length; m++) {
                for (int j = 0; j < bucketElementCount[m]; j++) {
                    arr[k++] = bucket[m][j];
                }
            }
            for (int n = 0; n < bucketElementCount.length; n++) {
                bucketElementCount[n] = 0;
            }
        }

    }
}
