package com.leetcode2019.medium;

/*
https://leetcode.com/problems/set-matrix-zeroes/
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input:
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output:
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input:
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output:
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
 */
public class SetMatrixZero {
    public static void setZeroes(int[][] matrix) {
        // first make sure there are no zeros in first row and first column
        boolean hasZeroInRow = false;
        boolean hasZeroInCol = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0 && j == 0) {
                        hasZeroInCol = true;
                        hasZeroInRow = true;
                    } else if (i == 0) {
                        hasZeroInCol = true;
                    } else if (j == 0) {
                        hasZeroInRow = true;
                    } else {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // cleanup up the first row and first colunn
        for (int i = 0; i < matrix.length; i++) {
            if (hasZeroInRow) {
                matrix[i][0] = 0;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if (hasZeroInCol) {
                matrix[0][i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] input = new int[][]{{0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}};
        setZeroes(input);
    }
}
