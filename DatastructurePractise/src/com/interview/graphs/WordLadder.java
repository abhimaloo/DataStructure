package com.interview.graphs;

import com.interview.graphs.algorithm.SimpleEdge;
import com.interview.graphs.algorithm.SimpleGraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * Word Ladder
 *
 * https://oj.leetcode.com/problems/word-ladder/
 * Created by abhishekm787 on 8/21/14.
 */
public class WordLadder {

    public static String[] words = {"hit","hot","dot","dog","lot","log", "cog"};

    // trick is to construct a graph and do BFS to find the destination. BFS will try to find the destination in shortest way.
    // keep the parent pointer intact
    public static void findShortestPath(String []words, String startWord, String endWord) {
         // construct a graph with it
        SimpleGraph g = new SimpleGraph();
        // constructing the graph
        for(int i=0; i< words.length; i++) {
            for( int j = 0; j< words.length; j++) {
                // if the letters has exactly one letter difference
                 if(oneLetterChanged(words[i], words[j])) {
                     // insert an edge between the two words
                     g.insertEdge(i,j,1, false);
                 }
            }
        }

        // find shortest path from start to end using dijkstra
        int startIndex = -1; int endIndex = -1;
        for(int i = 0; i< words.length; i++) {
           if(words[i] == startWord){
               startIndex = i;
           }
           if(words[i] == endWord){
               endIndex = i;
           }

        }

        // do BFS and keep counting the levels
        LinkedList<Integer> queue = new LinkedList<>();
        Map<Integer, Boolean> visited = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        LinkedList<String> stack = new LinkedList<>();
        queue.addLast(startIndex);
        parentMap.put(startIndex, -1);

        while(!queue.isEmpty()) {
            // deque the first item
            Integer vertex = queue.removeFirst();
            // if its not visited before
            if(!visited.containsKey(vertex)){
                // check if this equals the endIndex
                if(vertex == endIndex) {
                    // push the word on the stack
                    stack.push(words[vertex]);
                    while(true) {
                        // keep on unwinding the parent pointer and push to stack
                        Integer parent = parentMap.get(vertex);
                        if(parent == -1) {
                            break;
                        }
                        stack.push(words[parent]);
                        vertex = parent;
                    }

                    break;

                }
                // mark the vertex as visited
                visited.put(vertex, true);
            }

            // iterate though all the outgoing edges
            for(SimpleEdge e: g.getOutgoingEdges(vertex)) {
                // add all the unvisited neighbours
                if(!visited.containsKey(e.to)) {
                    queue.addLast(e.to);
                    parentMap.put(e.to, vertex);
                }
            }

        }

        // print the result
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }



    private static boolean oneLetterChanged(String from, String to) {
        if(from.length() != to.length()) {
            return false;
        }

        int diff = 0;
        for( int i=0; i< from.length() ; i++) {
            if(from.charAt(i) != to.charAt(i)){
                if(diff > 1) {
                    return false;
                }
                diff ++;
            }
        }

        return diff == 1 ?true: false;
    }

    public static void main(String[] args) {

        findShortestPath(words, "hit", "cog");
    }
}
