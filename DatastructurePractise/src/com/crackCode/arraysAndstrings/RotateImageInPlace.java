package com.crackCode.arraysAndstrings;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees.
 * Can you do this in place?
 * Created by abhimaloo on 9/13/14.
 */
public class RotateImageInPlace {
    public static int[][] input = {{1,2,4,5}
                                 ,{5,6,7,6},
                                  {8,9,8,7},
                                  {3,6,9,2}};

    public static void rotateImage(int[][] input) {

        for( int layer = 0; layer < input.length/2; layer++) {

            int start = layer;
            int end = input.length - layer - 1;

            int offset = end - start;
            for( int i = 0; i< offset; i++ ) {
                int temp = input[start+i][start];
                input[start+i][start] = input[end][start + i];
                input[end][start + i] = input[end - i][end];
                input[end - i][end] = input[start][end - i];
                input[start][end - i] = temp;

            }

        }
    }

    public static void main(String[] args) {
       rotateImage(input);
    }
}
