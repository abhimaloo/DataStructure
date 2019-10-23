package com.leetcode2019.medium;

/*
https://leetcode.com/problems/word-search/

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */
public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        boolean found = false;
        if (board.length == 0 || word.length() == 0) {
            return found;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return found;
    }

    /*
    This is very tricky, You need to mark the visited column with # since we can move in all 4 directions.
    code will revisit the elements if we do not mark them.
    It like regular backtracking, where you mark and then unmark on failure
     */
    public static boolean exist(char[][] board, int x, int y, int targetIndex, String word) {
        if (targetIndex == word.length()) {
            return true;
        }

        if (x < 0 | x == board.length || y < 0 || y == board[0].length || board[x][y] == '#') {
            return false;
        }


        if (board[x][y] == word.charAt(targetIndex)) {
            char c = board[x][y];
            board[x][y] = '#';
            if (exist(board, x + 1, y, targetIndex + 1, word) ||
                    exist(board, x - 1, y, targetIndex + 1, word) ||
                    exist(board, x, y + 1, targetIndex + 1, word) ||
                    exist(board, x, y - 1, targetIndex + 1, word)) {
                return true;
            }
            board[x][y] = c;
        }

        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'c', 'a', 'a'},
                {'a', 'a', 'a'},
                {'b', 'c', 'd'}};
        System.out.println(exist(board, "aab"));
    }


}
