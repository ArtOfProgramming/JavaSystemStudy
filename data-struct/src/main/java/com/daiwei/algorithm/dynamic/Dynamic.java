package com.daiwei.algorithm.dynamic;

import java.util.Arrays;

public class Dynamic {

    public static void main(String[] args) {
        int[] v = {1500, 3000, 2000};
        int[] w = {1, 4, 3};
        int maxW = 4;
        int[][] m = dynamic(v, w, maxW);
        for (int[] ints : m) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 背包问题
     * @param v 物品价值
     * @param w 物品重量
     * @param maxWeight 背包容量
     * @return
     */
    public static int[][] dynamic (int[] v, int[] w, int maxWeight) {
        int[][] m = new int[v.length + 1][maxWeight + 1];
        int[][] path = new int[v.length + 1][maxWeight + 1];
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                 if (w[i - 1] > j) {
                    m[i][j] = m[i - 1][j];
                } else {
//                    m[i][j] = Math.max(m[i - 1][j], v[i - 1] + m[i - 1][j - w[i - 1]]);
                     if (m[i - 1][j] < v[i - 1] + m[i - 1][j - w[i - 1]]) {
                         m[i][j] = v[i - 1] + m[i - 1][j - w[i - 1]];
                         path[i][j] = 1;
                     } else {
                         m[i][j] = m[i - 1][j];
                     }
                }
            }
        }

        int i = path.length - 1;
        int j = path[i].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个商品放入书包");
                j -= w[i - 1];
            }
            i--;
        }

        return m;
    }
}
