package com.leetcode2022.blind75.matrix;

/*
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
public class RotateMatrix {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;

        // idea is to rotate layer by layer .. Number of layers in a matrix is floor of n/2
        for (int start = 0; start <= matrix[0].length / 2; start++) { // start will denote the row
            int end = matrix[0].length - start - 1; // this is the width of the row (columns).. on every layer the width will reduce
            // this will determine number of swaps needed per layer
            for (int i = 0; i < end - start; i++) {
                //save top left
                int temp = matrix[start][start + i];
                // move bottom left to top left. then column would decrease but row will stay same
                matrix[start][start + i] = matrix[end - i][start];
                // move bottom right to bottom left
                matrix[end - i][start] = matrix[end][end - i];
                // move top right to bottom right
                matrix[end][end - i] = matrix[start + i][end];
                // fill the bottom right with temp
                matrix[start + i][end] = temp;
            }
        }
    }
}
