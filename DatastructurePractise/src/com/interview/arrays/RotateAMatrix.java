package com.interview.arrays;

/**
 * Task is to rotate a square matrix by 90 degree clockwise
 * Ex -   1  2  4  5
 *        5  6  7  6
 *        8  9  8  7
 *        3  6  9  2
 *
 * Rotated view -
 *        3  8  5  1
 *        6  9  6  2
 *        9  8  7  4
 *        2  7  6  5
 *
 * Created by abhimaloo on 8/6/14.
 */
public class RotateAMatrix {
    public static int[][] input = {{1,2,4,5}
                                  ,{5,6,7,6},
                                   {8,9,8,7},
                                   {3,6,9,2}};

    /**
     * very tricky - do the rotation layer by layer
     * layer one is outer circumference . layer 2 in inner square and so on ..
     * No of layers in a square matrix of nxn is floor of n/2
     * @param input
     */
    public static void rotateInPlace(int[][] input) {

        // idea is to rotate layer by layer .. Number of layers in a matrix is floor of n/2
        for( int layer = 0; layer <input.length/2; layer++) {
            int start = layer;
            int end = input.length-layer-1;
            for( int i=start; i<end; i++) {
                int offset = i-start;

                //save the top right element
                int top = input[i][end];

                //move the left top to right top
                input[i][end] = input[start][i];

                // move bottom left to left top
                input[start][i] = input[end-offset][start];

                //move bottom right to bottom left
                input[end-offset][start]  = input[end][end-offset];

                // move top right to bottom right
                input[end][end-offset] = top;


            }

        }



    }

    public static void print(int [][] input) {
        for( int i=0; i<input.length; i++) {
            System.out.println();
            for( int j=0; j< input.length; j++) {
                System.out.print(" "+ input[i][j]+ " ");
            }

        }
    }

    public static void main(String[] args) {
        print(input);
        rotateInPlace(input);
        System.out.println("------------After rotation -----");
        print(input);
    }

}
