package com.revision;

/**
 * Created by abhimaloo on 9/17/14.
 */
public class PrintMatrixSpirally {
    public static int [][] input =  {{1,2,3,4,20},
                                    {5,6,7,8,21},
                                    {9,10,11,12,22},
                                    {13,14,15,16,23}};

    public static void printMatrixSpirally(int[][] input) {
        for(int level = 0; level < input.length/2; level++) {
            int start = level;
            int endRow = input.length - level - 1;
            int endColumn = input[0].length - level - 1;

            for( int i = 0; i< endColumn - start  ;i++) {
                System.out.println(input[start][start+i]);
            }

            for( int i = 0; i< endRow - start;i++) {
                System.out.println(input[start+i][endColumn]);
            }

            for( int i = 0; i< endColumn - start ;i++) {
                System.out.println(input[endRow][endColumn-i]);
            }

            for( int i = 0; i< endRow - start ;i++) {
                System.out.println(input[endRow-i][start]);
            }


        }

    }


    public static void main(String[] args) {
        printMatrixSpirally(input);
    }

}
