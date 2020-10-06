package com.daiwei.question372;

/**
 * 问题372
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 * 欧拉公式
 * 降幂
 */
public class Question372 {

    public static void main(String[] args) {
        System.out.println(superPow(10, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    public static int superPow(int a, int[] b) {
        int c = ol(1337);
        int sum = 0;
        for (int i = 0; i < b.length; i++) {
            sum = (sum * 10 + b[i]) % c;
        }
        sum += c;
        return q((long) a, sum, 1337);
    }

    private static int q(long x, int y, int M) {
        long res = 1;
        while (y > 0) {
            if (y % 2 > 0) {
                res = res % M * x % M;
            }
            x = x % M * x % M;
            y /= 2;
        }
        return (int) res;
    }

    private static int ol(int x) {
        int res = x;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                res = res - res / i;
                while (x % i == 0) {
                    x /= i;
                }
            }
        }
        if (x > 1) {
            res = res - res / x;
        }
        return res;
    }
}
