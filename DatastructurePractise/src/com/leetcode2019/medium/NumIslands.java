package com.leetcode2019.medium;

/*
https://leetcode.com/problems/number-of-islands/submissions/

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */
public class NumIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    numIslands(grid, i, j);
                }
            }
        }

        return count;
    }

    public void numIslands(char[][] grid, int x, int y) {
        if (x < grid.length && y < grid[0].length && x >= 0 && y >= 0 && grid[x][y] == '1') {
            grid[x][y] = '0';  // mark the grid as water to make it simple
            numIslands(grid, x + 1, y);
            numIslands(grid, x - 1, y);
            numIslands(grid, x, y + 1);
            numIslands(grid, x, y - 1);
        }

    }
}
