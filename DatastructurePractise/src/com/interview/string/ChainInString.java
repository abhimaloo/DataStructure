package com.interview.string;

import com.interview.graphs.KosarajuSCC;
import com.interview.graphs.Vertex;

/**
 * Given an array of strings, find if the given strings can be chained to form a circle.
 * A string X can be put before another string Y in circle
 * if the last character of X is same as first character of Y
 *
 * Input: arr[] = {"for", "geek", "rig", "kaf"}
 * Output: Yes, the given strings can be chained.
 * The strings can be chained as "for", "rig", "geek"
 * and "kaf"
 * Created by abhishekm787 on 7/31/14.
 */
public class ChainInString {
    public static String[] words = {"for", "geek", "rig", "kaf"};

    /**
     * idea is to create a graph out of these strings and check whether there is a Euler circuit in them.
     * Euler Circuit has two conditions :
     * a) Indegree and Outdegree of all the vertices should be same
     * b) All Non zero degree vertices should be a part of single SCC
     *
     * @param words
     * @return
     */
    public static boolean isChainPresent(String[] words) {

        //Step 1 - create the graph from first char and last char of each string
        KosarajuSCC<String,String> graph = new KosarajuSCC<>(true);

        for(int i=0; i< words.length; i++){
            graph.addEdge(new Vertex<String>(String.valueOf(words[i].charAt(0)).toLowerCase()),
                    new Vertex<String>(String.valueOf(words[i].charAt(words[i].length()-1)).toLowerCase()), words[i]);
        }


        // Step 2 - check the in-degree and out-degree of every vertex to be same
        int indegree = 0;
        int outdegree = 0;
        boolean sameIndegreeOutDegrees = true;
        for(Vertex<String> vertex : graph.getAllVertex()){
            indegree =  graph.indegree(vertex.data);
            outdegree =  graph.outdegree(vertex.data);
            sameIndegreeOutDegrees &=  indegree == outdegree;
        }

        // Step 3 - make sure there is only 1 SCC for this graph
        return graph.isSCC() & sameIndegreeOutDegrees;
    }

    public static void main(String[] args) {

        System.out.println("Is Chain present : "+ isChainPresent(words) );
    }
}
