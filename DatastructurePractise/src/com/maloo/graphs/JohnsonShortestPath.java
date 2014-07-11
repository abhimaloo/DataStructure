package com.maloo.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by abhimaloo on 7/10/14.
 */
public class JohnsonShortestPath {

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

        // Now apply Dijkstra algorithm to compute shotest path

        //

    }
}
