package com.leetcode2019.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
https://leetcode.com/problems/course-schedule/
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseSchedule {
    public static boolean canFinish(int n, int[][] prerequisites) {
        Set<Integer> visited = new HashSet();
        Set<Integer> visiting = new HashSet();
        List<List<Integer>> adjacencyList = new ArrayList(n);
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            adjacencyList.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        for (int i = 0; i < n; i++) {
            if (hasCycle(i, adjacencyList, visiting, visited)) {
                return false;
            }
        }

        return true;
    }

    public static boolean hasCycle(int course, List<List<Integer>> dep, Set<Integer> visiting, Set<Integer> visited) {
        if (visiting.contains(course)) {
            return true;
        }
        if (visited.contains(course)) {
            return false;
        }
        visiting.add(course);
        for (int depCourse : dep.get(course)) {
            if (hasCycle(depCourse, dep, visiting, visited)) {
                return true;
            }
        }
        visiting.remove(course);
        visited.add(course);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }
}
