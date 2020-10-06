package com.daiwei.algorithm.prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Prim {

    public static void main(String[] args) {
        Graph graph = new Graph(7);
        String[] vertexList = {"A", "B", "C", "D", "E", "F", "G"};
        for (String s : vertexList) {
            graph.insertVertex(s);
        }
        // 添加边
        graph.addEdges(0, 1, 5);
        graph.addEdges(0, 2, 7);
        graph.addEdges(0, 6, 2);
        graph.addEdges(1, 3, 9);
        graph.addEdges(1, 6, 3);
        graph.addEdges(2, 4, 8);
        graph.addEdges(3, 5, 4);
        graph.addEdges(4, 5, 5);
        graph.addEdges(4, 6, 4);
        graph.addEdges(5, 6, 6);
//        graph.showGraph();
        List<Graph> graphs = prim(graph);
        graphs.get(0).showGraph();
        for (Graph graph1 : graphs) {
            System.out.println(graph1.getTotalWeight());
        }
    }

    public static List<Graph> prim(Graph graph) {
        List<Graph> graphs = new ArrayList<>();
        int i = 0;
//        for (int i = 0; i < graph.getVertexList().size(); i++) {
            graph.resetVisited();
            Graph newGraph = new Graph(graph.getNumberOfVertex());
            newGraph.insertVertex(graph.getVertexList().get(i));
            graph.setVisited(i, true);
            while (newGraph.getNumberOfVertex() != graph.getNumberOfVertex()) {
                // 寻找最短连通路径的节点
                int minIndex1 = -1;
                int minIndex2 = -1;
                int minPath = Integer.MAX_VALUE;
                for (int j = 0; j < graph.getVertexList().size(); j++) {
                    // 已访问的图节点
                    if (graph.isVisited(j)) {
                        // 寻找下一个距离最短节点
                        for (int k = 0; k < graph.getEdges()[j].length; k++) {
                            if (!graph.isVisited(k) && graph.getEdges()[j][k] != 0 && graph.getEdges()[j][k] < minPath) {
                                minIndex1 = j;
                                minIndex2 = k;
                                minPath = graph.getEdges()[j][k];
                            }
                        }
                    }
                }
                if (minIndex1 != -1 && minIndex2 != -1) {
                    graph.setVisited(minIndex2, true);
                    newGraph.insertVertex(graph.getValueByIndex(minIndex2));
                    newGraph.addEdges(minIndex1, minIndex2, minPath);
                }
            }
            graphs.add(newGraph);
//        }
        return graphs;
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
     * 设置顶点被访问状态
     * @param i
     * @return
     */
    public void setVisited (int i, boolean flag) {
        if (i < isVisited.length && i >= 0) {
            isVisited[i] = flag;
        } else {
            System.out.println("不存在下标为" + i + "的顶点" );
        }
    }

    public int getTotalWeight() {
        int sum = 0;
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                sum += edges[i][j];
            }
        }
        return sum / 2;
    }

    /**
     * 判断顶点是否已经被访问
     * @param i
     */
    public boolean isVisited (int i) {
        if (i < isVisited.length && i >= 0) {
            return isVisited[i];
        } else {
            System.out.println("不存在下标为" + i + "的顶点" );
            return false;
        }
    }

    public void resetVisited () {
        isVisited = new boolean[vertexList.size()];
    }

    public ArrayList<String> getVertexList() {
        return vertexList;
    }

    public int[][] getEdges() {
        return edges;
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