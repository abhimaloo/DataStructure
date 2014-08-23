package com.interview.graphs.algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by abhimaloo on 8/18/14.
 */
public class DijkstraShortestPath extends  SimpleGraph{


    public void shortestPath(Integer sourceVertex, Integer destinationVertex) {
        final Map<Integer, Integer> cost = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();

        PriorityQueue<Integer> heap = new PriorityQueue<>(getAllVertex().size(), new Comparator<Integer>() {
            @Override
            public int compare(Integer p1, Integer p2) {
                return Integer.compare(cost.get(p1), cost.get(p2));
            }
        });

        heap.add(sourceVertex);
        cost.put(sourceVertex, 0);
        parent.put(sourceVertex, null);
        int count = 1;
        while(!heap.isEmpty()) {
            Integer p  = heap.remove();

            for(SimpleEdge e : getOutgoingEdges(p, false)) {
                int newCost = cost.get(p) + e.weight;
                if((!cost.containsKey(e.to) || newCost < cost.get(e.to))) {
                   cost.put(e.to, newCost);
                   parent.put(e.to, p);
                   heap.add(e.to);
                    System.out.println("count : "+ ++count);
                }

            }
        }

        if(parent.containsKey(destinationVertex)) {
            System.out.println(destinationVertex);
            while(true) {
                if(parent.get(destinationVertex) == null) {
                    break;
                }
                System.out.println(parent.get(destinationVertex));
                destinationVertex = parent.get(destinationVertex);
            }
        }

    }

    public static void main(String[] args) {
        DijkstraShortestPath graph = new DijkstraShortestPath();
        graph.insertEdge(1,2,1, false);
        graph.insertEdge(2,3,5, false);
        graph.insertEdge(3,4,2, false);
        graph.insertEdge(1,5,3, false);
        graph.insertEdge(5,6,1, false);
        graph.insertEdge(6,4,2, false);

        graph.shortestPath(1, 4);


    }



}
