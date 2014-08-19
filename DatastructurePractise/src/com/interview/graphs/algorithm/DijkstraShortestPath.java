package com.interview.graphs.algorithm;

import java.util.*;

/**
 * Created by abhimaloo on 8/18/14.
 */
public class DijkstraShortestPath extends  SimpleGraph{

    /**
     * idea is to follow dikstra's algorithm
     * Keep finding the nearest neighbour whose traversal cost is minimum
     *
     * @param sourceVertex
     * @param destinationVertex
     * @return
     */
    public List<SimpleEdge> shortestPath(Integer sourceVertex, Integer destinationVertex) {

        // this keeps all the vertices in the universe
        Set<Integer> universe = new HashSet<>(getAllVertex());
        // this contains vertices whose shortest path has been calculated
        Set<Integer> mould = new HashSet<>();

        // this Map keeps the shortest path of every vertex from sourceVertex
        Map<Integer, List<SimpleEdge>> shortestPath = new HashMap<>();
        // this keeps the cost of shortest path to every vertex from source vertex
        final Map<Integer, Integer> shortestCost  = new HashMap<>();

        //initialize by removing source Vertex from universe
        universe.remove(sourceVertex);
        // adding it to mould
        mould.add(sourceVertex);
        // update the shortest path
        shortestPath.put(sourceVertex, new ArrayList<SimpleEdge>());
        // cost to go to itself is zero
        shortestCost.put(sourceVertex, 0);

        // this is min heap implementation for keeping all edges in order of their cost
        PriorityQueue<SimpleEdge> heap = new PriorityQueue<>(universe.size(), new Comparator<SimpleEdge>() {
            @Override
            public int compare(SimpleEdge o1, SimpleEdge o2) {
                // compare the cost to come to the "from" vertex from source vertex and the current weight of the edge
                return Integer.compare(shortestCost.get(o1.from) + o1.weight, shortestCost.get(o2.from) + o2.weight);
            }
        });

        // keep looping till universe has any vertices
        while(!universe.isEmpty()) {
            // iterate through all the vertices of mould
            for(Integer vertex : mould) {
                // get all the outgoing edges for every vertex
                List<SimpleEdge> edges  = getOutgoingEdges(vertex);

                if(edges != null) {
                    // iterate through all the edges outgoing from the mould
                    for(SimpleEdge edge : edges) {
                        //if edges go to a point which is extending the horizon and heap does not already contains that edge .. add that edge to the heap
                        if(!mould.contains(edge.to) && !heap.contains(edge)) {
                            heap.add(edge);
                        }
                    }
                }
            }


            SimpleEdge shortestEdge = null;
            // while heap has some element
            while(!heap.isEmpty()) {
                // try looking at the peek of the heap if it contains an edge which goes to a vertex not present in mould
                if(!mould.contains(heap.peek().to)) {
                    // remove the min edge
                    shortestEdge = heap.remove();
                    // break the loop
                    break;
                }
            }


            // add the shortest edge to the mould
            mould.add(shortestEdge.to);
            // remove this edge from the universe
            universe.remove(shortestEdge.to);

            // update the shortest path of the vertex which shortestEdge goes to .
            // add shortest path till the from of shortestEdge
            shortestPath.put(shortestEdge.to, new ArrayList<SimpleEdge>(shortestPath.get(shortestEdge.from)));
            // add the current edge
            shortestPath.get(shortestEdge.to).add(shortestEdge);
            // update the cost by adding cost to the from of shortestEdge and add current weight
            shortestCost.put(shortestEdge.to, shortestCost.get(shortestEdge.from) + shortestEdge.weight);


        }
        // return the list of edges making shortest path for destination vertex
        return shortestPath.get(destinationVertex);
    }

    public static void main(String[] args) {
        DijkstraShortestPath graph = new DijkstraShortestPath();
        graph.insertEdge(1,2,1);
        graph.insertEdge(2,3,5);
        graph.insertEdge(3,4,2);
        graph.insertEdge(1,5,3);
        graph.insertEdge(5,6,1);
        graph.insertEdge(6,4,2);

        List<SimpleEdge> edges = graph.shortestPath(1,4);
        for(SimpleEdge edge : edges) {
            System.out.println(edge.to);
        }

    }



}
