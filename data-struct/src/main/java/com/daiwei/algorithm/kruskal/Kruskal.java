package com.daiwei.algorithm.kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Kruskal {

    public static void main(String[] args) {
        MGraph graph = new MGraph(7);
        String[] vertexList = {"A", "B", "C", "D", "E", "F", "G"};
        for (String s : vertexList) {
            graph.insertVertex(s);
        }
        // 添加边
        graph.addEdges(0, 1, 12);
        graph.addEdges(0, 5, 16);
        graph.addEdges(0, 6, 14);
        graph.addEdges(1, 2, 10);
        graph.addEdges(1, 5, 7);
        graph.addEdges(2, 3, 3);
        graph.addEdges(2, 4, 5);
        graph.addEdges(2, 5, 6);
        graph.addEdges(3, 4, 4);
        graph.addEdges(4, 5, 2);
        graph.addEdges(4, 6, 8);
        graph.addEdges(5, 6, 9);

        List<Edge> edges = new ArrayList<>();
        List<Edge> endEdges = new ArrayList<>();
        int[] ends = new int[graph.getEdges().length];
        for (int i = 0; i < graph.getEdges().length; i++) {
            for (int j = i + 1; j < graph.getEdges()[i].length; j++) {
                if (graph.getEdges()[i][j] != MGraph.INF) {
                    edges.add(new Edge(i, j, graph.getValueByIndex(i), graph.getValueByIndex(j), graph.getWeight(i, j)));
                }
            }
        }
        Collections.sort(edges);
        System.out.println(edges);
        for (int i = 0; i < edges.size(); i++) {
            int m;
            int n;
            m = ends(ends, edges.get(i).getOneIndex());
            n = ends(ends, edges.get(i).getTheOtherIndex());
            if (m != n) {
                ends[m] = n;
                endEdges.add(edges.get(i));
            }
        }
        System.out.println(endEdges);
        System.out.println(endEdges.size());
        System.out.println(Arrays.toString(ends));
    }

    public static int ends(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public static void kruskal(MGraph graph) {
//        List<> list = new ArrayList<>();
    }
}

class MGraph {

    private ArrayList<String> vertexList; // 存储顶点集合
    private int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdges; // 表示边的数目
    private boolean[] isVisited; // 是否已经访问
    public final static int INF = Integer.MAX_VALUE;

    public MGraph(int n) {
        edges = new int[n][n];
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                edges[i][j] = INF;
            }
        }
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

    /**
     * 判断是否构成回路
     */
    public boolean isCircle() {
        return false;
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

class Edge implements Comparable<Edge> {
    private int oneIndex;
    private int theOtherIndex;
    private String oneVertex;
    private String theOtherVertex;
    private int weight;

    public Edge(int oneIndex, int theOtherIndex, String oneVertex, String theOtherVertex, int weight) {
        this.oneIndex = oneIndex;
        this.theOtherIndex = theOtherIndex;
        this.oneVertex = oneVertex;
        this.theOtherVertex = theOtherVertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }

    public String getOneVertex() {
        return oneVertex;
    }

    public void setOneVertex(String oneVertex) {
        this.oneVertex = oneVertex;
    }

    public String getTheOtherVertex() {
        return theOtherVertex;
    }

    public void setTheOtherVertex(String theOtherVertex) {
        this.theOtherVertex = theOtherVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getOneIndex() {
        return oneIndex;
    }

    public void setOneIndex(int oneIndex) {
        this.oneIndex = oneIndex;
    }

    public int getTheOtherIndex() {
        return theOtherIndex;
    }

    public void setTheOtherIndex(int theOtherIndex) {
        this.theOtherIndex = theOtherIndex;
    }

    @Override
    public String toString() {
        return "Edge{" +
            "oneVertex='" + oneVertex + '\'' +
            ", theOtherVertex='" + theOtherVertex + '\'' +
            ", weight=" + weight +
            '}';
    }
}