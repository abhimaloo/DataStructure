package com.revision;

/**
 * Created by abhimaloo on 9/24/14.
 */
public class PascalTriangle {

    public static int[][] pascalTriangle(int n){
        int[][] pascal = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i+1; j++) {
                if(i-1 >=0 && j-1>=0) {
                    pascal[i][j] = pascal[i-1][j]+ pascal[i-1][j-1];
                } else {
                    pascal[i][j] = 1;
                }
            }
        }

        return pascal;
    }

    public static void main(String[] args) {
        int[][] pascal = pascalTriangle(7);
        for(int i = 0; i < pascal.length; i++) {
            for (int j = 0; j < i+1; j++) {
                System.out.print(" "+ pascal[i][j]+" ");
            }
            System.out.println();
        }

    }

}
