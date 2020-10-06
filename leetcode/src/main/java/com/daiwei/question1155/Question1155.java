package com.daiwei.question1155;

public class Question1155 {

    public static void main(String[] args) {
        System.out.println(numRollsToTarget(30, 30, 500));
    }

    public static int numRollsToTarget(int d, int f, int target) {
        int mod = (int)(Math.pow(10, 9) + 7);
        // 动态规划
        // arr[i][j]表示i次投骰子，目标为j的可能性，当第i次投为k时（0 < k <= min(f, j)), 次数为arr[i-1][j-k]，遍历k，获得arr[i][j]。
        int[][] arr = new int[31][1001];
        for (int j = 1; j <= Math.min(f, target); j++) {
            arr[1][j] = 1;
        }

        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= d * f; j++) {
                for (int k = 1; k <= Math.min(f, j); k++) {
                    arr[i][j] = (arr[i][j] + arr[i-1][j-k]) % mod;
                }
            }
        }
        return arr[d][target];
    }
}
