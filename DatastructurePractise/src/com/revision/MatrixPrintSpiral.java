package com.revision;

/**
 * Created by abhimaloo on 9/20/14.
 */
public class MatrixPrintSpiral {
    public static int [][] input =  {{1,2,3,4,20},
            {5,6,7,8,21},
            {9,10,11,12,22},
            {13,14,15,16,23}
    };

    public static void printMatrix(int[][] input) {

        if(input == null) {
            return;
        }
        if(input.length == 0){
            return;
        }

        int rowStart = 0;
        int rowEnd = input.length-1;
        int columnStart = 0;
        int columnEnd = input[0].length - 1;

        while(rowStart <= rowEnd && columnStart <= columnEnd) {

            // print the first row from the remaining rows
            for( int i = columnStart; i<= columnEnd ; i++) {
                System.out.println(input[rowStart][i]);
            }
            rowStart++;

            for( int i = rowStart; i<= rowEnd; i++) {
                System.out.println(input[i][columnEnd]);
            }
            columnEnd --;

            if(rowStart<= rowEnd) {
                for( int i = columnEnd; i>= columnStart; i--) {
                    System.out.println(input[rowEnd][i]);
                }
                rowEnd --;
            }

            if(columnStart <= columnEnd) {
                for( int i = rowEnd; i>= rowStart; i--) {
                    System.out.println(input[i][columnStart]);
                }
                columnStart++;
            }

        }

    }

    public static void main(String[] args) {
       printMatrix(input);
    }

}
