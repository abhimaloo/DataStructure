package com.interview.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other
 *
 * http://www.geeksforgeeks.org/backtracking-set-3-n-queen-problem/
 * Created by abhimaloo on 8/21/14.
 */
public class EightQueenProblem {

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] board = {{0,0,0,0,0,0,0,0},
                                   {0,0,0,0,0,0,0,0}
                                  ,{0,0,0,0,0,0,0,0}
                                  ,{0,0,0,0,0,0,0,0}
                                  ,{0,0,0,0,0,0,0,0}
                                  ,{0,0,0,0,0,0,0,0}
                                  ,{0,0,0,0,0,0,0,0}
                                  ,{0,0,0,0,0,0,0,0}};

    public static boolean placeQueen(int[][] board, int x, int y, int row) {
        if(row == board.length) {
            return true;
        } else {

            for(Pair move : getNextValidMoves(board, x, y, row)) {
                // record the move or make the move
                board[move.x][move.y] = 1;
                printBoard(board);
                // recurse
                if(placeQueen(board, move.x, move.y, row+1)) {
                    return true;
                }

                // backtrack by unrecording last move
                board[move.x][move.y] = 0;
                printBoard(board);
            }

        }

        return false;
    }

    public static List<Pair> getNextValidMoves(int[][] board, int x, int y, int row) {
        List<Pair> nextMoves = new ArrayList<>();
        // go through all possible option on row
        for( int i = 0; i< board.length; i++) {
            // check if its a valid and unoccupied move
            if(isValid(row,i)) {
                // add the move
                nextMoves.add(new Pair(row, i));
            }
        }
        return nextMoves;
    }

    private static boolean isValid(int x, int y) {


        for( int i=0; i< board.length; i++) {
            //check if row and column are occupied then return false
            if(board[x][i] == 1 || board[i][y] == 1) {
                return false;
            }

            // check right bottom diagonal is occupied or not
            if(x+i < board.length && y+i < board.length) {
                if(board[x+i][y+i] == 1){
                    return false;
                }
            }

            // right top diagonal
            if(x+i < board.length && y-i >= 0) {
                if(board[x+i][y-i] == 1){
                    return false;
                }
            }

            // left top diagonal
            if(x-i >= 0 && y-i >= 0) {
                if(board[x-i][y-i] == 1){
                    return false;
                }
            }

            // left bottom diagonal
            if(x-i >= 0 && y+i < board.length) {
                if(board[x-i][y+i] == 1){
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(int [][] board) {

        for(int i =0; i<8; i++) {
            for(int j=0; j<8; j++) {
                System.out.print(" "+board[i][j] + " ");
            }
            System.out.println(" ");
        }

        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        board[0][0] = 1;
        placeQueen(board, 0,0, 1);
    }


}
