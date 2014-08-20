package com.interview.graphs.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a Weighted Directed Acyclic Graph and a source vertex in the graph,
 * find the shortest paths from given source to all other vertices.
 * Created by abhimaloo on 8/19/14.
 */
public class ShortestPathInDAGUsingTopologicalSort extends SimpleGraph{

    /**
     * Idea is to first create topological sort and store it in some datastructure
     Now assign all the shortest path map entry as Positive Infinite but source->source path as 0.
     Now iterate on vertices in the order of topological sort  -
     a) for every vertex which has non negative infinite cost iterate through all outgoing edges u -> v
     b) compare if cost(v) > cost(u) + weight .. if cost is greater update it with lesser cost (since we need shortest path)
     * @param sourceVertex
     */
    public void findShortestPathCost(Integer sourceVertex) {
        // first topological sort the graph
        LinkedList<Integer> stack  = new LinkedList<>();
        Map<Integer, Boolean> visited = new HashMap<>();

        for(Integer vertex : getAllVertex()) {
            if(!visited.containsKey(vertex)) {
                sort(vertex, visited, stack);
            }
        }

        Map<Integer, Integer> shortestCost = new HashMap<>(getAllVertex().size());
        for(Integer vertex : getAllVertex()) {
            if(vertex == sourceVertex) {
                shortestCost.put(vertex, 0);
            } else {
                shortestCost.put(vertex, Integer.MAX_VALUE);
            }
        }


        while(!stack.isEmpty()) {
            Integer vertex = stack.pop();
            if(shortestCost.get(vertex) != Integer.MAX_VALUE) {
                List<SimpleEdge> edges = getOutgoingEdges(vertex);
                if(edges != null) {
                    for(SimpleEdge e : edges) {

                        if(shortestCost.get(e.to) > shortestCost.get(e.from) + e.weight) {
                            shortestCost.put(e.to, shortestCost.get(e.from) + e.weight);
                        }
                    }
                }
            }

        }

        for(Integer vertex : shortestCost.keySet()) {
            System.out.println("Shortest cost of "+ vertex + " is "+ shortestCost.get(vertex));
        }

    }

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
        ShortestPathInDAGUsingTopologicalSort graph = new ShortestPathInDAGUsingTopologicalSort();
        graph.insertEdge(1,2,1);
        graph.insertEdge(2,3,5);
        graph.insertEdge(3,4,2);
        graph.insertEdge(1,5,3);
        graph.insertEdge(5,6,1);
        graph.insertEdge(6,4,2);

        graph.findShortestPathCost(1);

    }
}
