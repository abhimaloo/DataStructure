package com.leetcode2019.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WallsAndGates {
    public static void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    rooms[i][j] = -2;
                    bfs(rooms, i, j);
                    rooms[i][j] = 0;
                }
            }
        }
    }

    public static void bfs(int[][] rooms, int i, int j) {
        Set<String> visited = new HashSet<>();
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[]{i, j, 0});
        while (!queue.isEmpty()) {
            Integer[] cell = queue.poll();
            int cellx = cell[0];
            int cellY = cell[1];
            int dist = cell[2];
            if (rooms[cellx][cellY] == -1 || rooms[cellx][cellY] == 0 || visited.contains(cellx + "," + cellY)) {
                continue;
            }

            if (rooms[cellx][cellY] == Integer.MIN_VALUE) {
                rooms[cellx][cellY] = dist;
            } else if (rooms[cellx][cellY] > 0) {
                rooms[cellx][cellY] = Math.min(rooms[cellx][cellY], dist);
            }
            visited.add(cellx + "," + cellY);

            if (cellx + 1 < rooms.length && !visited.contains(cellx + 1 + "," + cellY)) {
                queue.offer(new Integer[]{cellx + 1, cellY, dist + 1});
            }
            if (cellx - 1 >= 0 && !visited.contains((cellx - 1) + "," + cellY)) {
                queue.offer(new Integer[]{cellx - 1, cellY, dist + 1});
            }
            if (cellY + 1 < rooms[0].length && !visited.contains(cellx + "," + cellY + 1)) {
                queue.offer(new Integer[]{cellx, cellY + 1, dist + 1});
            }
            if (cellY - 1 >= 0 && !visited.contains(cellx + "," + (cellY - 1))) {
                queue.offer(new Integer[]{cellx, cellY - 1, dist + 1});
            }
        }
    }

    public static void main(String[] args) {
        int[][] rooms = new int[][]{{Integer.MIN_VALUE, -1, 0, Integer.MIN_VALUE},
                {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, -1},
                {Integer.MIN_VALUE, -1, Integer.MIN_VALUE, -1},
                {0, -1, Integer.MIN_VALUE, Integer.MIN_VALUE},
        };
        wallsAndGates(rooms);
    }
}
