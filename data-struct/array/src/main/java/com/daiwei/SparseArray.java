package com.daiwei;

import java.util.ArrayList;
import java.util.List;

/**
 * sparseArray
 * 1.数组压缩成稀疏数组
 * 2.稀疏数组还原成原数组
 *
 * 稀疏数组
 * 1.第一行存原始行列和数据量
 * 2.sum+1行3列
 */

public class SparseArray {

    private static class Pos {
        private int row;
        private int col;
        private int value;

        public Pos(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static int[][] compress(int[][] array, int normal) {
        // 遍历找到特征值位置及计数
        List<Pos> list = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int i1 = 0; i1 < array[i].length; i1++) {
                if (array[i][i1] != normal) {
                    list.add(new Pos(i, i1, array[i][i1]));
                    count++;
                }
            }
        }
        int[][] array1 = new int[count + 1][3];
        array1[0][0] = array.length;
        array1[0][1] = array[0].length;
        array1[0][2] = count;
        for (int i = 0; i < list.size(); i++) {
            array1[i + 1][0] = list.get(i).getRow();
            array1[i + 1][1] = list.get(i).getCol();
            array1[i + 1][2] = list.get(i).getValue();
        }
        return array1;
    }

    private static int[][] parse(int[][] array, int normal) {
        // 创建容器
        int[][] array1 = new int[array[0][0]][array[0][1]];

        // 填充
        if (normal != 0) {
            for (int i = 0; i < array1.length; i++) {
                for (int i1 = 0; i1 < array1[i].length; i1++) {
                    array1[i][i1] = normal;
                }
            }
        }
        // 解析
        for (int i = 1; i < array.length; i++) {
            array1[array[i][0]][array[i][1]] = array[i][2];
        }

        return array1;
    }

    private static void goThrough(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int i1 = 0; i1 < array[i].length; i1++) {
                System.out.print(array[i][i1] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] temp = new int[9][9];
        temp[0][1] = 10;
        temp[2][3] = 9;
        goThrough(temp);
        int[][] compressArray = compress(temp, 0);
        goThrough(compressArray);
        int[][] parseArray = parse(compressArray, 0);
        goThrough(parseArray);
    }
}
