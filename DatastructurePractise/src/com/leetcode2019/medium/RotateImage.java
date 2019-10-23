package com.leetcode2019.medium;

/*
https://leetcode.com/problems/rotate-image/
 */
public class RotateImage {
    /*
        Solution is to go layer by layer.
     */
    public static void rotate(int[][] matrix) {
        //run this outer loop for number of layers time
        for (int left = 0; left <= matrix[0].length / 2; left++) {
            //this is the right (width) index for every layer
            int right = matrix[0].length - left - 1;
            for (int i = 0; i < right - left; i++) {
                int temp = matrix[left][left + i]; // THIS IS LEFT TOP
                matrix[left][left + i] = matrix[right - i][left]; // Left Bottom to Left Top
                matrix[right - i][left] = matrix[right][right - i]; // Right bottom to left bottom
                matrix[right][right - i] = matrix[left + i][right]; // right top  to left bottom
                matrix[left + i][right] = temp; // left top to right top
            }
        }
    }

    public static void print(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(" " + matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {{2, 29, 20, 26, 16, 28},
                {12, 27, 9, 25, 13, 21},
                {32, 33, 32, 2, 28, 14},
                {13, 14, 32, 27, 22, 26},
                {33, 1, 20, 7, 21, 7},
                {4, 24, 1, 6, 32, 34}};

        rotate(matrix);
    }
}
