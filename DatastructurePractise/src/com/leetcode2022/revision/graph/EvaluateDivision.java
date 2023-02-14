package com.leetcode2022.revision.graph;

import java.util.*;

/*
https://leetcode.com/problems/evaluate-division/

You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.



Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]


Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // validate the inputs
        if (equations == null || equations.size() == 0) return null;
        if (equations.size() != values.length) return null;
        double[] response = new double[queries.size()];
        Arrays.fill(response, -1.0);

        //build graph first
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String numerator = equations.get(i).get(0);
            String denom = equations.get(i).get(1);
            double ratio = values[i];
            Map<String, Double> edges = graph.getOrDefault(numerator, new HashMap<>());
            edges.put(denom, ratio);
            graph.put(numerator, edges);

            Map<String, Double> reverseEdges = graph.getOrDefault(denom, new HashMap<>());
            reverseEdges.put(numerator, 1 / ratio);
            graph.put(denom, reverseEdges);
        }

        // now we can do DFS
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < queries.size(); i++) {
            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);
            Double res = 1.0;

            if (!graph.containsKey(from) || !graph.containsKey(to)) {
                response[i] = -1.0;
                continue;
            }

            response[i] = bfs(from, to, graph, visited);
            visited.clear();
        }

        return response;
    }

    public static double bfs(String from, String to, Map<String, Map<String, Double>> graph, Set<String> visited) {
        Queue<String> nodeQueue = new LinkedList<>();
        Queue<Double> valueQueue = new LinkedList<>();

        nodeQueue.offer(from);
        valueQueue.offer(1.0);

        while (!nodeQueue.isEmpty()) {
            String node = nodeQueue.poll();
            Double value = valueQueue.poll();
            if (node == to) {
                return value;
            }
            visited.add(node);

            for (Map.Entry<String, Double> edge : graph.get(node).entrySet()) {
                if (visited.contains(edge.getKey())) {
                    continue;
                }
                nodeQueue.offer(edge.getKey());
                valueQueue.offer(value * edge.getValue());
            }

        }
        return -1.0;
    }

    public static void main(String[] args) {
        List<List<String>> equationList = new ArrayList<>();
        equationList.add(Arrays.asList(new String[]{"a", "b"}));
        equationList.add(Arrays.asList(new String[]{"b", "c"}));
        equationList.add(Arrays.asList(new String[]{"bc", "cd"}));

        double[] values = new double[]{1.5, 2.5, 5.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList(new String[]{"a", "c"}));
        queries.add(Arrays.asList(new String[]{"c", "b"}));
        queries.add(Arrays.asList(new String[]{"bc", "cd"}));
        queries.add(Arrays.asList(new String[]{"cd", "bc"}));


        double[] res = calcEquation(equationList, values, queries);


    }

}
