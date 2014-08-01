package com.interview.graphs;

import java.util.Map;

/**
 * Created by abhishekm787 on 7/31/14.
 */
public class Vertex<V extends Comparable<V>> {
    public V data;
    public Map<Object,Object> bag;

    public Vertex(V data, Map<Object, Object> bag) {
        this.data = data;
        this.bag = bag;

    }
    public Vertex(V data) {
        this(data,null);

    }

    @Override
    public int hashCode(){
       return data.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Vertex<V> otherVertex = (Vertex<V>) obj;
        return (this.data.compareTo(otherVertex.data) ==0);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "data=" + data +
                '}';
    }
}
