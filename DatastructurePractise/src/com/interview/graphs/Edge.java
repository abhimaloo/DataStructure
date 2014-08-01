package com.interview.graphs;

import java.util.Map;

/**
 * Created by abhishekm787 on 7/31/14.
 */
public class Edge<V extends Comparable<V>,E> {
    public Vertex<V> from;
    public Vertex<V> to;
    public E data;
    Map<Object,Object> bag ;

    public Edge(Vertex<V> from, Vertex<V> to, E data, Map<Object, Object> bag) {
        this.to = to;
        this.from = from;
        this.data = data;
        this.bag = bag;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + data +
                '}';
    }
}
