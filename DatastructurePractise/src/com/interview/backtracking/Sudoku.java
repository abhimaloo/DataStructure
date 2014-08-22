package com.interview.backtracking;

/**
 * Given a partially filled 9×9 2D array ‘grid[9][9]‘,
 * the goal is to assign digits (from 1 to 9) to the empty cells so that
 * every row, column, and subgrid of size 3×3 contains exactly one instance of the digits from 1 to 9.
 *
 * http://www.geeksforgeeks.org/backtracking-set-7-suduku/
 * Created by abhimaloo on 8/21/14.
 */
public class Sudoku {
    public static int[][] board={{0,0,0,0,0,0,0,0,0},
                                 {0,0,0,0,0,0,0,0,0}
                                ,{0,0,0,0,0,0,0,0,0}
                                ,{0,0,0,0,0,0,0,0,0}
                                ,{0,0,0,0,0,0,0,0,0}
                                ,{0,0,0,0,0,0,0,0,0}
                                ,{0,0,0,0,0,0,0,0,0}
                                ,{0,0,0,0,0,0,0,0,0}
                                ,{0,0,0,0,0,0,0,0,0}};
    public static int n = 9;

    /**
     * trick is to apply backtracking
     * we will take number of each box index from 0 to 80
     * run all the possible values of number and record a valid value
     * recurse to next box else backtrack
     *
     * @param index
     * @param board
     * @return
     */
    public static boolean solveSudoku(int index, int[][] board) {
        if(index == board.length * board.length) {
            printBoard(board);
            return true;
        } else {
            //cool trick .. convert index to x, y. ex - index = 12 so x = index/n = 12/9 = 1 and y = index % n  = 12 % 9 =  3 so x, y = 1,3
            int x = index /n;
            int y = index % n;
            // try all possible numbers
            for(int i=1; i <= n; i++) {
                // if this number becomes a valid move
                if(isValidMove(x,y,board, i)) {
                    // record the move
                    board[x][y] = i;
                    //printBoard(board);
                    //recurse
                    if(solveSudoku(index+1, board)) {
                        return true;
                    }

                    solveSudoku(index+1, board);
                    //backtrack and unrecord
                    board[x][y] = 0;
                    //printBoard(board);
                }
            }

        }
        return false;
    }

    private static boolean isValidMove(int x, int y, int[][] board, int number) {
        //check if the row or column contains the same number
        for( int i=0; i< n; i++) {
            if(board[x][i] == number || board[i][y] ==number) {
                return false;
            }
        }

        // we want to find the left Top coordinates of the box in which the current x,y element is
        // since there cannot be duplicate element in the box
        // very cool trick  -   x - x%sqrt(n) - > leftTopX
        int boxDimension = new Double(Math.sqrt(n)).intValue();
        int xleftTop = x - (x % boxDimension);
        // same trick  - y - y%sqrt(n)  - > yTopLeft
        int yleftTop = y - (y % boxDimension);

        // fromt left top to right bottom .. check in the box
        for( int i = xleftTop; i < xleftTop + boxDimension; i++) {
            for(int j= yleftTop; j < yleftTop + boxDimension; j++) {
                if(board[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(int [][] board) {

        for(int i =0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(" "+board[i][j] + " ");
            }
            System.out.println(" ");
        }

        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        solveSudoku(0, board);
    }

}
