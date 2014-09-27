package com.facebook;


import java.util.*;

/**
 * Created by abhimaloo on 9/27/14.
 */
public class CloneGraph {

    static class GraphNode {
        public int vertex;
        public List<GraphNode> neighbours;

        GraphNode(int vertex, List<GraphNode> neighbours) {
            this.vertex = vertex;
            this.neighbours = neighbours;
        }
    }

    public static GraphNode clone(GraphNode original) {

        if(original == null) {
            return null;
        }

        LinkedList<GraphNode> queue = new LinkedList<>();
        Map<Integer, GraphNode> parentMap = new HashMap<>();

        queue.push(original);
        GraphNode clone  = new GraphNode(original.vertex, new ArrayList<GraphNode>());
        parentMap.put(original.vertex, clone);

        while(!queue.isEmpty()) {
            GraphNode node  = queue.removeFirst();

            for(GraphNode neighbour : node.neighbours) {
                if(!parentMap.containsKey(neighbour.vertex)) {
                    GraphNode cloneNeighbour = new GraphNode(neighbour.vertex, new ArrayList<GraphNode>());
                    parentMap.put(neighbour.vertex, cloneNeighbour);
                    parentMap.get(node.vertex).neighbours.add(cloneNeighbour);
                    queue.add(neighbour);
                } else {
                    parentMap.get(node.vertex).neighbours.add(parentMap.get(neighbour.vertex));
                }

            }
        }

        return clone;
    }

    public static void main(String[] args) {

        GraphNode original = new GraphNode(1, new ArrayList<GraphNode>());
        GraphNode two = new GraphNode(2, new ArrayList<GraphNode>());
        GraphNode three = new GraphNode(3, new ArrayList<GraphNode>());

        original.neighbours.add(two);
        original.neighbours.add(three);

        two.neighbours.add(original);
        two.neighbours.add(three);
        two.neighbours.add(two);

        three.neighbours.add(original);
        three.neighbours.add(two);


        GraphNode cloned = clone(original);


    }


}
