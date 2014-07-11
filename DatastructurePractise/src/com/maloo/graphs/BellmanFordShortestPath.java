package com.maloo.graphs;



import java.util.*;

/**
 * Created by abhimaloo on 7/10/14.
 */
public class BellmanFordShortestPath {

    /**
     * this is an excellent algorithm to find shortest path between two nodes of a graph
     * given the graph could have negative edges and negatove cycles as well
     *
     * Solution : Cost[i][v] = cost of going from s to v by using i edges
     * @param g
     * @param sourceVertex
     * @param destinationVertex
     * @return
     */
    public static List<Graph.Edge> findShortestPath(Graph g, int sourceVertex, int destinationVertex){

        int n = g.getVerticesCount();
        //keeping space for 1 extra cycle to detect a negative cycle
        int [][]cost = new int[n+1][n];
        Graph.Edge[][] retrackt = new Graph.Edge[n+1][n];

        for( Integer i: g.getAllVertex()) {
            cost[0][i] = i!=sourceVertex ? Integer.MAX_VALUE: 0;
        }

        for( int i =1; i <=n; i++) {
            for( int v = 0; v<n; v++ ) {

                Set<Graph.Edge> inboundEdges = g.getInboundEdges(v);
                if(inboundEdges.size() == 0){
                    cost[i][v] = cost[i-1][v];
                } else{
                    cost[i][v] = Math.min(cost[i-1][v], getMinCostFromNeighbours(cost,v,g,sourceVertex, i, retrackt));
                }

            }
        }

        //Detect negative cycle
        // if cost[n-1][v] != cost[n][v] that means a negative cycle is present
        for( int i= 0; i <n; i ++){
            if(cost[n-1][i] != cost[n][i] ){
                throw new RuntimeException("Negative Cycle detected");
            }
        }

      System.out.println("Shortest Cost : "+ cost[n-1][destinationVertex]);

      List<Graph.Edge> shortestPath = new ArrayList<>();
      while(true) {
          if(destinationVertex == sourceVertex){
              break;
          }

          Graph.Edge e = retrackt[n-1][destinationVertex];
          shortestPath.add(e);
          destinationVertex = e.from;
      }

      return shortestPath;

    }

    public static int[][] findShortestPathReturnCost(Graph g, int sourceVertex){

        int n = g.getVerticesCount();
        //keeping space for 1 extra cycle to detect a negative cycle
        int [][]cost = new int[n+1][n];
        Graph.Edge[][] retrackt = new Graph.Edge[n+1][n];

        for( Integer i: g.getAllVertex()) {
            cost[0][i] = i!=sourceVertex ? Integer.MAX_VALUE: 0;
        }

        for( int i =1; i <=n; i++) {
            for( int v = 0; v<n; v++ ) {

                Set<Graph.Edge> inboundEdges = g.getInboundEdges(v);
                if(inboundEdges.size() == 0){
                    cost[i][v] = cost[i-1][v];
                } else{
                    cost[i][v] = Math.min(cost[i-1][v], getMinCostFromNeighbours(cost,v,g,sourceVertex, i, retrackt));
                }

            }
        }

        //Detect negative cycle
        // if cost[n-1][v] != cost[n][v] that means a negative cycle is present
        for( int i= 0; i <n; i ++){
            if(cost[n-1][i] != cost[n][i] ){
                throw new RuntimeException("Negative Cycle detected");
            }
        }


        return cost;

    }


    private static int getMinCostFromNeighbours(final int[][] cost, int v, Graph g, final int sourceVertex, final int i, Graph.Edge[][] retrackt) {
        Set<Graph.Edge> inboundEdges = g.getInboundEdges(v);

        int minimum  = Integer.MAX_VALUE;
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for(Graph.Edge edge: inboundEdges) {

            if(minimum > (cost[i-1][edge.from]!=Integer.MAX_VALUE?cost[i-1][edge.from] + edge.weight: Integer.MAX_VALUE)) {
               minimum =  (cost[i-1][edge.from]!=Integer.MAX_VALUE?cost[i-1][edge.from] + edge.weight: Integer.MAX_VALUE);
               retrackt[i][v] = edge;
            }

        }

        return minimum;
    }

    public static void main(String[] args) {
        Graph g = new Graph();

        g.addEdge(0,1,2); // edge between vertex label 1 to 2 with weight 1
        g.addEdge(1,2,2); // edge between vertex label 2 to 3 with weight 6
        g.addEdge(2,3,2); // edge between vertex label 2 to 4 with weight 2
        g.addEdge(0,4,4); // edge between vertex label 1 to 4 with weight 4
        g.addEdge(1,4,1); // edge between vertex label 4 to 3 with weight 3
        g.addEdge(4,3,4); // edge between vertex label 4 to 3 with weight 3

        for(Graph.Edge e: findShortestPath(g, 0, 3))  // shortest path should be 1->2->4->3
        {
            System.out.println(e.from + "->" +e.to);
        }

    }
}
