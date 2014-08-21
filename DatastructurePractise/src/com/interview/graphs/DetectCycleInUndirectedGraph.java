package com.interview.graphs;


import com.interview.graphs.algorithm.SimpleGraph;

import java.util.*;

/**
 * Given an undirected graph, how to check if there is a cycle in the graph
 *
 * http://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 * Created by abhimaloo on 8/20/14.
 */
public class DetectCycleInUndirectedGraph extends SimpleGraph{

    /**
     * Trick is to do DFS while maintaining parent Stack as well.
     * If while Dfs'ing you hit a node which is already visited, check that if that node is not your parent.
     * If its not the parent that means there is a cycle
     *
     * @return
     */
    public boolean hasCycle() {
        List<Integer> vertices = new ArrayList<>(getAllVertex());
        // just get any vertex to start from
        Integer sourceVertex = vertices.get(0);
        // this is to keep track of visited stuff
        Map<Integer, Boolean> visited = new HashMap<>();
        // DFS main stack
        LinkedList<Integer> stack = new LinkedList<>();
        // this stack keeps the parent vertex of every child vertex which gets added
        LinkedList<Integer> parentStack = new LinkedList<>();
        // push the source vertex
        stack.push(sourceVertex);
        //parent of first node is null
        parentStack.push(null);

        // while stack has any elements left
        while(!stack.isEmpty()) {
            // pop the vertex from stack
            Integer vertex = stack.pop();
            //pop its parent
            Integer parent = parentStack.pop();
            // mark the vertex visited
            visited.put(vertex, true);
            // find all the adjacent nodes to this vertex
            for(Integer v: getAdjacentNeighbours(vertex, false)) {
                // if not visited already
                if(!visited.containsKey(v)) {
                   // push the neighbour to the stack
                   stack.push(v);
                   // push vertex as parent
                   parentStack.push(vertex);
                } else {
                    // if the neighbour has already been visited make sure that its not the parent pointer
                    // because in undirected graph u, v has u->v edge and v->u edge as well. so try to make sure its not the
                    // back edge
                    if(v != parent) {
                        // if its not parent return that there is a cycle
                        return true;
                    }
                }
            }


        }


        return false;
    }


    public static void main(String[] args) {
        DetectCycleInUndirectedGraph graph = new DetectCycleInUndirectedGraph();
        graph.insertEdge(1,2,0,false);
        graph.insertEdge(2,3,0,false);
        graph.insertEdge(3,4,0,false);
        graph.insertEdge(4,1,0,false);

        System.out.println(graph.hasCycle());

    }




}
