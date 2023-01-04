package com.leetcode2022.blind75.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.



Example 1:


Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
Example 2:

Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.


Constraints:

m == heights.length
n == heights[r].length
1 <= m, n <= 200
0 <= heights[r][c] <= 105
 */
public class PacificAtlanticWaterFlow {
    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    int numRows = 0;
    int numColumns = 0;

    /*
    Idea is to DFS starting from each oceans's boundary cells and maintain the list of i,j which are altantic reachable and pacific reachable
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return null;
        }

        List<List<Integer>> response = new ArrayList<>();
        numRows = matrix.length;
        numColumns = matrix[0].length;

        boolean[][] atlanticReachable = new boolean[numRows][numColumns];
        boolean[][] pacificReachable = new boolean[numRows][numColumns];

        // lets first explore all the cells for rows
        for (int i = 0; i < numRows; i++) {
            dfs(i, 0, matrix, pacificReachable);
            dfs(i, numColumns - 1, matrix, atlanticReachable);
        }

        for (int i = 0; i < numColumns; i++) {
            dfs(0, i, matrix, pacificReachable);
            dfs(numRows - 1, i, matrix, atlanticReachable);
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    response.add(Arrays.asList(i, j));
                }
            }
        }

        return response;
    }

    public void dfs(int row, int col, int[][] matrix, boolean[][] reachable) {
        reachable[row][col] = true;
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newColumn = col + dir[1];
            // check that the new cells are not within bounds
            if (newRow < 0 || newRow >= numRows || newColumn < 0 || newColumn >= numColumns) {
                continue;
            }

            // it should not be visited already
            if (reachable[newRow][newColumn]) {
                continue;
            }

            // also the new row and column should have lower heights
            if (matrix[newRow][newColumn] < matrix[row][col]) {
                continue;
            }

            dfs(newRow, newColumn, matrix, reachable);
        }
    }


}
