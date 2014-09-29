package com.facebook;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class SpiralMatrixII {
    public static int n = 4;

    public static int[][] generateSpiralMatrix(int n) {

        if(n < 1) {
            return null;
        }
        int[][] input = new int[n][n];
        int count = 1;

        int rowStart = 0;
        int rowEnd = n-1;
        int columnStart = 0;
        int columnEnd = n-1;

        while(rowStart<= rowEnd && columnStart<=columnEnd) {
            // print the upper edge
            for(int i = columnStart; i <= columnEnd; i++) {
                input[rowStart][i] = count++;
            }
            rowStart++;

            // print the right edge
            for( int i = rowStart; i<= rowEnd; i++) {
                input[i][columnEnd] = count++;
            }
            columnEnd --;

            //print the bottom edge if required
            if(rowStart <= rowEnd) {
                for( int i = columnEnd; i >= columnStart; i--) {
                    input[rowEnd][i] = count++;
                }
                rowEnd--;
            }

            //print the left edge if necessary
            if(columnStart<=columnEnd) {
                for(int i = rowEnd; i>= rowStart; i--) {
                   input[i][columnStart] = count++;
                }
                columnStart++;
            }

        }

        return input;
    }

    public static void main(String[] args) {
       int[][] result =  generateSpiralMatrix(n);
       for(int i =0; i< n; i++) {
           for( int j = 0; j< n; j++) {
               System.out.print(" "+ result[i][j]+ " ");
           }
           System.out.println();
       }

    }

}
