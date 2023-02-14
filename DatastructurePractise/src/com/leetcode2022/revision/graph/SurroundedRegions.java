package com.leetcode2022.revision.graph;

/*
https://leetcode.com/problems/surrounded-regions/description/
 */
public class SurroundedRegions {
    private static int[][] DIRECTION = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        // trick is to first capture all the zeros which are at border and start marking them with a special character T
        // since they cannot be captured. Now dfs from there are start marking all the characters linked to them as T
        // finally mark all the T as Os and all Os as X in the graph

        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                dfs(i, 0, board);
            }
            if (board[i][board[0].length - 1] == 'O') {
                dfs(i, board[0].length - 1, board);
            }
        }

        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == 'O') {
                dfs(0, j, board);
            }

            if (board[board.length - 1][j] == 'O') {
                dfs(board.length - 1, j, board);
            }
        }


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }

    }

    public static void dfs(int i, int j, char[][] board) {
        if (((i < 0 || i >= board.length) || j < 0 || j >= board[0].length) || board[i][j] == 'T' || board[i][j] == 'X') {
            return;
        }

        board[i][j] = 'T';
        for (int[] direction : DIRECTION) {
            dfs(i + direction[0], j + direction[1], board);
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};

        solve(board);
    }
}
