package com.daiwei.sort.quicksort;

public class QuickSort {
    /**
     * (5)快速排序 分左右递归
     * 对冒泡排序的改进
     * @param arr
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int povit = arr[(l + r) / 2];
        while (l < r) {

            while (arr[l] < povit) {
                l++;
            }

            while (arr[r] > povit) {
                r--;
            }

            if (l >= r) {
                break;
            }

            swap(arr, l ,r);

            if (arr[l] == povit) {
                r--;
            } else if (arr[r] == povit) {
                l++;
            }
        }

        if (l == r) {
            l++;
            r--;
        }

        if (r > left) {
            quickSort(arr, left, r);
        }

        if (l < right) {
            quickSort(arr, l, right);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
