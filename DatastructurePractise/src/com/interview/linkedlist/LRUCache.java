package com.interview.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhishekm787 on 7/29/14.
 */
public class LRUCache<K extends Comparable<K>,V> {
    private Map<K, Pair<K>> lookupStore = new HashMap<>();
    private DoublyLinkedList<K> doublyList = new DoublyLinkedList<>();
    private static int PAGE_SIZE = 5;

    public void put(K key, V value) {

       if(lookupStore.containsKey(key)){


       } else {

           if(doublyList.size == PAGE_SIZE){
               System.out.println("Evict: "+ doublyList.removeLast().data);
           }
           DoublyNode<K> node = doublyList.insertAtFront(key);
           lookupStore.put(key, new Pair<K>(node,value));
       }

    }

    private class Pair<K extends  Comparable<K>>{
       DoublyNode<K> link;
       V value;

        private Pair(DoublyNode<K> link, V value) {
            this.link = link;
            this.value = value;
        }
    }
}

