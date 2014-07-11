package com.maloo.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by abhimaloo on 7/10/14.
 */
public class JohnsonShortestPath {

    /**
     * Tricky algorithm
     * It is used to create all source shortest path in O(mnlogn) times
     * It first adds a fake node s and connects this node with all the vertices with an edge costing 0 length
     * Now you run Bellman Ford algorithm to find shortest path of all vertices from fake node S
     * Once you have the shortest path array, you remove fake node S and all fake edges from graph
     * You adjust the edge lengths on graph by newCost(u,v) = oldCost(u,v)+ min[s][u] - min[s][v]
     * This will make all the edges positive
     *
     * Now run Dijkstra's algorithm for all the vertices to find the shortest path
     * finally subtract  the adjustment from the shortest path -
     * shortestCost(u,v) = short[u][v] - min[s][u] + min[s][v]
     * QED
     *
     * @param g
     */
    public static void findShortestPath(Graph g){
        List<Integer> vertices = new ArrayList<>(g.getAllVertex());
        Collections.sort(vertices);
        for(Integer v: vertices){
            g.addEdge(vertices.get(vertices.size()-1)+1, v, 0);
        }

        int cost[][]  = BellmanFordShortestPath.findShortestPathReturnCost(g, vertices.get(vertices.size()-1)+1);


        for(Integer v: vertices){
            g.removeEdge(vertices.get(vertices.size()-1)+1, v, 0);
        }

        for(Graph.Edge e: g.getAllEdges()){
              g.updateWeight(e, e.weight + cost[vertices.get(vertices.size()-1)+1][e.from] - cost[vertices.get(vertices.size()-1)+1][e.to] );
        }

        // Now apply Dijkstra algorithm to compute shortest path

        //

    }
}
