package com.crackCode;

/**
 * Created by abhimaloo on 9/16/14.
 */
public class TicTacToe {

    /**
     * trick is top traverse the board . Use 0 for unused cell, 1 for player 1 and n+1 for player 2 if n X n is the dimension of tic tac toe board
     * @param board
     * @param n
     * @return
     */
    public static boolean hasWon(int[][] board , int n) {

        for( int i=0; i< n; i++) {

            int rowHit = 0;
            int columnHit = 0;
            int diagonal1Hit = 0;
            int diagonal2Hit = 0 ;

            for( int j = 0; j< n ; j++) {
                // for rows  // summing value of one row
                rowHit += board[i][j];
                // for columns , summing value of 1 column
                columnHit += board[j][i];


                if(i ==0) {   //reading value of left top to  bottom right diagonal
                    diagonal1Hit += board[i+j][i+j];
                }

                if(i == n-1) {
                    diagonal2Hit += board[j][i-j];
                }

            }

            if(rowHit == n || rowHit == (n+1)*n || columnHit == n || columnHit == (n+1)*n
                    || diagonal1Hit == n || diagonal1Hit == (n+1)*n || diagonal2Hit == n || diagonal2Hit == (n+1)*n) {
                return true;
            }

        }

        return false;

    }

    public static void main(String[] args) {
        int[][] board = {{4,4,1},
                         {4,4,1},
                         {1,1,0}};

        System.out.println(hasWon(board, 3));

    }

}
