package com.interview.graphs;

import com.interview.graphs.algorithm.SimpleGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Check if the graph is 2 colorable or bipartitie
 * Created by abhimaloo on 8/20/14.
 */
public class IsBipartite extends SimpleGraph {
    // its the same graph coloring algorithm using backtracking
    // this time number of colors allowed are only 2
    public boolean isBipartite() {
        //color the graph using minimum numbers of colors
        Map<Integer, Integer> colorMap = new HashMap<>();
        return colorGraph(0,colorMap, new ArrayList<Integer>(getAllVertex()));
    }

    // thats where backtracking happens
    private boolean colorGraph(int index, Map<Integer, Integer> colorMap, List<Integer> allVertices) {
        // if index reaches size return true
        if(index == allVertices.size()) {
            return true;
        } else {
            // iterate over two colors 1 and 2
            for(int color = 1; color< 3; color++) {
                // is vertex colorable by color
                if(isColorable(allVertices.get(index), color, colorMap)) {
                    //color the graph
                    colorMap.put(allVertices.get(index), color);
                    // recurse
                    if(colorGraph(index+1, colorMap, allVertices)) {
                        return true;
                    } else {
                        // backtrack by unrecording the selection made in this step
                        colorMap.put(allVertices.get(index), 0);
                    }
                }
            }
        }
        // return false since it has not yet returned inside the for loop
        return false;

    }

    private boolean isColorable(Integer vertex, int color, Map<Integer, Integer> colorMap) {
        // iterate over the adjacent neighbours
        for(Integer v : getAdjacentNeighbours(vertex, false)) {
            // check if any colored neighbour has the same color or not
            if(colorMap.get(v) != null && colorMap.get(v) == color) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsBipartite graph = new IsBipartite();
        graph.insertEdge(1,2,0,false);
        graph.insertEdge(3,4,0,false);
        graph.insertEdge(5,4,0,false);
        graph.insertEdge(5,2,0,false);

        System.out.println(graph.isBipartite());


    }
}
