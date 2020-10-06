package com.daiwei.sort.bubblesort;

public class BubbleSort {

    /**
     * (1)冒泡排序
     * 1.一共进行了数组大小减1次排序
     * 2.每一趟排序的次数都在逐渐减小
     * 3.如果发现某一次排序未发生交换，则可以跳出
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    count++;
                    swap(arr, j, j + 1);
                }
            }
            if (!flag) {
                break;
            }
        }
        System.out.println("count:" + count);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
