package com.interview.graphs.impl;

import java.util.*;

/**
 * Created by abhimaloo on 7/31/14.
 */
public class TopologicalSort<V extends Comparable<V>, E> extends MyGraph<V,E>{

    public TopologicalSort(boolean isDirected) {
        super(isDirected);
    }

    /**
     * This performs a topological sort..
     * Its DFS with recording all the popped vertices in a stack
     * @return
     */
    public List<Vertex<V>> sort() {
        Set<V> visited = new HashSet<>();
        LinkedList<Vertex<V>> stack = new LinkedList<>();
        //try runnign DFS on every non visited element
        for(Vertex<V> vertex : getAllVertex()){
            if(!visited.contains(vertex.data)){
                doSort(this,visited,stack,vertex);
            }
        }
        List<Vertex<V>> response = new ArrayList<>();
        //collect the results
        while(!stack.isEmpty()) {
            response.add(stack.pop());
        }

        return response;
    }

    //that's where the main DFS happens
    private void doSort(MyGraph<V,E> graph, Set<V> visited, LinkedList<Vertex<V>> stack,Vertex<V> vertex) {
        visited.add(vertex.data);
        for(Edge<V,E> edge : graph.getOutgoingEdges(vertex.data)){
            if(!visited.contains(edge.to.data)){
                doSort(graph, visited, stack, edge.to);
            }
        }
        stack.push(vertex);
    }

}
