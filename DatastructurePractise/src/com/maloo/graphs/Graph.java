package com.maloo.graphs;

import java.util.*;

/**
 * General representation of a graph using adjancency List
 * Created by abhishekm787 on 6/30/14.
 */
public class Graph {

   private Map<Integer,List<Edge>> store = new Hashtable<>();

   public void addVertex(int vertex) {
       if(!contains(vertex)){
           store.put(vertex,new ArrayList<Edge>());
       }
   }

   public List<Edge> getEdges(int vertex) {
       return store.get(vertex);
   }

   public Set<Integer> getAllVertex(){
       return store.keySet();
   }

   public List<Edge> getAllEdges() {
       List<Edge> response = new ArrayList<>();
       for(Integer key:store.keySet()) {
           response.addAll(store.get(key));
       }

       return response;
   }

   public boolean contains(int vertex) {
        return store.containsKey(vertex);
   }

   public void addEdge(int from, int to, int weight) {

          if(!contains(to)){
              addVertex(to);
          }

          if(!contains(from)){
              addVertex(from);
          }

          store.get(from).add(new Edge(to, from, weight));
   }


   public void dfs(int startPoint, Set<Integer> visited){

       if(!visited.contains(startPoint)){
           //visit it
           System.out.println(startPoint);
           visited.add(startPoint);
       }

       //get all out going edges from start point
       for(Edge e: getEdges(startPoint)){
           dfs(e.to,visited);
       }

   }


   public void bfs(int startpoint) {
       LinkedList<Integer> queue = new LinkedList<>();
       Set<Integer> visited = new HashSet<>();

       //add the start point to queue
       queue.addLast(startpoint);

       while(!queue.isEmpty()){
           int vertex = queue.removeFirst() ;
           if(!visited.contains(vertex)){
               //visit the vertex
               System.out.println(vertex);
               visited.add(vertex);
           }
           // add the next neighbours
           for (Edge e: getEdges(vertex)){
               queue.add(e.to);
           }

       }



   }


    public static void main(String[] args) {
        Graph g = new Graph();

        g.addEdge(1,2,1);
        g.addEdge(2,3,6);
        g.addEdge(2,4,2);
        g.addEdge(1,4,4);
        g.addEdge(4,3,3);

        g.bfs(1);
    }


    class Edge {
        public int to;
        public int from;
        public int weight;

        Edge(int to, int from, int weight) {
            this.to = to;
            this.from = from;
            this.weight = weight;
        }
    }



}
