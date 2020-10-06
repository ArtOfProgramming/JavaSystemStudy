package com.daiwei.sort.mergesort;

public class MergeSort {

    /**
     * (6)归并排序
     * 分治算法
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (right > left) {
            int mid = (left + right) / 2;
            // 分
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            // 治
            int leftIndex = left;
            int rightIndex = mid + 1;
            int i = 0;
            while (leftIndex <= mid && rightIndex <= right) {
                if (arr[leftIndex] < arr[rightIndex]) {
                    temp[i] = arr[leftIndex];
                    leftIndex++;
                } else {
                    temp[i] = arr[rightIndex];
                    rightIndex++;
                }
                i++;
            }
            while (leftIndex <= mid) {
                temp[i] = arr[leftIndex];
                leftIndex++;
                i++;
            }
            while (rightIndex <= right) {
                temp[i] = arr[rightIndex];
                rightIndex++;
                i++;
            }
            int index = 0;
//            print(temp);
            for (int t = left; t <= right; t++) {
                arr[t] = temp[index];
                index++;
            }
        }

    }

}
