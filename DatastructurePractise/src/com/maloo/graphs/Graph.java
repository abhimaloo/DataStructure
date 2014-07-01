package com.maloo.graphs;

import java.util.*;

/**
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


   public List<Edge> dijkstraShortestPath(int sourceVertex, int destinationVertex) {
       Set<Integer> mould = new HashSet<>();
       Set<Integer> universe = new HashSet<>();
       universe.addAll(getAllVertex());

       Map<Integer,List<Edge>> shortestPath = new HashMap<>();
       final Map<Integer,Integer> shortestCost = new HashMap<>();

       mould.add(sourceVertex);
       universe.remove(sourceVertex);
       shortestPath.put(sourceVertex,new ArrayList<Edge>());
       shortestCost.put(sourceVertex,0);

       PriorityQueue<Edge> heap = new PriorityQueue<>(universe.size(),new Comparator<Edge>(){

           @Override
           public int compare(Edge e1, Edge e2) {

               return Integer.compare(e1.weight+shortestCost.get(e1.from), e2.weight+shortestCost.get(e2.from));
           }
       });

       while(!universe.isEmpty()) {
           // go through all the vertices of the mould
           for(int vertex: mould){
               // check all outgoing edges

                for(Edge edge: getEdges(vertex)) {
                    // add the edge which is not targetting to a edge present in the mould
                    if(!mould.contains(edge.to)){
                        heap.add(edge);
                    }
                }
           }

           //this will find the next edge to be included which has minimum shotestCost till origin +weight
           //remove it from the heap
           Edge target = null;
           while(true) {
               target = heap.remove();
               if(!mould.contains(target.to)){
                   break;
               }
           }


           // remove the target vertex from universe
           universe.remove(target.to);
           // add it to mould
           mould.add(target.to);

           //update the shortest path
           List<Edge> shortestPathForTarget = new ArrayList<>();
           shortestPathForTarget.addAll(shortestPath.get(target.from));
           shortestPathForTarget.add(target);
           shortestPath.put(target.to,shortestPathForTarget);

           // update the shortest cost
           shortestCost.put(target.to, shortestCost.get(target.from) + target.weight );

       }

       return shortestPath.get(destinationVertex);
   }


    public static void main(String[] args) {
        Graph g = new Graph();

        g.addEdge(1,2,1);
        g.addEdge(2,3,6);
        g.addEdge(2,4,2);
        g.addEdge(1,4,4);
        g.addEdge(4,3,3);


        for(Edge e: g.dijkstraShortestPath(1,3))
        {
            System.out.println(e.from);
        }
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
