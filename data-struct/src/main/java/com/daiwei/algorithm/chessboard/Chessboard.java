package com.daiwei.algorithm.chessboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Chessboard {

    private static int X; // 列数
    private static int Y; // 行数
    private static int[][] board; // 棋盘数
    private static int step; // 当前步数
    private static boolean[] visited; // 已访问标识
    private static boolean finished; // 是否完成

    public static void main(String[] args) {

        int n = 8;
        X = n;
        Y = n;
        board = new int[n][n];
        visited = new boolean[X * Y];
        step = 1;
        long start = System.currentTimeMillis();
        chessBoard(new Point(0, 0), step);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    public static void chessBoard(Point p, int step) {
        visited[p.x * X + p.y] = true;
        board[p.x][p.y] = step;
        ArrayList<Point> nextPoints = next1(p);
        Collections.sort(nextPoints, new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                // TODO Auto-generated method stub
                //获取到o1的下一步的所有位置个数
                return next(o1).size() - next(o2).size();
            }

        });
//        nextPoints.sort(new Comparator<Point>() {
//
//            @Override
//            public int compare(Point o1, Point o2) {
//                // TODO Auto-generated method stub
//                //获取到o1的下一步的所有位置个数
//                int count1 = next(o1).size();
//                //获取到o2的下一步的所有位置个数
//                int count2 = next(o2).size();
//                if(count1 < count2) {
//                    return -1;
//                } else if (count1 == count2) {
//                    return 0;
//                } else {
//                    return 1;
//                }
//            }
//
//        });
//        sort(nextPoints);
        while (!nextPoints.isEmpty()) {
            Point nextPoint = nextPoints.remove(0);
            if (!visited[nextPoint.x * X + nextPoint.y]) {
                chessBoard(nextPoint, step + 1);
            }
        }
        if (step < X * Y  && !finished) {
            visited[p.x * X + p.y] = false;
            board[p.x][p.y] = 0;
        } else {
            finished = true;
        }
    }

    /**
     * 返回下一步可以走的位置数组
     * @param p
     * @return
     */
    public static ArrayList<Point> next(Point p) {
        ArrayList<Point> ps = new ArrayList<>();
        if (p.x - 1 >= 0 && p.y - 2 >= 0) {
            ps.add(new Point(p.x - 1, p.y - 2));
        }
        if (p.x - 2 >= 0 && p.y - 1 >= 0) {
            ps.add(new Point(p.x - 2, p.y - 1));
        }
        if (p.x - 2 >= 0 && p.y + 1 < X) {
            ps.add(new Point(p.x - 2, p.y + 1));
        }
        if (p.x - 1 >= 0 && p.y + 2 < X) {
            ps.add(new Point(p.x - 1, p.y + 2));
        }
        if (p.x + 1 < Y && p.y + 2 < X) {
            ps.add(new Point(p.x + 1, p.y + 2));
        }
        if (p.x + 2 < Y && p.y + 1 < X) {
            ps.add(new Point(p.x + 2, p.y + 1));
        }
        if (p.x + 2 < Y && p.y - 1 >= 0) {
            ps.add(new Point(p.x + 2, p.y - 1));
        }
        if (p.x + 1 < Y && p.y - 2 >= 0) {
            ps.add(new Point(p.x + 1, p.y - 2));
        }

        return ps;
    }

    public static ArrayList<Point> next1(Point curPoint) {
        //创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<Point>();
        //创建一个Point
        Point p1 = new Point();
        //表示马儿可以走5这个位置
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y -1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走6这个位置
        if((p1.x = curPoint.x - 1) >=0 && (p1.y=curPoint.y-2)>=0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走7这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走0这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走1这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走2这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走3这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走4这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                // TODO Auto-generated method stub
                //获取到o1的下一步的所有位置个数
                int count1 = next(o1).size();
                //获取到o2的下一步的所有位置个数
                int count2 = next(o2).size();
                if(count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }

        });
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Point() {
    }
}
