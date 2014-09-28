package com.facebook;

/**
 * Created by abhimaloo on 9/27/14.
 */
public class PascalTriangle {
    public static void pascalTriangle(int n) {
        int[][] pascal = new int[n+1][n+1];

        for( int i = 0; i< n+1; i++) {
            for( int j = 0; j< i; j++) {

                if(i-1 >= 0 && j-1 >= 0) {
                    pascal[i][j] = pascal[i-1][j] + pascal[i-1][j-1];
                } else {
                    pascal[i][j] = 1;
                }

            }
        }

        for( int i = 0; i< n+1; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" "+ pascal[i][j]+" ");
            }
            System.out.println();
        }


    }

    public static void main(String[] args) {
        pascalTriangle(6);
    }

}
