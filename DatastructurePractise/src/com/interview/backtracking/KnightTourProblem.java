package com.interview.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * The knight is placed on the first block of an empty board and, moving according to the rules of chess,
 * must visit each square exactly once.
 *
 * http://www.geeksforgeeks.org/backtracking-set-1-the-knights-tour-problem/
 * Created by abhimaloo on 8/21/14.
 */
public class KnightTourProblem {

    public static int[][] board = {{0,0,0,0,0},
                                   {0,0,0,0,0},
                                   {0,0,0,0,0},
                                   {0,0,0,0,0},
                                   {0,0,0,0,0}};
    public static int n = 5;

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static List<Pair> findNextLegalMove(int[][] board, int x, int y) {
        int[][] validlocation = {{2,1}, {-2, -1}, {-2,1}, {2, -1}, {1,2}, {-1,-2}, {1, -2}, {-1, 2}};
        List<Pair> nextMoves= new ArrayList<>();

        for(int i = 0; i < validlocation.length; i++) {
            int x1 = x + validlocation[i][0];
            int y1 = y + validlocation[i][1];

            if(isLegal(board, x1, y1)) {

                nextMoves.add(new Pair(x1,y1));
            }

        }

        return nextMoves;
    }

    private static boolean isLegal(int[][] board, int x, int y) {
        if((x < 0 || y <0 || x >= board[0].length || y >= board[0].length)) {
            return false;
        }
        if(board[x][y] != 0) {
            return false;
        }

        return true;
    }

    private static void print(int[][] board) {
        for( int i = 0; i< n; i++) {
            for( int j = 0; j< n ; j++) {

                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("------------------------------");
    }

    public static boolean tour(int[][] board, int x, int y, int index) {
        if(index > n *n) {
            return true;
        } else {

            for(Pair p : findNextLegalMove(board, x,y)) {
                //start the tour
                board[p.x][p.y] = index;
                print(board);
                //recurse
                if(tour(board, p.x, p.y, index +1)) {
                    return true;
                } else {
                    //backtrack
                    board[p.x][p.y] = 0;
                    print(board);
                }
            }

        }

        return false;

    }


    public static void main(String[] args) {
        board[0][0] = 1;
        tour(board, 0, 0, 2);

    }


}
