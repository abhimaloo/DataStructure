package com.leetcode2019.hard;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/robot-room-cleaner/

 */
public class RobotRoomCleaner {
    int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0, 0, new HashSet<>());
    }

    public void dfs(Robot robot, int x, int y, int currDirection, Set<String> visited) {
        robot.clean(); // do the cleanup of current cell
        visited.add(x + " " + y);

        for (int i = 0; i < 4; i++) {
            int newX = x + directions[currDirection][0];
            int newY = y + directions[currDirection][1];
            if (!visited.contains(newX + " " + newY) && robot.move()) {
                dfs(robot, newX, newY, currDirection, visited);
                // backtrack
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }

            // change direction by once
            robot.turnRight();
            currDirection++;
            currDirection %= 4;
        }
    }
}

interface Robot {
    public boolean move();

    public void turnLeft();

    public void turnRight();

    public void clean();
}