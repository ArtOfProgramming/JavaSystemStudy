package com.daiwei.search.seqsearch;

public class SeqSearch {
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
}
