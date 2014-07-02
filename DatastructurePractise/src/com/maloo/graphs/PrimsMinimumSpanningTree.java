package com.maloo.graphs;

import java.util.*;

/**
 * Greedy Algorithm - Prim's Algorithm for minimum spanning Tree
 * Created by abhimaloo on 7/1/14.
 */
public class PrimsMinimumSpanningTree {

    /**
     * It incresingly accumulates the nodes which has minimum cost and which is covering the uncovered vertices
     * @param g
     * @param sourceVertex
     * @return
     */
    public static List<Graph.Edge> minimumSpanningTree(Graph g, int sourceVertex) {
        Set<Integer> mould = new HashSet<>();
        Set<Integer> universe = new HashSet<>();
        universe.addAll(g.getAllVertex());

        List<Graph.Edge> minimumSpanningTree = new ArrayList<>();


        mould.add(sourceVertex);
        universe.remove(sourceVertex);


        PriorityQueue<Graph.Edge> heap = new PriorityQueue<>(universe.size(),new Comparator<Graph.Edge>(){

            @Override
            public int compare(Graph.Edge e1, Graph.Edge e2) {

                return Integer.compare(e1.weight, e2.weight);
            }
        });

        while(!universe.isEmpty()) {
            // go through all the vertices of the mould
            for(int vertex: mould){
                // check all outgoing edges

                for(Graph.Edge edge: g.getEdges(vertex)) {
                    // add the edge which is not targeting to a edge present in the mould
                    if(!mould.contains(edge.to)){
                        heap.add(edge);
                    }
                }
            }



            //this will find the next edge to be included which has minimum shortest Cost till origin +weight
            //remove it from the heap
            Graph.Edge target = null;
            //get the edge which has minimum cost and which goes to a destination not already in mould.
            while(true) {
                target = heap.remove();
                if(!mould.contains(target.to)){
                    break;
                }

            }


            // remove the target vertex from universe
            universe.remove(target.to);
            // add it to mould
            mould.add(target.to);

            //update the minimum spanning tree
            minimumSpanningTree.add(target);


        }

        return minimumSpanningTree;
    }

    public static void main(String[] args) {

        Graph g = new Graph();

        g.addEdge(1,2,1); // edge between vertex label 1 to 2 with weight 1
        g.addEdge(2,3,6); // edge between vertex label 2 to 3 with weight 6
        g.addEdge(2,4,2); // edge between vertex label 2 to 4 with weight 2
        g.addEdge(1,4,4); // edge between vertex label 1 to 4 with weight 4
        g.addEdge(4,3,3); // edge between vertex label 4 to 3 with weight 3


        for(Graph.Edge e: minimumSpanningTree(g,1))  // shortest path should be 1->2->4->3
        {
            System.out.println(e.from + "->" +e.to);
        }
    }

}
