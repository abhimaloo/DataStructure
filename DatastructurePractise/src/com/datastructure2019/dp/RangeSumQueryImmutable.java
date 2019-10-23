package com.datastructure2019.dp;

/*
https://leetcode.com/problems/range-sum-query-2d-immutable/
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.

 */
public class RangeSumQueryImmutable {
    /*
    Solution is to use DP to pre compute sums
    DP table would look like this  -
       Lets take a DP array of riw and column 1 greater than original

          0  1   2   3  4  5
      0   0  0   0   0  0  0
      1   0  3   3   4  8  10
      2   0  8   14  x
      3   0  9
      4   0  13
      5   0  14


      Here DP[i, j] represents the sum of matrix starting from 0,0 to i-1, j-1 index in main array

      As a first step, Fill in first row and columns with 0
      now - > DP [i][j]  = DP[i-1][j] + DP[i][j-1] + matrix[i-1][j-1] - DP[i-1][j-1]
      This formula comes due to this
      DP[i-1][j] represent the left edge of the rectangle, DP[i][j-1] represent the top edge, here left top part is repeated in both the sum
      hence we add bottom right coordinate and subtrack dp[i-1][j-1] which is topleft rectangle


     */
    int[][] dp;

    public RangeSumQueryImmutable(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 0;
        }

        // fill in the rest of the DP table
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + matrix[i - 1][j - 1] - dp[i - 1][j - 1];
            }
        }

    }

    // now sum of any rectangle would be
    //sum  -> dp[r2][c2] - dp[r2][c1-1] - dp [r1-1][c2] + dp [r1-1][c1-1]
    public int sumRegion(int r1, int c1, int r2, int c2) {
        if (dp.length == 0) return 0;
        if (r1 >= 0 && r1 < dp.length - 1 && r2 >= 0 && r2 < dp.length - 1 && c1 >= 0 && c1 < dp[0].length - 1 && c2 >= 0 && c2 < dp[0].length - 1) {
            r1++;
            c1++;
            r2++;
            c2++;
            return dp[r2][c2] - dp[r2][c1 - 1] - dp[r1 - 1][c2] + dp[r1 - 1][c1 - 1];
        }

        return 0;

    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        RangeSumQueryImmutable obj = new RangeSumQueryImmutable(new int[][]{{1}, {-7}});
        System.out.println(obj.sumRegion(0, 0, 1, 0));
    }
}
