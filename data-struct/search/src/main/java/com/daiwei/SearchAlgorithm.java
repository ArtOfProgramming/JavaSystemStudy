package com.daiwei;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 算法
 */
public class SearchAlgorithm {

    public static void main(String[] args) {
        int[] arr = new int[10];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
//        arr = new int[] {9, 10, 9, 9, 9, 8, 9, 9, 9, 9};
        Sort.quickSort(arr, 0, arr.length - 1);
        Sort.print(arr);
////        System.out.println(binarySearch(arr,0, arr.length - 1, 9));
//        List<Integer> temp = binarySearchAll(arr, 0, arr.length - 1, 9);
//        for (Integer integer : temp) {
//            System.out.println(integer);
//        }
//        System.out.println(insertSearch(arr, 0, arr.length - 1, arr[5]));
//        System.out.println(fibonacciSearch(arr, 0, arr.length - 1, arr[5]));
        System.out.println(fibonacciSearch1(arr, arr[9]));
    }

    /**
     * 顺序查找
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找 从小到大的情况
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[(left + right) / 2];
        if (midValue > value) {
            return binarySearch(arr, left, mid - 1, value);
        } else if (midValue< value) {
            return binarySearch(arr, mid + 1, right, value);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找 从小到大的情况 查找该值的所有下标
     * mid = left + (right - left) * (value - left) * (arr[right] - arr[left])
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    public static List<Integer> binarySearchAll(int[] arr, int left, int right, int value) {
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midValue = arr[(left + right) / 2];
        if (midValue > value) {
            return binarySearchAll(arr, left, mid - 1, value);
        } else if (midValue< value) {
            return binarySearchAll(arr, mid + 1, right, value);
        } else {
            List<Integer> valueIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != value) {
                    break;
                }
                valueIndexList.add(temp);
                temp -= 1;
            }
            temp = mid;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != value) {
                    break;
                }
                valueIndexList.add(temp);
                temp += 1;
            }
            return valueIndexList;
        }
    }

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
