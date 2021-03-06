package com.leetcode2019.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/number-of-islands-ii/

A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class NumberOfIsland2 {

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] parent = new int[m * n];
        Arrays.fill(parent, -1);
        List<Integer> numIslands = new ArrayList<>(positions.length);
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int islandCount = 0;

        for (int i = 0; i < positions.length; i++) {
            int[] position = positions[i];
            int current = n * position[0] + position[1];
            if (parent[current] == -1) {  // if there is water than only process the land part
                // update the root to be root
                parent[current] = current;
                islandCount++;

                for (int[] direction : directions) {
                    int x = position[0] + direction[0];
                    int y = position[1] + direction[1];
                    int nCurrent = n * x + y;

                    if (x < 0 || x >= m || y < 0 || y >= n || parent[nCurrent] == -1) continue;

                    int nParent = find(parent, nCurrent);
                    if (nParent != current) {  // it means they are in different islands hence merge them
                        parent[current] = nParent;
                        current = nParent;
                        islandCount--;
                    }
                }
            }

            numIslands.add(islandCount);
        }

        return numIslands;
    }

    public static int find(int[] parent, int root) {
        while (parent[root] != root) {
            root = parent[root];
        }
        return root;
    }

    public static void main(String[] args) {
        numIslands2(3, 3, new int[][]{{0, 0}, {0, 1}, {1, 2}, {1, 2}});
    }

}
