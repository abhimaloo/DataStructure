package com.leetcode;

import com.interview.linkedlist.DoublyLinkedList;
import com.interview.linkedlist.DoublyNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 *
 * https://oj.leetcode.com/problems/lru-cache/
 * Created by abhimaloo on 8/23/14.
 */
public class LRUCache<K extends Comparable<K>, V> {
    class Pair<K extends Comparable<K>,V> {
        DoublyNode<K> node;
        V value;

        Pair(DoublyNode<K> node, V value) {
            this.node = node;
            this.value = value;
        }
    }

    public DoublyLinkedList<K> list = new DoublyLinkedList<>();
    public Map<K, Pair<K,V>> map  = new HashMap<>();
    public static int default_size = 5;

    public void put(K key, V value) {
        if(map.size() >= 5) {
            // evict it from map and list both
            map.remove(list.removeLast().data);
        }
        // insert the data at front in list and add it to hashMap
        map.put(key, new Pair<>(list.insertAtFront(key),value));
    }

    public V get(K key) {
        if(map.containsKey(key)) {
            // whenever some one access it move the keys to the front
            list.moveToFront(map.get(key).node);
            return map.get(key).value;
        }
        return null;
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> cache  = new LRUCache<>();
        cache.put("A",1);
        cache.put("B", 2);
        cache.put("C",3);
        cache.put("D",4);
        cache.put("E",5);
        //test eviction
        cache.put("F", 6);
        System.out.println(cache.get("A"));

    }


}
