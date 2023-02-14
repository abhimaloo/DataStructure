package com.leetcode2022.datastructures;

public interface MyMap<K, V> {
    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    V get(Object key);

    void put(K key, V value);

    void remove(Object key);
}
