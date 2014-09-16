package com.crackCode.arraysAndstrings;

/**
 * Created by abhimaloo on 9/13/14.
 */
public class SetZerosInMatrix {
    public static int[][] input = {{1,2,4,5, 6, 7}
                                  ,{5,6,0,1, 6, 7},
                                   {0,9,8,7, 6, 7},
                                   {3,6,9,2, 1, 7}};


    /**
     * Solution is to do it in place using minimum extra space ..
     * We use first column and first row as place to record whether a a particular row/column should have all the elements as zero or not
     * @param input
     */
    public static void setZeros(int[][] input) {
        boolean firstRowHasZeros = false;
        boolean firstColumnHasZeros = false;

        // first check whether the first row or column has zero's or not
        for( int i=0; i< input[0].length; i++) {
            if(input[0][i] == 0) {
                firstRowHasZeros = true;
            }
        }

        for( int i=0; i< input.length; i++) {
            if(input[i][0] == 0) {
                firstColumnHasZeros = true;
            }
        }

        // use first column and first row to mark ros/column to zero
        for( int i = 1; i < input.length; i++) {
            for(int j = 1 ; j< input[0].length; j++) {
                if(input[i][j] == 0) {
                    input[i][0] = 0;
                    input[0][j] = 0;
                }
            }
        }

        // use the previous recording to mark all the elements to zero
        for( int i = 1; i< input.length; i++) {
            for(int j = 1 ; j< input[0].length; j++) {
                if(input[i][0] == 0 || input[0][j] == 0) {
                    input[i][j] = 0;
                }
            }
        }


        // set first row as zero
        if(firstRowHasZeros) {
            for( int i = 0; i< input[0].length; i++) {
                input[0][i] = 0;
            }
        }

        if(firstColumnHasZeros) {
            for( int i = 0; i< input.length; i++) {
                input[i][0] = 0;
            }
        }

    }

    public static void main(String[] args) {


        setZeros(input);
        for( int i = 0; i< input.length; i++) {
            for(int j = 0 ; j< input[0].length; j++) {
                System.out.print(" " + input[i][j] + " ");
            }
            System.out.println();
        }
    }


}
