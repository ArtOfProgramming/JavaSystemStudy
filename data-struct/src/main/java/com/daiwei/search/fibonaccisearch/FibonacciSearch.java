package com.daiwei.search.fibonaccisearch;

import java.util.Arrays;

public class FibonacciSearch {

    public static int[] fibonacciArr(int n) {
        int maxSize = n;
        int[] f = new int[maxSize < 2 ? 2 : maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibonacciSearch1(int[] arr, int value) {
        int k = 1;
        int[] f = fibonacciArr(k);
        while (f[k - 1] - 1 < arr.length) {
            f = fibonacciArr(++k);
        }
        int low = 0;
        int high = arr.length - 1;
        int[] temp = new int[f[k - 1] - 1];
        temp = Arrays.copyOf(arr, f[k - 1] - 1);
        for (int i = arr.length; i < f[k - 1] - 1; i++) {
            temp[i] = temp[arr.length - 1];
        }
        while (low <= high) {
            int mid = low + f[k - 2] - 1;
            if (value < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (value > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

//
//    public static int fibonacciSearch(int[] arr, int left, int right, int value) {
//        if (left > right) {
//            return -1;
//        }
//        int k = 0;
//        while (fibonacci(k) < (right - left + 1)) {
//            k++;
//        }
//        int mid = left + fibonacci(k - 1) - 1;
//        int midValue = arr[left + fibonacci(k - 1) - 1];
//        if (value < midValue) {
//            return fibonacciSearch(arr, left, mid - 1, value);
//        } else if (value > midValue) {
//            return fibonacciSearch(arr, mid + 1, right, value);
//        } else {
//            return mid;
//        }
//    }
//
//    public static int fibonacci(int n) {
//        if (n == 0 || n == 1) {
//            return 1;
//        }
//        return fibonacci(n - 1) + fibonacci(n - 2);
//    }
}
