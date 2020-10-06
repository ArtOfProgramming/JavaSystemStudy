package com.daiwei.sort.heapsort;

public class HeapSort {

    /**
     * 堆排序
     * @param arr
     */
    public static void heapSort(int[] arr) {
        int temp = arr.length;
        while (temp > 0) {
            int index = temp / 2 - 1;
            int k = index * 2 + 1;
            while (index >= 0) {
                if (k + 1 < temp && arr[k] < arr[k + 1]) {
                    k++;
                }
                swap(arr, index, k);
                index--;
            }
            swap(arr, 0, temp - 1);
            temp--;
        }
    }

    /**
     * 堆排序
     */
    public static void heapSort1(int[] arr) {
        // 构建大顶堆，从右向左，从下向上
        int last = (arr.length - 1) / 2;
        for (int i = last; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    /**
     *
     */
    public static void heapify(int[] arr, int len, int index) {
        int k = index * 2 + 1;
        if (k >= len) {
            return;
        }
        if (k + 1 < len && arr[k] < arr[k + 1]) {
            k++;
        }
        if (arr[k] > arr[index]) {
            swap(arr, k, index);
            heapify(arr, len, k);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
