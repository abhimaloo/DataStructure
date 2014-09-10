package com.leetcode;


/**
 * In a Pascal triangle, each element is the sum of the element directly above it and
 the element to the left of the element directly above it (if any). A
 Pascal triangle where n=7 is shown below.

 http://www.dreamincode.net/forums/topic/30725-pascal-triangle/
 * Created by abhishekm787 on 8/22/14.
 */
public class PascalTriangle {

    /**
     * trick is to create  two dimensional grid for nxn
     * now use two for loops .
     * first one for number of rows
     * other for numbers of column
     *
     * in pascals triangle i,j = (i-1, j) + (i-1, j-1)  if i-1 and j-1 >= 0
     * else i,j = 1
     *
     * @param n
     */
    public static void printPascalTriangle(int n) {
        int[][] grid = new int[n][n];
        // first loop for number of rows
        for( int i=0; i< grid.length; i++) {
            //second loop for number of elements
            for( int j = 0; j < i+1; j++) {
                // i-1 and j-1 are greater or equals to zero
                if(i-1 >= 0 && j-1 >= 0) {
                    //apply pasacl'a formulae
                   grid[i][j] = grid[i-1][j] + grid[i-1][j-1];
                } else {
                    // else assign it to zero
                    grid[i][j] = 1;
                }

            }
        }

        for( int i = 0; i< n; i++) {
            for (int j = 0; j< n; j++) {
                if(grid[i][j]!=0) {
                    System.out.print(" " + grid[i][j] + " ");
                }
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
         printPascalTriangle(7);
    }

}
