package com.interview.arrays;

/**
 * Given a boolean matrix mat[M][N] of size M X N,
 * modify it such that if a matrix cell mat[i][j] is 1 (or true)
 * then make all the cells of ith row and jth column as 1.
 *
 * The matrix
 * 0 0 0
 * 0 0 1
 * should be changed to following
 * 0 0 1
 * 1 1 1
 *
 * Created by abhimaloo on 7/24/14.
 */
public class BooleanMatrixMarkRowColumn {
    public static int[][] input = {{0,0,0},
                                   {0,0,1}};

    public static void markRowColumn(int[][] input) {
        boolean[] row = new boolean[input.length];
        boolean[] column = new boolean [input[0].length];

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if(input[i][j] ==1){
                    row[i] |= true;
                    column[j] |= true;
                }

            }
        }

        for( int i=0; i< row.length; i++){
            for (int j = 0; j < column.length; j++) {
                if(row[i]){
                    input[i][j] = 1;
                }

            }
        }


        for( int i=0; i< column.length; i++){
            for (int j = 0; j < row.length; j++) {
                if(column[i]){
                    input[j][i] = 1;
                }

            }
        }

    }

    public static void main(String[] args) {
        markRowColumn(input);
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                System.out.print(" " + input[i][j] + " ");
            }
            System.out.println();
        }
    }

}
