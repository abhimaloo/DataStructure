package com.interview.linkedlist;

import java.util.HashMap;
import java.util.Map;

/** Implement LRU cache
 * Created by abhishekm787 on 7/29/14.
 */
public class LRUCache<K extends Comparable<K>,V> {
    // this is hashMap which gives O(1) lookup,
    //it saves a container object containing Node of the linked list and the value
    private Map<K, Pair<K>> lookupStore = new HashMap<>();
    // this is to  impose LRU eviction policy
    private DoublyLinkedList<K> doublyList = new DoublyLinkedList<>();
    //eviction trigger
    private static int PAGE_SIZE = 5;


    public void put(K key, V value) {
       if(lookupStore.containsKey(key)){
           //update scenario
           DoublyNode<K> frontNode = doublyList.moveToFront(lookupStore.get(key).link);
           lookupStore.put(key,new Pair(frontNode,value));
       } else {
            //insert scenario
           if(doublyList.size == PAGE_SIZE){
               DoublyNode<K> removedNode = doublyList.removeLast();
               System.out.println("Evict: "+ removedNode.data);
               //evict it from the cache as well
               lookupStore.remove(removedNode.data);
           }
           DoublyNode<K> node = doublyList.insertAtFront(key);
           lookupStore.put(key, new Pair(node,value));
       }
    }

    public V get(K key) {
        if(lookupStore.containsKey(key)){
            DoublyNode<K> frontNode = doublyList.moveToFront(lookupStore.get(key).link);
            return lookupStore.get(key).value;
        } else {
            return null;
        }
    }

    public V remove(K key) {
        if(lookupStore.containsKey(key)){
            doublyList.remove(key);
            return lookupStore.remove(key).value;
        }
        return null;
    }

    private class Pair<K extends  Comparable<K>>{
       DoublyNode<K> link;
       V value;

        private Pair(DoublyNode<K> link, V value) {
            this.link = link;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache<String,Integer> cache = new LRUCache<>();
        cache.put("A",1);
        cache.put("B", 2);
        cache.put("C",3);
        cache.put("D",4);
        cache.put("E",5);
        //test eviction
        cache.put("F", 6);

        cache.remove("B");
        cache.remove("C");
        cache.remove("A");



    }

}

