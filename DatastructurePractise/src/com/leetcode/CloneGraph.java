package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 *    As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * https://oj.leetcode.com/problems/clone-graph/
 * Created by abhishekm787 on 8/26/14.
 */
public class CloneGraph {

    class GraphNode {
        int label;
        List<GraphNode> neighbours;

        GraphNode(int label, List<GraphNode> neighbours) {
            this.label = label;
            this.neighbours = neighbours;
        }
    }

    /**
     * first convert the string into desrialized graph ..
     * graph is nothing but a map of label and graph nodes .
     * Each graph node contains the label and the list of the adjance vertices
     * @param serializedGraph
     * @return
     */
    public Map<Integer, GraphNode> deserialize(String serializedGraph) {

        String[] nodes =  serializedGraph.split("#");
        Map<Integer, GraphNode> graph = new HashMap<>();

         for(int i = 0; i < nodes.length; i++ ) {
             String [] elements = nodes[i].split(",");
             for( int j = 0; j < elements.length; j++) {
                 if( j == 0) {
                     // if the key is not already been created
                     if(!graph.containsKey(new Integer(elements[j]))) {
                         // create a new graph node
                         GraphNode node = new GraphNode(Integer.parseInt(elements[j]), new ArrayList<GraphNode>()) ;
                         graph.put(node.label, node);

                     }
                 } else {

                     if(!graph.containsKey(new Integer(elements[j]))) {
                         GraphNode node = new GraphNode(Integer.parseInt(elements[j]), new ArrayList<GraphNode>()) ;
                         graph.put(node.label, node);
                     }
                    // add the element as neighbour of source index
                    graph.get(new Integer(elements[0])).neighbours.add(graph.get(new Integer(elements[j])));;

                 }
             }

         }

        return graph;
    }

    public Map<Integer, GraphNode> clone(GraphNode source, Map<Integer, GraphNode> cloned) {
       // try DFS form source
        if(source == null) {
            return null;
        }
        // if cloned graph does not contain the node
        if(!cloned.containsKey(new Integer(source.label))) {
            // create by copying the souce node data
            GraphNode newNode = new GraphNode(source.label, new ArrayList<GraphNode>());
            cloned.put(new Integer(source.label), newNode);
        }

        //iterate through all the neighbours
        if(source.neighbours.size() > 0) {
            for(GraphNode neighbour : source.neighbours) {
                // if neighbour is not present in the graph
                if(!cloned.containsKey(new Integer(neighbour.label))) {
                    // create the neighbour by copying the content of neighbour
                    cloned.put(new Integer(neighbour.label), new GraphNode(neighbour.label, new ArrayList<GraphNode>()));
                }
                // if cloned source does not contain neighbour as one of its neighbour. It saves us from cyclic dependencies
                if(!cloned.get(new Integer(source.label)).neighbours.contains(cloned.get(new Integer(neighbour.label)))) {
                    // add the cloned neighbour to the neighbour list
                    cloned.get(new Integer(source.label)).neighbours.add(cloned.get(new Integer(neighbour.label)));
                    // recurse by making neghbour as source
                    cloned = clone(neighbour, cloned);
                }

            }
        }


        return cloned;

    }


    public static void main(String[] args) {
        CloneGraph g = new CloneGraph();
        Map<Integer, GraphNode> sourceGraph = g.deserialize("0,1,2#1,2#2,2");
        Map<Integer, GraphNode> clonedGraph = new HashMap<>();
        clonedGraph = g.clone(sourceGraph.get(new Integer(0)), clonedGraph);
    }


}
