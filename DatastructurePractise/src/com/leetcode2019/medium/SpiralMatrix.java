package com.leetcode2019.medium;

/*
https://leetcode.com/problems/spiral-matrix/
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public static void spiral(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int columnStart = 0;
        int columnEnd = matrix[0].length - 1;

        while (rowStart <= rowEnd && columnStart <= columnEnd) {
            for (int i = columnStart; i <= columnEnd; i++) {
                System.out.print(" " + matrix[rowStart][i] + " ");
            }
            rowStart++;


            for (int i = rowStart; i <= rowEnd; i++) {
                System.out.print(" " + matrix[i][columnEnd] + " ");
            }
            columnEnd--;

            if (rowEnd >= rowStart) {
                for (int i = columnEnd; i >= columnStart; i--) {
                    System.out.print(" " + matrix[rowEnd][i] + " ");
                }
                rowEnd--;
            }

            if (columnStart <= columnEnd) {
                for (int i = rowEnd; i >= rowStart; i--) {
                    System.out.print(" " + matrix[i][columnStart] + " ");
                }
                columnStart++;
            }
        }
    }

    public static void main(String[] args) {
        int[][] input = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        spiral(input);
    }
}
