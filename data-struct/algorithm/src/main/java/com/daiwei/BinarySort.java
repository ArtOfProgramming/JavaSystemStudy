package com.daiwei;

public class BinarySort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 8};
        System.out.println(binarySearch1(arr, 0, arr.length - 1, 8));
    }

    public static int binarySearch1(int[] arr, int left, int right, int value) {
        while (left <= right) {
            int mid = (left + right) / 2;
            int povit = arr[mid];
            if (value < povit) {
                right = mid - 1;
            } else if (value > povit){
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

