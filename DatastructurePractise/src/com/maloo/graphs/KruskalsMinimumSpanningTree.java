package com.maloo.graphs;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of Kruskal's Algorithm
 * Created by abhishekm787 on 7/2/14.
 */
public class KruskalsMinimumSpanningTree {

    public static  List<Graph.Edge> minimumSpanningTree(Graph g) {
        // create a list of universe
        List<Graph.Edge> universe = new ArrayList<>();
        universe.addAll(g.getAllEdges());
        //optimization for greedy approach
        // sort the edges with respect to the cost in increasing order
        Collections.sort(universe, new Comparator<Graph.Edge>() {
            @Override
            public int compare(Graph.Edge o1, Graph.Edge o2) {
                return Integer.compare(o1.weight,o2.weight);
            }
        });

        // initialize a mould
        List<Graph.Edge> mould  = new ArrayList<>();
        // data structure to make sure there is no cycle
        DisjointSets ds = new DisjointSets();


        //iterate through edges
        for(Graph.Edge edge: universe){
            // make sure the root of edge which is being iterated is not same as of first edge
            // we do not consider directions
             if(!(ds.find(edge.to) == ds.find(edge.from) ) ){
                 // add the "to" node to the disjoint set
                 ds.union(edge.to,edge.from);
                 //add the edge to the mould
                 mould.add(edge);
             }
        }

       //return the mould
       return mould;
    }


    public static void main(String[] args) {
        Graph g = new Graph();

        g.addEdge(1,2,1); // edge between vertex label 1 to 2 with weight 1
        g.addEdge(2,3,6); // edge between vertex label 2 to 3 with weight 6
        g.addEdge(2,4,2); // edge between vertex label 2 to 4 with weight 2
        g.addEdge(1,4,4); // edge between vertex label 1 to 4 with weight 4
        g.addEdge(4,3,3); // edge between vertex label 4 to 3 with weight 3




        for(Graph.Edge e: minimumSpanningTree(g))  // shortest path should be 1->2->4->3
        {
            System.out.println(e.from + "->" +e.to);
        }
    }
}
