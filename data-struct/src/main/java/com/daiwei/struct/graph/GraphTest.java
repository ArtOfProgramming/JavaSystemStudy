package com.daiwei.struct.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GraphTest {

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertexList = {"A", "B", "C", "D", "E"};
        for (String s : vertexList) {
            graph.insertVertex(s);
        }
        // 添加边
        graph.addEdges(0, 1, 1);
        graph.addEdges(0, 2, 1);
        graph.addEdges(1, 2, 1);
        graph.addEdges(1, 3, 1);
        graph.addEdges(1, 4, 1);

        graph.showGraph();

        graph.dfs();
//        graph.bfs();
    }
}

class Graph {

    private ArrayList<String> vertexList; // 存储顶点集合
    private int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdges; // 表示边的数目
    private boolean[] isVisited; // 是否已经访问

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     * 插入顶点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1
     * @param v2
     */
    public void addEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 获取顶点数
     *
     * @return
     */
    public int getNumberOfVertex() {
        return vertexList.size();
    }

    /**
     * 获取边数
     *
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 获取坐标对应的值
     *
     * @param i
     * @return
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 对dfs 进行重载，遍历我们所有的节点，并进行dfs
     */
    public void dfs() {
        //
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    public void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;

        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    public void bfs() {
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    public void bfs(boolean[] isVisited, int i) {
        int u;
        int w;
        LinkedList linkedList = new LinkedList();
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        linkedList.addLast(i);

        while (!linkedList.isEmpty()) {
            u = (int) linkedList.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    linkedList.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    /**
     * 得到第一个邻接节点的下标w
     *
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 获取前一个邻接节点的下一个邻接节点
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }


}
