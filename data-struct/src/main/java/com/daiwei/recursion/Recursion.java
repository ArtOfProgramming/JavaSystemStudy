package com.daiwei.recursion;

/**
 * 递归应用
 * 1.8皇后，汉罗塔，阶乘，迷宫，球和篮子问题
 * 2. 快排，归并排序，二分查找，分治算法
 * 3. 将用栈解决的问题 递归代码比较简洁
 * 递归5个规则
 * 1.执行一个方法时，就创建一个新的受保护的独立空间（站空间）
 * 2.方法的局部变量是独立的
 * 3.方法的引用变量是共享的
 * 4.递归必须向退出递归的条件逼近
 * 5.当一个方法调用完毕或者return，就会返回
 */

public class Recursion {

    public static void main(String[] args) {
//        printRecursion(10);
//        System.out.println(factorial(3));
        maze();

//        int[] map = new int[8];
//        eightQueen(map, 0);
//        System.out.println(count);

    }

    public static void printRecursion(int n) {
        if (n > 2) {
            printRecursion(n - 1);
        }
        System.out.println(n);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void maze() {
        int[][] map = new int[8][7];
        // 设置墙
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][map[i].length - 1] = 1;
        }
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[map.length - 1][i] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[5][3] = 1;
        map[5][4] = 1;
        map[5][5] = 1;

        setWay(map, 1, 1);
        for (int i = 0; i < map.length; i++) {
            for (int i1 = 0; i1 < map[i].length; i1++) {
                System.out.print(map[i][i1] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 使用递归回溯来给小球找路
     * 说明
     * 1.map表示地图
     * 2.i,j表示从地图什么位置开始出发
     * 3.如果小球能到达map[6][5]则说明路通
     * 4.约定：当map[i][j]为0表示该点没走过，为1表示墙，为2表示通路可以走，为3表示该店已经走过但是走不通
     * 5.在走迷宫时，需要确定一个策略 下->右->上->左
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                // 下->右->上->左
                map[i][j] = 2;
                if(setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                }
                map[i][j] = 3;
                return false;
            } else {
                return false;
            }
        }
    }


    // TODO: 寻找最短路径
    public static void findShortestPath() {

    }

    /**
     * 八皇后问题
     * 1.第一个皇后放到第一行第一列
     * 2.第二个皇后放在第二行第一列、然后判断是否OK，如果不OK，继续放，找到一个合适的位置
     * 3.继续放第三个皇后，还是找到一个合适的位置
     * 4.当得到一个正确解后，回到上一个栈，开始回溯，找到第一个皇后放到第一列的全部解
     * 5.然后回头继续讲第一行皇后换到第二行，后续继续1,2,3,4
     */
    private static int count = 0;
    public static void eightQueen(int[] map, int cur) {
        if (cur == map.length) {
            for (int i = 0; i < map.length; i++) {
                System.out.print(map[i]);
            }
            System.out.println();
            count++;
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (isOk(map, cur, i)) {
                map[cur] = i;
                eightQueen(map, cur + 1);
            }
        }
    }


    public static boolean isOk(int[] map, int cur, int value) {
        if (cur > 0) {
            for (int i = 0; i < cur; i++) {
                if (map[i] == value || Math.abs(i - cur) == Math.abs(map[i] - value)) {
                    return false;
                }
            }
        }
        return true;
    }
}
