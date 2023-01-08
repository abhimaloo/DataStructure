package com.leetcode2022.top_facebook;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.



Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4


Constraints:

1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.
 */
public class LRUCache {
    Map<Integer, Integer> cache = null;
    LinkedList<Integer> prioritiyQueue = null;
    int capacity = 0;

    public LRUCache(int capacity) {
        cache = new HashMap<>();
        prioritiyQueue = new LinkedList<Integer>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            prioritiyQueue.removeLastOccurrence(key);
            prioritiyQueue.offerFirst(key);
            return cache.get(key);
        }

        return -1;
    }

    public void put(int key, int value) {
        // update if found
        if (cache.containsKey(key)) {
            cache.put(key, value);
            prioritiyQueue.removeLastOccurrence(key);
            prioritiyQueue.offerFirst(key);
        } else {
            // make space if needed
            if (cache.size() >= capacity) {
                int keyToRemove = prioritiyQueue.removeLast();
                cache.remove(keyToRemove);
            }
            cache.put(key, value);
            prioritiyQueue.offerFirst(key);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
