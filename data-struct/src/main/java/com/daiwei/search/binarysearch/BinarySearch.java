package com.daiwei.search.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

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
}
