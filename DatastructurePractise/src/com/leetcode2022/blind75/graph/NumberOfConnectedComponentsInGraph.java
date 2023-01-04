package com.leetcode2022.blind75.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.



Example 1:


Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1


Constraints:

1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= ai <= bi < n
ai != bi
There are no repeated edges.
 */
public class NumberOfConnectedComponentsInGraph {
    public int countComponents(int n, int[][] edges) {
        if (n == 0 || n == 1) return n;
        List<List<Integer>> graph = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int count = 0;

        for (int i = 0; i < n; i++) {
            graph.add(i, new ArrayList<>());
        }

        //build the graph
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                dfs(i, visited, graph);
                count++;
            }
        }
        return count;
    }

    public void dfs(int node, Set<Integer> visited, List<List<Integer>> graph) {
        if (visited.contains(node))
            return;

        visited.add(node);

        for (int neighbour : graph.get(node)) {
            dfs(neighbour, visited, graph);
        }
    }

    public static void main(String[] args) {

    }
}
