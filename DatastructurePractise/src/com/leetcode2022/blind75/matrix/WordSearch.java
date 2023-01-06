package com.leetcode2022.blind75.matrix;

/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.



Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false


Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 */
public class WordSearch {
    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;

        // this looks like a dfs problem with backtracking
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(i, j, word, 0, board)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean exist(int i, int j, String word, int targetIndex, char[][] board) {
        if (targetIndex == word.length()) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        if (board[i][j] == word.charAt(targetIndex)) {
            char c = board[i][j];
            board[i][j] = '#';
            for (int[] direction : DIRECTIONS) {
                if (exist(i + direction[0], j + direction[1], word, targetIndex + 1, board)) {
                    return true;
                }
            }
            board[i][j] = c;

        }

        return false;
    }

    public static void main(String[] args) {

    }
}
