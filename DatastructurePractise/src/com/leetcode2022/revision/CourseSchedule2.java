package com.leetcode2022.revision;

import java.util.*;

/*
https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseSchedule2 {
    // topological sort question
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //lets build the graph first
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(i, new ArrayList<>());
        }

        for (int[] preReq : prerequisites) {
            graph.get(preReq[0]).add(preReq[1]);
        }

        Stack<Integer> order = new Stack<>();
        Set<Integer> visiting = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, graph, order, visiting, visited)) {
                return new int[]{};
            }
        }

        int[] response = new int[order.size()];
        for (int i = 0; i < order.size(); i++) {
            response[i] = order.get(i);
        }
        return response;
    }

    public boolean hasCycle(int course, List<List<Integer>> graph, Stack<Integer> order, Set<Integer> visiting, Set<Integer> visited) {
        if (visiting.contains(course)) {
            return true;
        }

        if (visited.contains(course)) {
            return false;
        }

        visiting.add(course);
        for (Integer preReq : graph.get(course)) {
            if (hasCycle(preReq, graph, order, visiting, visited)) {
                return true;
            }
        }

        visiting.remove(course);
        visited.add(course);
        // once all the prereqs are done now record the order
        order.push(course);
        return false;
    }
}
