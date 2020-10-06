package com.daiwei.sort.insertsort;

public class InsertSort {

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
}
