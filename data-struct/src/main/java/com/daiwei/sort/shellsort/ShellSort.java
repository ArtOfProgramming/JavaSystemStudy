package com.daiwei.sort.shellsort;

public class ShellSort {
    /**
     * (4)希尔排序
     * 1.数组长度/2 得到步数
     * 2.分组插入排序
     * 3.继续/2得到步数 直到步数为0
     */
    public static void shellSort(int[] arr) {
//        int step = arr.length / 2;
//        while (step != 0) {
//            for (int i = 0; i < step; i++) {
//                for (int j = i + step; j < arr.length; j += step) {
//                    int cur = arr[j];
//                    int index = j - step;
//                    while (index >= i && cur < arr[index]) {
//                        arr[index + step] = arr[index];
//                        index = index - step;
//                    }
//                    arr[index + step] = cur;
//                }
//            }
//            step = step / 2;
//        }
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int cur = arr[i];
                int index = i - gap;
                while (index >= 0 && cur < arr[index]) {
                    arr[index + gap] = arr[index];
                    index -= gap;
                }
                arr[index + gap] = cur;
            }
        }
    }

    /**
     * (4)希尔排序 通过交换 耗时较长
     *  不建议
     *
     *
     */
    public static void shellSortBySwap(int[] arr) {
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2 ) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }
}
