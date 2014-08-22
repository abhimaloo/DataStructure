package com.interview.backtracking;

import com.interview.graphs.algorithm.SimpleGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abhimaloo on 8/21/14.
 */
public class MColorableGraph extends SimpleGraph{

    public boolean isMcolorable(List<Integer> vertices, int m, Integer index, Map<Integer,Integer> colorMap) {
        if(index == vertices.size()) {
            return true;
        } else {
            for( int color = 0; color < m; color ++) {
                if(isValidColor(vertices.get(index), color, colorMap)) {
                    colorMap.put(vertices.get(index), color);
                    if(isMcolorable(vertices,m,index+1,colorMap)) {
                        return true;
                    }
                    //backtrack
                    colorMap.remove(vertices.get(index));
                }
            }
        }

        return false;

    }

    private boolean isValidColor(Integer vertex, int color, Map<Integer,Integer> colorMap) {
        for(Integer v : getAdjacentNeighbours(vertex,false)){
            if(colorMap.containsKey(v) && colorMap.get(v) == color) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MColorableGraph graph = new MColorableGraph();
        graph.insertEdge(1,2,0,false);
        graph.insertEdge(2,3,0,false);
        graph.insertEdge(3,4,0,false);
        graph.insertEdge(3,5,0,false);
        graph.insertEdge(2,5,0,false);
        graph.insertEdge(1,5,0,false);

        System.out.println(graph.isMcolorable(new ArrayList<Integer>(graph.getAllVertex()),3,0,new HashMap<Integer, Integer>()));



    }
}
