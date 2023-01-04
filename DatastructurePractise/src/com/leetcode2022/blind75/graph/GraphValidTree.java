package com.leetcode2022.blind75.graph;

import java.util.*;

/*
You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.



Example 1:


Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false


Constraints:

1 <= n <= 2000
0 <= edges.length <= 5000
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no self-loops or repeated edges.
 */
public class GraphValidTree {
    public static boolean validTree(int n, int[][] edges) {
        if (n == 0) return true;
        if (edges.length < n - 1) return false;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return dfs(0, -1, visited, graph) && n == visited.size();

    }

    public static boolean dfs(int node, int prevNode, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        // loop exists
        if (visited.contains(node)) {
            return false;
        }

        visited.add(node);

        for (int neighbour : graph.get(node)) {
            if (neighbour == prevNode)
                continue;
            if (!dfs(neighbour, node, visited, graph)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
    }
}
