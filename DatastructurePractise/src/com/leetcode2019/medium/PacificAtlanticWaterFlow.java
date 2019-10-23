package com.leetcode2019.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticWaterFlow {
    public static List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        if (matrix.length == 0) return result;

        for (int i = 1; i < matrix.length; i++) {
            if (!visited[i][0])
                findFlow(matrix, matrix[i][0], i, 0, matrix.length, matrix[0].length, visited);
        }

        for (int i = 1; i < matrix[0].length; i++) {
            if (!visited[0][i])
                findFlow(matrix, matrix[0][i], 0, i, matrix.length, matrix[0].length, visited);
        }
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; i < visited[0].length; j++) {
                if (visited[i][j]) result.add(Arrays.asList(i, j));
            }
        }
        return result;
    }

    public static boolean findFlow(int[][] matrix, int prevValue, int x, int y, int xlimit, int ylimit, boolean[][] visited) {

        if (x == xlimit || y == ylimit) return true;
        if (x < 0 || y < 0) return false;
        if (visited[x][y]) return true;
        if (matrix[x][y] == -1) return false;

        if (matrix[x][y] <= prevValue) {
            int curValue = matrix[x][y];
            matrix[x][y] = -1;
            if (!(findFlow(matrix, matrix[x][y], x + 1, y, xlimit, ylimit, visited) ||
                    findFlow(matrix, matrix[x][y], x - 1, y, xlimit, ylimit, visited) ||
                    findFlow(matrix, matrix[x][y], x, y + 1, xlimit, ylimit, visited) ||
                    findFlow(matrix, matrix[x][y], x, y - 1, xlimit, ylimit, visited))) {
                matrix[x][y] = curValue;
            } else {
                visited[x][y] = true;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        pacificAtlantic(matrix);
    }
}
