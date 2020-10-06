package com.daiwei;

import java.util.Arrays;

public class Floyd {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
            {0, 5, 7, MatrixFloyd.INF, MatrixFloyd.INF, MatrixFloyd.INF, 2},
            {5, 0, MatrixFloyd.INF, 9, MatrixFloyd.INF, MatrixFloyd.INF, 3},
            {7, MatrixFloyd.INF, 0, MatrixFloyd.INF, 8, MatrixFloyd.INF, MatrixFloyd.INF},
            {MatrixFloyd.INF, 9, MatrixFloyd.INF, 0, MatrixFloyd.INF, 4, MatrixFloyd.INF},
            {MatrixFloyd.INF, MatrixFloyd.INF, 8, MatrixFloyd.INF, 0, 5, 4},
            {MatrixFloyd.INF, MatrixFloyd.INF, MatrixFloyd.INF, 4, 5, 0, 6},
            {2, 3, MatrixFloyd.INF, MatrixFloyd.INF, 4, 6, 0},
        };
        MatrixFloyd matrixFloyd = new MatrixFloyd(vertex, matrix);
        matrixFloyd.show();
        matrixFloyd.floyd();
        matrixFloyd.show();
    }
}

class MatrixFloyd {

    char[] vertex;
    int[][] matrix;
    int[][] disMatrix;
    char[][] preVertex;
    public final static int INF = 65535;

    public MatrixFloyd(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        this.disMatrix = new int[vertex.length][vertex.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                disMatrix[i][j] = matrix[i][j];
            }
        }
        this.preVertex = new char[vertex.length][vertex.length];
        for (int i = 0; i < this.preVertex.length; i++) {
            Arrays.fill(this.preVertex[i], vertex[i]);
        }
    }

    public void floyd() {
        // 遍历中间节点
        for (int i = 0; i < vertex.length; i++) {
            // 遍历访问起点
            for (int j = 0; j < vertex.length; j++) {
                // 遍历访问终点
                for (int k = 0; k < vertex.length; k++) {
                    int len = disMatrix[j][i] + disMatrix[i][k];
                    if (len < disMatrix[j][k]) {
                        disMatrix[j][k] = len;
                        preVertex[j][k] = preVertex[i][k]; // 注意
                    }
                }
            }
        }
    }

    public void show() {
        for (int i = 0; i < this.disMatrix.length; i++) {
            System.out.println(Arrays.toString(disMatrix[i]));
        }
        for (int i = 0; i < this.preVertex.length; i++) {
            System.out.println(Arrays.toString(preVertex[i]));
        }
    }
}

