package com.interview.graphs.impl;

import java.util.*;

/**
 * Objective is to find out whether graph has only one strongly connected component or not
 * Created by abhishekm787 on 7/31/14.
 */
public class KosarajuSCC<V extends  Comparable<V>,E> extends MyGraph<V,E>{

    public KosarajuSCC(boolean isDirected) {
        super(isDirected);
    }
    /**
     * Lets implement kosaraju's Strongly connected component algorithm
     */
    public boolean isSCC() {
        Set<V> visited = new HashSet<>();

        //Step 1 : clone and obtain a reversed graph
        MyGraph<V,E> reversed = cloneAndReverse();

        //Step 2: Calculate finishing times.. pass the vertex one by one
        Set<Vertex<V>> vertices = reversed.getAllVertex();
        //stack will have elements in order of their finishing times
        LinkedList<Vertex<V>> stack = new LinkedList<>();
        for(Vertex<V> vertex : vertices){
            if(!visited.contains(vertex.data)){
                dfsFinishingTime(reversed, stack, visited, vertex);
            }
        }

        if(stack.size()!=vertices.size()){
            return false;
        }

        //step 3 : on unreversed graph now go through stack one by one and do a dfs.. collect the DFS elements in scc set
        visited.clear();
        List<Set<Vertex<V>>> scc = new ArrayList<>();
        while(!stack.isEmpty()){
            Vertex<V> vertex = stack.pop();
            Set<Vertex<V>> component =  new HashSet<Vertex<V>>();
            if(!visited.contains(vertex.data)){
                dfsSCC((MyGraph) this, component, visited,vertex);
                scc.add(component);
            }
        }


       return scc.size()==1 && scc.get(0).size() == vertices.size();

    }

    private void dfsSCC(MyGraph<V,E> myGraph, Set<Vertex<V>> component, Set<V> visited, Vertex<V> vertex) {
        visited.add(vertex.data);
        component.add(vertex);
        for(Edge<V,E> edge : myGraph.getOutgoingEdges(vertex.data)){
            if(!visited.contains(edge.to.data)){
                dfsSCC(myGraph, component, visited, edge.to);
            }
        }

    }

    private void dfsFinishingTime(MyGraph<V, E> reversed, LinkedList<Vertex<V>> stack, Set<V> visited, Vertex<V> vertex) {
        visited.add(vertex.data);
        for(Edge<V,E> edge : reversed.getOutgoingEdges(vertex.data)){
            if(!visited.contains(edge.to.data)){
                dfsFinishingTime(reversed,stack,visited,edge.to);
            }
        }
        stack.push(vertex);
    }

    private MyGraph<V, E> cloneAndReverse() {
        MyGraph<V,E> reversed = new MyGraph<>(true);
        for(Edge<V,E> edge : getAllEdges()){
             reversed.addEdge(edge.to,edge.from,edge.data);
       }
       return reversed;
    }

    public static void main(String[] args) {
        KosarajuSCC<Integer,Integer> graph = new KosarajuSCC<>(true);
        graph.addEdge(graph.addVertex(1), graph.addVertex(2),1);
        graph.addEdge(graph.addVertex(2), graph.addVertex(3),6);
        graph.addEdge(graph.addVertex(3), graph.addVertex(1),2);
       graph.addEdge(graph.addVertex(1), graph.addVertex(4),4);
       graph.addEdge(graph.addVertex(4), graph.addVertex(5),3);
       graph.addEdge(graph.addVertex(5), graph.addVertex(6),3);
       graph.addEdge(graph.addVertex(6), graph.addVertex(4),3);

        System.out.println(graph.isSCC());
    }


}
