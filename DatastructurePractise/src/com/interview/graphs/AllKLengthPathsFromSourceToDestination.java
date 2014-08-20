package com.interview.graphs;

import com.interview.graphs.algorithm.SimpleEdge;
import com.interview.graphs.algorithm.SimpleGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a directed graph and two vertices ‘u’ and ‘v’ in it, count all possible walks from ‘u’ to ‘v’ with exactly k edges on the walk
 *
 * http://www.geeksforgeeks.org/count-possible-paths-source-destination-exactly-k-edges/
 * Created by abhimaloo on 8/19/14.
 */
public class AllKLengthPathsFromSourceToDestination extends SimpleGraph{

    public int countWalksWithKEdges(Integer sourceVertex, Integer destinationVertex, int k) {

        Set<Integer> vertices = getAllVertex();

        class Pair {
            Integer xVertex;
            Integer yVertex;
            Integer kLength;

            Pair(Integer xVertex, Integer yVertex, Integer kLength) {
                this.xVertex = xVertex;
                this.yVertex = yVertex;
                this.kLength = kLength;
            }

            @Override
            public boolean equals(Object o) {
                Pair other  = (Pair) o;
                if(other.xVertex == this.xVertex && this.yVertex == this.yVertex && this.kLength == this.kLength){
                    return true;
                }
                return false;
            }

            @Override
            public int hashCode() {
                int hash = 7;
                hash = 31 * hash + xVertex;
                hash = 31 * hash + yVertex;
                hash = 31 * hash + kLength;

                return hash;
            }

        }

        Map<Pair,Integer> countOfWalks = new HashMap();

        for( int l = 0; l <= k; l++) {

            for(Integer i : vertices) {
                for(Integer j : vertices) {
                    // initialize
                    countOfWalks.put(new Pair(i,j,l), 0);

                    // path from same ndoe to same node with 0 edges is 1
                    if(l == 0 && i == j) {
                        countOfWalks.put(new Pair(i,j,l), 1);
                    }
                    // for single length paths , if i and j contains an edge etween them .. mark the count to be 1
                    if(l ==1 && hasEdge(i, j)) {
                        countOfWalks.put(new Pair(i,j,l), 1);
                    }

                    // for multiple length paths
                    if(l > 1) {
                        // find the adjacent vertices of i
                        for(Integer m : vertices) {
                            // if there is a direct edge from  i to m
                            if(hasEdge(i,m)) {
                                int count = countOfWalks.get(new Pair(i,j,l));
                                //using DP add the count of walks starting from vertex m to desitnation j using l-1 budget
                                count += countOfWalks.get(new Pair(m,j,l-1));
                                // all such m's will contribute to numbe rof walks from i to j
                                countOfWalks.put(new Pair(i,j,l), count);
                            }
                        }
                    }


                }
            }
        }

        return countOfWalks.get(new Pair(sourceVertex, destinationVertex, k));
    }

    private boolean hasEdge(Integer i, Integer j) {
        List<SimpleEdge> edges = getOutgoingEdges(i);
        if(edges != null) {
            for( SimpleEdge e: edges) {
                if(e.to == j) {
                    return  true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        AllKLengthPathsFromSourceToDestination graph = new AllKLengthPathsFromSourceToDestination();
        graph.insertEdge(0,1,0);
        graph.insertEdge(0,2,0);
        graph.insertEdge(0,3,0);
        graph.insertEdge(2,3,0);
        graph.insertEdge(1,3,0);

        System.out.println(graph.countWalksWithKEdges(0, 3, 2));


    }
}
