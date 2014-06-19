package com.maloo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a famous problem called Eight Queens problem. Objective is to arrange
 * eight queens in a chess board so that they do not kill each other
 */
public class EightQueenProblem {


    public static boolean isValidMove(int[][] board, int x, int y) {


        for(int i =0; i < board.length; i ++) {    //checks if row or column has any placed queen

            if((board[x][i]==1 ) || (board[i][y]==1 )) {
                return false;
            }
            if(i==0){
                continue;
            }

            if(x+i<board.length && y+i<board.length){
                if(board[x+i][y+i]==1) {
                    return false;
                }

            }
            if(x-i >= 0 && y-i >= 0) {
                if(board[x-i][y-i]==1) {
                    return false;
                }

            }

            if(x+i<board.length && y-i>=0) {
                if(board[x+i][y-i]==1) {
                    return false;
                }

            }
            if(x-i>=0 && y+i<board.length) {
                if(board[x-i][y+i]==1) {
                    return false;
                }

            }

        }

        return true;

    }



    public static boolean isGameOver(int[][] board) {
        for(int i =0; i<8; i++) {
            int queenCount =0;
            for(int j=0; j<8; j++) {
                if(board[i][j] == 1){
                    queenCount ++;
                }
            }
            if(queenCount==0 || queenCount >1 ){
                return false;
            }

        }

        return true;
    }


    public static List<Point> findAllValidPossibleNextPoints(int [][] board , int row) {

        List<Point> points = new ArrayList<Point>();
        for(int i =0; i<8; i++) {
                if(board[row][i] != 1 && isValidMove(board,row,i)){
                    points.add(new Point(row,i));
                }

        }

        return points;

    }

    public static boolean placeQueen(int[][] board, int row) {
        //terminating condition
        if(isGameOver(board)) {
            return true;
        }

        //find all next possible destinations
        List<Point> nextPoints = findAllValidPossibleNextPoints(board, row) ;

        for(Point p : nextPoints ) {
            recordMove(board,p);
            if(placeQueen(board,row+1)) {
                return true;
            } else {
                unrecordMove(board,p);
            }

        }

        return false;

    }

    public static void recordMove(int[][] board, Point p) {
        board[p.x][p.y] = 1;
       System.out.println("----Recording Move : "+p.x+","+p.y+" ----");
       printBoard(board);


    }

    public static void unrecordMove(int[][] board, Point p) {
      System.out.println("----UnRecording Move : "+p.x+","+p.y+"----");
      board[p.x][p.y] = 0;
      printBoard(board);
    }

    public static void printBoard(int [][] board) {
        for(int i =0; i<8; i++) {
            for(int j=0; j<8; j++) {
                System.out.print(" "+board[i][j] + " ");
            }
            System.out.println(" ");
        }
    }



    public static void main(String args[]) {
        int[][] board= {{0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0}};

        placeQueen(board,0);
        System.out.println("Solution is : ");
        printBoard(board);

    }

    private static class Point {
        public int x;
        public  int y;
        public Point(int x, int y) {
            this.x = x ;
            this.y = y;
        }
    }
}
