package com.interview.graphs;

import com.interview.graphs.algorithm.SimpleGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Color the vertices of graph with colors so that no two adjacent vertex has the same color
 *
 * http://www.geeksforgeeks.org/graph-coloring-set-2-greedy-algorithm/
 * Created by abhishekm787 on 8/20/14.
 */
public class GraphColoringProblem extends SimpleGraph{

    /**
     * Solution is to use backtracking algorithm
     * assign colors to all the vertices and finally find the maxColor from the assignment
     * @return
     */
    public int colorGraph() {
       Map<Integer, Integer> colorMap = new HashMap<>();
       // this is where the backtracking occurs
       colorIt(0, new ArrayList<Integer>(getAllVertex()), colorMap);

       int maxColors = -1;
       // color map has all the assignments
        // iterate through it and find the maxColor
       for(Integer vertex: getAllVertex()) {
           if(maxColors < colorMap.get(vertex)) {
               maxColors = colorMap.get(vertex);
           }
       }
       return maxColors;
    }

    /**
     * This is where the backtracking is happening ..
     *  We run colors from minimum to max of (Vertex size)
     *  try checking the color number in increasing order if it can be applied
     *  we recurse if a color can be applied to a particular vertex and go to the next ndoe ..
     *  very much like 8 queen problem
     * @param sourceVertexIndex
     * @param allVertices
     * @param colorMap
     * @return
     */
    public boolean colorIt(Integer sourceVertexIndex, List<Integer> allVertices, Map<Integer, Integer> colorMap) {
        // base case .. all the vertices has been colored
        if(sourceVertexIndex == allVertices.size()) {
            return true;
        } else {
            // iterate through all the colors in increasing order
            for(int color = 1; color <= allVertices.size(); color++) {
                // check if you can color a vertex with this particular color
                 if(canColor(allVertices.get(sourceVertexIndex), color, colorMap)) {
                     // color the vertex
                     colorMap.put(allVertices.get(sourceVertexIndex), color);
                     // recurse to next vertex
                     if(colorIt(sourceVertexIndex +1, allVertices, colorMap)) {
                         return true;
                     } else {
                          // thats where backtrack happens .. if color assignment is not succcessfull
                         // unrecord or uncolor the vertex and give a shot to next color
                         colorMap.put(allVertices.get(sourceVertexIndex), 0);
                     }

                 }
            }
        }

        return false;

    }

    /**
     * This routine checks whether a vertex can be colored by a particular color or not.
     * @param sourceVertex
     * @param color
     * @param colorMap
     * @return
     */
    private boolean canColor(Integer sourceVertex, int color, Map<Integer, Integer> colorMap) {
        // look at all the adjacent vertices ..
        for(Integer vertex : getAdjacentNeighbours(sourceVertex, false)) {
            // if any one of them is colored then the color should not match
             if(colorMap.get(vertex) != null && colorMap.get(vertex) == color) {
                 return false;
             }
        }

        return true;
    }


    public static void main(String[] args) {

        GraphColoringProblem graph = new GraphColoringProblem();
        graph.insertEdge(1,2,0,false);
        graph.insertEdge(2,3,0,false);
        graph.insertEdge(3,4,0,false);
        graph.insertEdge(3,5,0,false);
        graph.insertEdge(2,5,0,false);
        graph.insertEdge(1,5,0,false);

        System.out.println("Max Colors Required : "+ graph.colorGraph());

    }

}
