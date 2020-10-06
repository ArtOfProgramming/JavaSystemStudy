package com.daiwei.search.insertsearch;

public class InsertSearch {

    /**
     * 插值查找
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static int insertSearch(int[] arr, int left, int right, int value) {
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (value - left) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (value > midValue) {
            return insertSearch(arr, mid + 1, right, value);
        } else if (value < midValue) {
            return insertSearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }
}
