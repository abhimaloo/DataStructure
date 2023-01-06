package com.leetcode2022.blind75.matrix;

/*
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.

You must do it in place.



Example 1:


Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
Example 2:


Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]


Constraints:

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1

 */
public class SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        // idea is to use the first row and first column to mark zeros for the complete rows or columns
        boolean firstRowHasZero = false;
        boolean firstColumnHasZero = false;

        // setting zeros in first row and first column for any zeros
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0 && j == 0) {
                        firstRowHasZero = true;
                        firstColumnHasZero = true;
                    } else if (i == 0) {
                        firstColumnHasZero = true;
                    } else if (j == 0) {
                        firstRowHasZero = true;
                    } else {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        // now set zeros if the top of the column or left of the row has zero
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // now fix the first row and first column
        for (int i = 0; i < matrix.length; i++) {
            if (firstRowHasZero) {
                matrix[i][0] = 0;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if (firstColumnHasZero) {
                matrix[0][i] = 0;
            }
        }
    }
}
