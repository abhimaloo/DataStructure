package com.leetcode2022.revision;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/*
https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {
    public boolean canFinish(int n, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        HashSet<Integer> visiting = new HashSet<>();
        HashSet<Integer> visited = new HashSet<>();

        // first build the graph using adjacency list
        for (int i = 0; i < n; i++) {
            graph.add(i, new ArrayList<>());
        }
        for (int[] preReq : prerequisites) {
            graph.get(preReq[0]).add(preReq[1]);
        }

        // we will do dfs and see fo there is a cycle or not
        for (int i = 0; i < n; i++) {
            if (hasCycle(i, graph, visiting, visited)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(int course, List<List<Integer>> graph, HashSet<Integer> visiting, HashSet<Integer> visited) {
        if (visiting.contains(course)) {
            return true;
        }
        if (visited.contains(course)) {
            return false;
        }

        visiting.add(course);

        for (int dep : graph.get(course)) {
            if (hasCycle(dep, graph, visiting, visited)) {
                return true;
            }
        }

        visiting.remove(course);
        visited.add(course);

        return false;
    }
}
