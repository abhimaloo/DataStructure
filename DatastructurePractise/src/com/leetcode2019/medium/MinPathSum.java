package com.leetcode2019.medium;

/*
https://leetcode.com/problems/minimum-path-sum/
 */
public class MinPathSum {
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int[][] dp = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(i - 1 >= 0 ? dp[i - 1][j] : Integer.MAX_VALUE, j - 1 >= 0 ? dp[i][j - 1] : Integer.MAX_VALUE);
                }
            }
        }

        return dp[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
    }
}
