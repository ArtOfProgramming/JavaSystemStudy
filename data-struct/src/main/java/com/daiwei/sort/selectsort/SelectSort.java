package com.daiwei.sort.selectsort;

public class SelectSort {

    /**
     * (2)选择排序
     * 1.选择拍讯一共进行数组大小减1次排序
     * 2.每一轮排序，又是一个循环
     * 3.循环中，找到最小值，当遍历到最后时，得到本轮最小下标
     * 4.将本轮最小下标与本轮下标值交换
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
