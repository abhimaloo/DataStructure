package com.interview.graphs;

import com.interview.graphs.algorithm.SimpleEdge;
import com.interview.graphs.algorithm.SimpleGraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a Weighted Directed Acyclic Graph (DAG) and a source vertex s in it, find the longest distances from s to all other vertices in the given graph.
 *
 * http://www.geeksforgeeks.org/find-longest-path-directed-acyclic-graph/
 * Created by abhimaloo on 8/19/14.
 */
public class LongestPathInDAG extends SimpleGraph{


    /**
     * Idea is to first create topological sort and store it in some datastructure
       Now assign all the longest path map entry as Negative Infinite but source->source path as 0.
       Now iterate on vertices in the order of topological sort  -
       a) for every vertex which has non negative infinite cost iterate through all outgoing edges u -> v
       b) compare if cost(v) < cost(u) + weight .. if cost is less update it with higher cost (since we need longest path)
     * @param sourceVertex
     */
    public void findLongestPathCost(Integer sourceVertex) {
        // first topological sort the graph
        LinkedList<Integer> stack  = new LinkedList<>();
        Map<Integer, Boolean> visited = new HashMap<>();

        // iterate through all vertices in any order
        for(Integer vertex : getAllVertex()) {
            if(!visited.containsKey(vertex)) {
                // pass very unvisited vertex to sort .. sort does DFS on the vertex and mark all the children
                // it can reach to as visited and while popping out it saves the vertices in the order of reverse pop out
                sort(vertex, visited, stack);
            }
        }

        Map<Integer, Integer> longestCost = new HashMap<>(getAllVertex().size());
        // initialize the longest cost map
        for(Integer vertex : getAllVertex()) {
            if(vertex == sourceVertex) {
                // make source's longest cost 0
                longestCost.put(vertex, 0);
            } else {
                // and everyone longest cost -Infinite
                longestCost.put(vertex, Integer.MIN_VALUE);
            }
        }

        // iterate through topologically sorted graph
        while(!stack.isEmpty()) {
            Integer vertex = stack.pop();
            // if this vertex does not have -Infinite cost
            if(longestCost.get(vertex) != Integer.MIN_VALUE) {
                // go through all the edges starting from it
                List<SimpleEdge> edges = getOutgoingEdges(vertex);
                if(edges != null) {

                    for(SimpleEdge e : edges) {
                         // update the longest cost with the greater value of a destination where its more costly to reach + weight
                        if(longestCost.get(e.to) < longestCost.get(e.from) + e.weight) {
                            longestCost.put(e.to, longestCost.get(e.from) + e.weight);
                        }
                    }
                }
            }

        }

        // iterate and print longest costs
        for(Integer vertex : longestCost.keySet()) {
            System.out.println("Longest cost of "+ vertex + " is "+ longestCost.get(vertex));
        }

    }

    // this does the topological sort or calculate finishing times
    // it does a DFS from the vertex passed through it and collects the vertex on the stack while popping out
    private void sort(Integer vertex, Map<Integer, Boolean> visited, LinkedList<Integer> stack) {
        visited.put(vertex, true);
        List<SimpleEdge> edges = getOutgoingEdges(vertex);
        if(edges != null) {
            for( SimpleEdge e: edges) {
                if(!visited.containsKey(e.to)) {
                    sort(e.to, visited, stack);
                }
            }
        }

        stack.push(vertex);

    }

    public static void main(String[] args) {
        LongestPathInDAG graph = new LongestPathInDAG();
        graph.insertEdge(1,2,1);
        graph.insertEdge(2,3,5);
        graph.insertEdge(3,4,2);
        graph.insertEdge(1,5,3);
        graph.insertEdge(5,6,1);
        graph.insertEdge(6,4,2);

        graph.findLongestPathCost(1);

    }

}
