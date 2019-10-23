package com.leetcode2019.medium;

import java.util.*;

/*
https://leetcode.com/problems/evaluate-division/
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].


The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */
public class EvaluateDivision {
    /*
    Trick is to build a graph and apply DFS
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            graph.putIfAbsent(equations.get(i).get(0), new HashMap<>());
            graph.putIfAbsent(equations.get(i).get(1), new HashMap<>());
            graph.get(equations.get(i).get(0)).put(equations.get(i).get(1), values[i]);  // weight is the multiplier ot go from a -> b
            graph.get(equations.get(i).get(1)).put(equations.get(i).get(0), 1 / values[i]); // 1/weight is the multiplier to go from b -> a
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            result[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), 1, graph, new HashSet<>());
        }

        return result;
    }

    public double dfs(String start, String target, double currVal, Map<String, Map<String, Double>> graph, Set<String> visited) {
        if (!graph.containsKey(target) || !graph.containsKey(start)) return -1;   // if target is not in graph return -1
        if (start == target) return currVal;    // found the target return total multiplier
        visited.add(start);  // mark it visited

        Map<String, Double> edges = graph.get(start);
        for (Map.Entry<String, Double> e : edges.entrySet()) {
            if (!visited.contains(e.getKey())) {   // make sure we do not visit the same node again
                double result = dfs(e.getKey(), target, currVal * e.getValue(), graph, visited);  //recurse
                if (result != -1) {   // keep trying till we do not get result other than -1
                    return result;
                }
            }
        }

        return -1;  // we tried our best but did not get any result
    }

    public static void main(String[] args) {
        List<List<String>> eq = new ArrayList<>();
        eq.add(Arrays.asList("a", "b"));
        eq.add(Arrays.asList("b", "c"));

        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));
        EvaluateDivision obj = new EvaluateDivision();
        double[] result = obj.calcEquation(eq, new double[]{2.0, 3.0}, queries);
        for (double d : result)
            System.out.println(d + " , ");
    }

}
