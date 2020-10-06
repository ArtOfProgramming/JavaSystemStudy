package com.daiwei;

import com.sun.deploy.util.ArrayUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 八种排序算法
 */
public class Sort {

    public static void main(String[] args) {
//        int[] arr = new int[10];
//        Random random = new Random(System.currentTimeMillis());
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = random.nextInt(200);
//        }
//        int[] arr = new int[8000000];
//        Random random = new Random(System.currentTimeMillis());
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = random.nextInt(8000000);
//        }
//        print(arr);
//        long start = System.currentTimeMillis();
////        bubbleSort(arr);
////        selectSort(arr);
////        shellSortBySwap(arr);
//        insertSort(arr);
//        shellSort(arr);
//        quickSort(arr, 0, arr.length - 1);
//        radixSort(arr);
//        heapSort(arr);
//        heapSort1(arr);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
////        insertSort(arr);
//        shellSort(arr);
//        print(arr);
//        int[] arr = new int[]{10, 9, 8 ,7, 6, 5, 4, 3, 2, 1};
//        print(arr);
////        shellSortBySwap(arr);
//        shellSort(arr);
//        print(arr);
//        int[] arr = new int[]{10, 9, 7 ,8, 5};
//        print(arr);
//        quickSort(arr, 0, arr.length - 1);
//        int[] temp = new int[arr.length];
//        mergeSort(arr, 0, arr.length - 1, temp);
//        radixSort(arr);
//        heapSort(arr);
//        print(arr);
        int[] arr = {1, 3, 5, 7, 9};
        print(arr);
        heapSort1(arr);
//        print(arr);
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

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

    /**
     * (2)选择排序
     * 1.选择拍讯一共进行数组大小减1次排序
     * 2.每一轮排序，又是一个循环
     * 3.循环中，找到最小值，当遍历到最后时，得到本轮最小下标
     * 4.将本轮最小下标与本轮下标值交换
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * (3)插入排序
     * 1.两个数组，一个有序，一个无序
     * 2.循环从无序表中取出第一个元素，插入到有序表中适当位置
     * 3.无序表遍历完毕结束
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            int index = i - 1;
            while (index >= 0 && cur < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = cur;
        }
//        List<Integer> temp = new ArrayList<>();
//        temp.add(arr[0]);
//        for (int i = 1; i < arr.length; i++) {
//            int cur = arr[i];
//            for (int j = temp.size() - 1; j >= 0; j--) {
//                if (cur >= temp.get(j)) {
//                    temp.add(j + 1, cur);
//                    break;
//                } else if (j == 0) {
//                    temp.add(j, cur);
//                    break;
//                }
//            }
//        }
//        for (int i = 0; i < temp.size(); i++) {
//            arr[i] = temp.get(i);
//        }
    }

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
     *
     *
     *
     */
    public static void shellSortBySwap(int[] arr) {
        int temp = 0;
        int count = 0;
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


    /**
     * (7)基数排序
     * 桶
     * @param arr
     */
    public static void radixSort(int[] arr) {
        // 定义桶
        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCount = new int[10];
        int maxBit = 1;
        int max = arr[0];
        // 找到最大值的位数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        while ((max / 10) != 0) {
            maxBit++;
            max /= 10;
        }


        for (int i = 0; i < maxBit; i++) {
            // 装桶
            for (int j = 0; j < arr.length; j++) {
                int index = arr[j] / (int)Math.pow(10, i) % 10;
                bucket[index][bucketElementCount[index]] = arr[j];
                bucketElementCount[index]++;
            }
            // 取出
            int k = 0;
            for (int m = 0; m < bucketElementCount.length; m++) {
                for (int j = 0; j < bucketElementCount[m]; j++) {
                    arr[k++] = bucket[m][j];
                }
            }
            for (int n = 0; n < bucketElementCount.length; n++) {
                bucketElementCount[n] = 0;
            }
        }

    }

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
}
