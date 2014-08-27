package com.leetcode;


/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

 A region is captured by flipping all 'O's into 'X's in that surrounded region.
 X X X X
 X O O X
 X X O X
 X O X X

 After running the fucntion
 X X X X
 X X X X
 X X X X
 X O X X

 https://oj.leetcode.com/problems/surrounded-regions/
 * Created by abhimaloo on 8/26/14.
 */
public class SurroundedRegion {

    public static char[][] board = {{'X','X','X','X'},
                                    {'X','O','O','X'},
                                    {'X','X','O','X'},
                                    {'X','O','X','X'}};

    public static void surroundRegion(char[][] board) {
        //find all the zeros's on edge and convert them into y's
        for( int i=0; i< board.length; i++) {
            for( int j = 0; j< board[0].length; j++) {
                if((i == 0 || i == board.length-1) || (j == 0 || j == board[0].length -1))  {
                    if(board[i][j] == 'O') {
                        // do a DFS to all the four neghbours .. if found O change them to Y as well
                        dfs(board, i, j);
                    }
                }
            }
        }

        //find all Y's and change them by O and Change All Os to X
        for( int i=0; i< board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // change 'Y' to 'O'
                if(board[i][j] == 'Y') {
                    board[i][j] = 'O';
                } else if(board[i][j] == 'O') { // change 'O' to 'X'
                    board[i][j] = 'X';
                }

            }
        }

    }

    public static void dfs( char[][] board, int x, int y) {
        if(board[x][y] == 'O') {
           board[x][y] = 'Y';
        } else {
            return;
        }

        //checking all the four neighbours
        if(x-1 >=0 ) {
            dfs(board, x-1,y);
        }

        if(x+1 < board.length) {
            dfs(board, x+1, y);
        }

        if(y-1 >=0) {
            dfs(board, x, y-1);
        }

        if(y+1 < board[0].length) {
            dfs(board, x,y+1);
        }

    }


    public static void main(String[] args) {
        surroundRegion(board);
        for( int i=0; i< board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(" " + board[i][j]+ " ");
            }
            System.out.println();
        }
    }

}
