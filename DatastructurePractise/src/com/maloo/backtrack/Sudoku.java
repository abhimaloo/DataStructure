package com.maloo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * This problem is to fill a sudoku 9x9 grid using backtracking algorithm
 */
public class Sudoku {
    int [][] board = new int[9][9];

    public static boolean isValid(int[][] board, Point p, int number) {

        //it has to be empty
        if(board[p.x][p.y] !=0) {
            return false;
        }

        //check for row and column .. NO elements of same type in row or column
        for(int i =0; i<9; i++) {
            if(board[i][p.y] == number || board[p.x][i] == number) {
                return false;
            }
        }

        //check for the box .. No element in smaller 3x3 grid
        if(usedInBox(board,p.x - (p.x % 3) , p.y - (p.y % 3), number)) {
            return false;
        }


       return true;
    }


    public static boolean usedInBox(int [][]board, int boxStartRow, int boxStartCol, int number)
    {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (board[row+boxStartRow][col+boxStartCol] == number)
                    return true;
        return false;
    }

    public static boolean isGameOver(int [][] board) {
        for(int i =0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if(board[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static int findFrequency(int[][] board, int number){
         int sum = 0;
        for(int i=0; i <9; i ++) {
            for(int j=0; j<9; j ++) {
                if(board[i][j] ==number){
                    sum++;

                }
            }
        }

        return sum;
    }

    public static List<Point> findAllPossible(int[][] board, int number) {

        ArrayList<Point> points = new ArrayList<Point>();
        for(int i=0; i <9; i ++) {
            for(int j=0; j<9; j ++) {
                if(isValid(board, new Point(i,j),number)){
                    points.add(new Point(i,j));
                }
            }
        }
       return  points;
    }

    public static boolean solveSudoku(int[][] board, Point startPoint, int number) {

        if(isGameOver(board))  {
            return true;
        }
        if(findFrequency(board,number)>=9){
            number ++;
        }

        //find all possible next numbers
        List<Point> points = findAllPossible(board,number);

        for(Point p: points) {
            //record it
            recordMove(board,p,number);
            //recursive check whether there is a solution or not
            if(solveSudoku(board,p,number)) {
                return true;
            } else {
                //backtrack
                unrecordMove(board,p);
            }
        }
        return false;

    }

    private static void recordMove(int[][] board, Point point, int num) {

        board[point.x][point.y] = num;
        //System.out.println("---Record Point - " + point.x + ":" + point.y + "----");
        //printBoard(board);
    }

    private static int unrecordMove(int[][] board, Point point) {
        int num =   board[point.x][point.y];
        board[point.x][point.y] = 0;
        //System.out.println("---UnRecord Point - "+point.x+":"+ point.y+"----");
        //printBoard(board);
        return num;
    }

    public static void printBoard(int [][] board) {
        for(int i =0; i<9; i++) {
            for(int j=0; j<9; j++) {
                System.out.print(" "+board[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    public static void main(String args[]) {
        int[][] board= {{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0,0}};

        solveSudoku(board,new Point(0,0), 1);
        printBoard(board);
    }

    static class Point {
        public int x;
        public  int y;
        public Point(int x, int y) {
            this.x = x ;
            this.y = y;
        }
    }

}

