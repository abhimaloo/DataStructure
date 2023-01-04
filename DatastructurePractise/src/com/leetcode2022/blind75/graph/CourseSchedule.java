package com.leetcode2022.blind75.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {

    public boolean canFinish(int n, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>(n);
        Set<Integer> visiting = new HashSet<>();
        Set<Integer> visisted = new HashSet<>();

        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            adjacencyList.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        for (int i = 0; i < n; i++) {
            if (hasCycle(i, adjacencyList, visiting, visisted)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(int course, List<List<Integer>> adjacencyList, Set<Integer> visiting, Set<Integer> visisted) {
        if (visiting.contains(course)) {
            return true;
        }
        if (visisted.contains(course)) {
            return false;
        }

        visiting.add(course);
        for (int preReq : adjacencyList.get(course)) {
            if (hasCycle(preReq, adjacencyList, visiting, visisted)) {
                return true;
            }
        }
        visiting.remove(course);
        visisted.add(course);

        return false;
    }

}
