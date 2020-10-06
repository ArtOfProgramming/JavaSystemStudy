package com.daiwei;

import java.util.Arrays;

public class Dijkstra {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];

        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, 8, N, N, N};
        matrix[3] = new int[]{N, 9, N, N, 4, N, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 2};
        // 创建graph
        DGragh gragh = new DGragh(vertex, matrix);
        gragh.showGraph();
        gragh.dijlstra(6);
        gragh.show();
    }
}

class VisitedVertex {
    public int[] already_arr; // 已访问节点
    public int[] pre_visited; // 前驱节点
    public int[] dis; // 距离

    public VisitedVertex(int len, int index) {
        this.already_arr = new int[len];
        this.pre_visited = new int[len];
        this.dis = new int[len];
        // 初始化dis
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;
        this.dis[index] = 0;
    }


    /**
     * 是否已经访问过
     * @param index
     * @return
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新距离数组
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新这个顶点的前驱为index
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     *返回出发节点到该节点的距离
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    public void show () {
        System.out.println("====================");
        System.out.println(Arrays.toString(already_arr));
        System.out.println("====================");
        System.out.println(Arrays.toString(pre_visited));
        System.out.println("====================");
        System.out.println(Arrays.toString(dis));
    }

}

class DGragh {
    private char[] vertex; // 顶点数组
    private int[][] matrix; // 邻接矩阵
    private VisitedVertex visitedVertex;

    public DGragh(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public void dijlstra(int index) {
        this.visitedVertex = new VisitedVertex(vertex.length,index);
        update(index);
        for (int j = 1; j < vertex.length; j++) {
            index = visitedVertex.updateArr();
            update(index);
        }

    }

    //
    private void update(int index) {
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            // 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
            len = visitedVertex.getDis(index) + matrix[index][i];
            if (!visitedVertex.in(i) && len < visitedVertex.getDis(i)) {
                visitedVertex.updatePre(i, index);
                visitedVertex.updateDis(i, len);
            }
        }
    }

    public void show() {
        visitedVertex.show();
    }
}