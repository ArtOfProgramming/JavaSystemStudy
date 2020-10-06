package com.daiwei.algorithm.permutation;

/**
 * 全排序
 */
public class Permutation {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3};
        permutation(arr, 0, arr.length);
    }

    public static void permutation(int[] arr, int p, int q) {
        if (p == q) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = p; i < q; i++) {
            swap(arr, p, i);
            permutation(arr, p + 1, q);
            swap(arr, i, p);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
