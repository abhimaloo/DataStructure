package com.interview.string;

import com.interview.graphs.impl.TopologicalSort;
import com.interview.graphs.impl.Vertex;

/**
 * Given a sorted dictionary (array of words) of an alien language, find order of characters in the language.
 * Input:  words[] = {"baa", "abcd", "abca", "cab", "cad"}
 * Output: Order of characters is 'b', 'd', 'a', 'c'
 * Note that words are sorted and in the given language "baa"
 * comes before "abcd", therefore 'b' is before 'a' in output.
 * Similarly we can find other orders.
 *
 * Created by abhimaloo on 7/31/14.
 */
public class FindOrderOFCharactersInAlienLanguage {
    public static String[] words = {"baa", "abcd", "abca", "cab", "cad"};

    /**
     * trick is to convert the words into graph.
     * iterate on pair of words and try finding first mismatching alphabet among two words.
     * Let the mismatching alphabets be 'a' and 'b'. Create an edge into a graph from vertex a to vertex b.
     * do it for all the word pairs.
     *
     * finally run a topological sort on the graph. It will respond with the order of the vertices
     *
     */
    public static void findOrder() {

        TopologicalSort<String,String> graph = new TopologicalSort<>(true);
        for(int i=0; i<words.length-1; i++) {
           String word1 = words[i];
           String word2 = words[i+1];

           for(int j=0; j< Math.min(word1.length(),word2.length()); j++) {
               if(word1.charAt(j) != word2.charAt(j)){
                   graph.addEdge(new Vertex<String>(String.valueOf(word1.charAt(j))),
                           new Vertex<String>(String.valueOf(word2.charAt(j))), null);
                   break;
               }
           }
        }

        //now call topological sort and print the order
        for(Vertex<String> v:  graph.sort()) {
            System.out.println(v);
        }
    }

    public static void main(String[] args) {
        findOrder();
    }

}
