package com.leetcode2022.datastructures;

public interface MyConcurrentMap<K, V> extends MyMap<K, V> {
    V putIfAbsent(K key, V value);

    boolean remove(Object key, Object value);

    boolean replace(K key, V oldValue, V newValue);

    V replace(K key, V value);
}
