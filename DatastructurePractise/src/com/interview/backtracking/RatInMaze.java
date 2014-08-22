package com.interview.backtracking;

/**
 * A Maze is given as N*N binary matrix of blocks where source block is the upper left most block i.e.,
 * maze[0][0] and destination block is lower rightmost block i.e., maze[N-1][N-1].
 * A rat starts from source and has to reach destination. The rat can move only in two directions: forward and down.
 *
 * http://www.geeksforgeeks.org/backttracking-set-2-rat-in-a-maze/
 * Created by abhimaloo on 8/21/14.
 */
public class RatInMaze {
    public static int[][] maze = {{1, 0, 0, 0},
                                  {1, 1, 0, 1},
                                  {0, 1, 0, 0},
                                  {1, 1, 1, 1}};


    /**
     * trick is to use backtracking -
     * recursively keep going to right and down .. if you reach destination return true else false
     */
    public static boolean findPath(int[][] maze, int x, int y, int targetx, int targety) {
        // base case or exit condition : reached the target
        if(x == targetx && y == targety) {
            return true;
        } else {
            // check if x and y are in the limits && x,y does not hit a wall
            if((x >=0 && x < maze[0].length && y >=0  && y < maze.length) && maze[x][y] != 0) {
                System.out.println("x :" +x+ " y: "+ y );
                //recurse on left and right and return result of both by logical OR
                return findPath(maze, x+1,y, targetx, targety) || findPath(maze, x, y + 1, targetx, targety);
            }

        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("found : " +findPath(maze, 0, 0, 3, 3));
    }

}
