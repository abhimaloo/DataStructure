package com.leetcode2022.blind75.matrix;

import java.util.ArrayList;
import java.util.List;

/*
Given an m x n matrix, return all elements of the matrix in spiral order.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:


Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> response = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return response;
        }

        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            //first print the top row
            for (int i = colStart; i <= colEnd; i++) {
                response.add(matrix[rowStart][i]);
            }
            rowStart++;

            // now print the right edge
            for (int i = rowStart; i <= rowEnd; i++) {
                response.add(matrix[i][colEnd]);
            }
            colEnd--;

            //print the bottom edge if needed
            if (rowStart <= rowEnd) {
                for (int i = colEnd; i >= colStart; i--) {
                    response.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }

            // print the left edge if needed
            if (colStart <= colEnd) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    response.add(matrix[i][colStart]);
                }
                colStart++;
            }
        }

        return response;
    }
}
