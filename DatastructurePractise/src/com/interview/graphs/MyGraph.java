package com.interview.graphs;

import java.util.*;

/**
 * generic graph representing directional and Undirectional graphs both
 * Created by abhishekm787 on 7/31/14.
 */
public class MyGraph<V extends Comparable<V>,E> {
    protected Map<Vertex<V>, List<Edge<V,E>>> graph = new HashMap<>();
    protected boolean isDirected;


    public MyGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public Vertex<V> addVertex(V data,Map<Object,Object> bag) {
        Vertex<V> vertex = new Vertex<>(data,bag);
        if(!graph.containsKey(vertex)) {
            graph.put(vertex, new ArrayList<Edge<V, E>>());
        }
        return vertex;
    }

    public Vertex<V> addVertex(V data) {
        return addVertex(data,null);
    }

    public int indegree(V vertex){
        int indegree = 0;
        for(Edge e: getAllEdges()){
            if(e.to.data.compareTo(vertex) ==0){
                indegree++;
            }
        }
        return indegree;
    }

    public int outdegree(V vertex){
         return graph.get(new Vertex<V>(vertex))!=null ? graph.get(new Vertex<V>(vertex)).size() : 0;
    }

    public Edge<V,E> addEdge(Vertex<V> from, Vertex<V> to, E data) {
       return addEdge(from,to,data,null);
    }

    public List<Edge<V,E>> getOutgoingEdges(V from) {
        return graph.get(new Vertex<>(from,null));
    }


    public Edge<V,E> addEdge(Vertex<V> from, Vertex<V> to, E data, Map<Object,Object> bag) {

        if(!graph.containsKey(to)){
            to = addVertex(to.data,to.bag);
        }

        if(!graph.containsKey(from)){
            from = addVertex(from.data,from.bag);
        }
        //try record the edge in the graph
        // if key is the form vertex, edge will be in the data
        Edge<V,E> newEdge = new Edge<>(from,to,data,bag);
        graph.get(from).add(newEdge);

        if(!this.isDirected){
            graph.get(to).add(new Edge<>(to,from,data,bag));
        }

        return newEdge;
    }


    /**
     * trick is to use a stack and keep track of the visited nodes
     * @param startVertex
     */
    public void dfsPrint(V startVertex) {
        Set<V> visited = new HashSet<>();
        LinkedList<Vertex<V>> stack = new LinkedList<>();

        stack.push(new Vertex<>(startVertex,null));
        while(!stack.isEmpty()) {
            Vertex<V> vertex = stack.pop();
            System.out.println(vertex);
            visited.add(vertex.data);
            for(Edge<V,E> edge : getOutgoingEdges(vertex.data)) {
                if(!visited.contains(edge.to.data)){
                    stack.push(edge.to);
                }
            }
        }

    }

    /**
     * trick is to use a queue and keep track of the visited nodes
     * @param startVertex
     */
    public void bfsPrint(V startVertex) {
        Set<V> visited = new HashSet<>();
        LinkedList<Vertex<V>> queue = new LinkedList<>();

        queue.addLast(new Vertex<>(startVertex,null));
        while(!queue.isEmpty()) {
            Vertex<V> vertex = queue.removeFirst();
            if(!visited.contains(vertex.data)){
                System.out.println(vertex);
                visited.add(vertex.data);
            }
            for(Edge<V,E> edge : getOutgoingEdges(vertex.data)) {
                if(!visited.contains(edge.to.data)){
                    queue.addLast(edge.to);
                }
            }
        }

    }


    public Set<Vertex<V>> getAllVertex() {
        return graph.keySet();
    }

    public List<Edge<V,E>> getAllEdges() {
        List<Edge<V,E>> response = new ArrayList<>();
        for(Vertex<V> v : getAllVertex()){
            response.addAll(graph.get(v));
        }
       return response;
    }


    public static void main(String[] args) {

        MyGraph<Integer,Integer> graph = new MyGraph<>(true);
        graph.addEdge(graph.addVertex(1), graph.addVertex(2),1);
        graph.addEdge(graph.addVertex(2), graph.addVertex(3),6);
        graph.addEdge(graph.addVertex(2), graph.addVertex(4),2);
        graph.addEdge(graph.addVertex(1), graph.addVertex(4),4);
        graph.addEdge(graph.addVertex(4), graph.addVertex(3),3);

        graph.bfsPrint(1);






    }



}
