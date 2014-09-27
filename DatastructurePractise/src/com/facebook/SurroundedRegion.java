package com.facebook;

/**
 * Created by abhimaloo on 9/27/14.
 */
public class SurroundedRegion {
    public static char[][] board = {{'X','X','X','X'},
                                    {'X','O','O','X'},
                                    {'X','X','O','X'},
                                    {'X','O','X','X'},
                                    {'X','X','X','X'},
                                    {'X','O','O','X'},
                                    {'X','O','O','X'},
                                    {'X','O','X','X'}};

    public static void surroundedRegion(char[][]s) {

        for(int i = 0; i< s.length; i++) {
            for(int j = 0; j< s[0].length; j++) {

                if((i ==0 && s[i][j] == 'O') || (i == s.length-1 && s[i][j] == 'O') ||
                        (j ==0 && s[i][j] == 'O')|| (j == s[0].length -1 && s[i][j] == 'O')) {
                    dfs(s, i, j);
                }
            }
        }

        for(int i = 0; i< s.length; i++) {
            for (int j = 0; j < s[0].length; j++) {

                if(s[i][j] == 'O') {
                    s[i][j] = 'X';
                }
                if(s[i][j] == 'Y') {
                    s[i][j] = 'O';
                }
            }
        }

    }

    private static void dfs(char[][] s, int x, int y) {
        if(s[x][y] != 'O') {
            return;
        }

        s[x][y] = 'Y';

        if(x-1 >= 0 && s[x-1][y] == 'O') {
            dfs(s,x-1,y);
        }

        if(x+1 < s.length  && s[x+1][y] == 'O') {
            dfs(s,x+1,y);
        }

        if(y-1 >= 0 && s[x][y-1] == 'O') {
            dfs(s,x,y-1);
        }

        if(y+1 < s[0].length && s[x][y+1] == 'O') {
            dfs(s,x,y+1);
        }

    }

    public static void main(String[] args) {
        surroundedRegion(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                System.out.print(" " + board[i][j] + " ");
            }

            System.out.println();
        }
    }
}
