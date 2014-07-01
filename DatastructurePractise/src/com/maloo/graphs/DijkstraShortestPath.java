package com.maloo.graphs;





import com.maloo.graphs.Graph.Edge;

import java.util.*;

/**
 * This an implementation of Dijkstra's Shortest path Algorithm
 * Created by abhimaloo on 7/1/14.
 */
public class DijkstraShortestPath {

    public static List<Edge> dijkstraShortestPath(Graph g, int sourceVertex, int destinationVertex) {
        Set<Integer> mould = new HashSet<>();
        Set<Integer> universe = new HashSet<>();
        universe.addAll(g.getAllVertex());

        Map<Integer,List<Edge>> shortestPath = new HashMap<>();
        final Map<Integer,Integer> shortestCost = new HashMap<>();

        mould.add(sourceVertex);
        universe.remove(sourceVertex);
        shortestPath.put(sourceVertex,new ArrayList<Edge>());
        shortestCost.put(sourceVertex,0);

        PriorityQueue<Edge> heap = new PriorityQueue<>(universe.size(),new Comparator<Edge>(){

            @Override
            public int compare(Edge e1, Edge e2) {

                return Integer.compare(e1.weight+shortestCost.get(e1.from), e2.weight+shortestCost.get(e2.from));
            }
        });

        while(!universe.isEmpty()) {
            // go through all the vertices of the mould
            for(int vertex: mould){
                // check all outgoing edges

                for(Edge edge: g.getEdges(vertex)) {
                    // add the edge which is not targetting to a edge present in the mould
                    if(!mould.contains(edge.to)){
                        heap.add(edge);
                    }
                }
            }



            //this will find the next edge to be included which has minimum shortest Cost till origin +weight
            //remove it from the heap
            Edge target = null;
            //get the edge which has minimum cost and which goes to a destination not already in mould.
            while(true) {
                //special case : if the supplied graph is a disconnected graph
                if(heap.isEmpty()) {
                    return shortestPath.get(destinationVertex);
                }

                target = heap.remove();
                if(!mould.contains(target.to)){
                    break;
                }


            }


            // remove the target vertex from universe
            universe.remove(target.to);
            // add it to mould
            mould.add(target.to);

            //update the shortest path
            List<Edge> shortestPathForTarget = new ArrayList<>();
            shortestPathForTarget.addAll(shortestPath.get(target.from));
            shortestPathForTarget.add(target);
            shortestPath.put(target.to,shortestPathForTarget);

            // update the shortest cost
            shortestCost.put(target.to, shortestCost.get(target.from) + target.weight );

        }

        return shortestPath.get(destinationVertex);
    }

    public static void main(String[] args) {

        Graph g = new Graph();

        g.addEdge(1,2,1); // edge between vertex label 1 to 2 with weight 1
        g.addEdge(2,3,6); // edge between vertex label 2 to 3 with weight 6
        g.addEdge(2,4,2); // edge between vertex label 2 to 4 with weight 2
        g.addEdge(1,4,4); // edge between vertex label 1 to 4 with weight 4
        g.addEdge(4,3,3); // edge between vertex label 4 to 3 with weight 3


        for(Edge e: dijkstraShortestPath(g,1,3))  // shortest path should be 1->2->4->3
        {
            System.out.println(e.from + "->" +e.to);
        }
    }


}
