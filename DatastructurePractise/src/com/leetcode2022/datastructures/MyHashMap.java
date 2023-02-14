package com.leetcode2022.datastructures;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private volatile int size = 0;
    private static int DEFAULT_SIZE = 16;
    private int maxSize = 0;
    private double loadFactor = 0.0;
    private static double DEFAULT_LOAD_FACTOR = 0.7;
    private Entry<K, V>[] backingStore;

    private static final int hash(Object key) {
        return key == null ? 0 : key.hashCode();
    }

    public MyHashMap() {
        maxSize = DEFAULT_SIZE;
        backingStore = new Entry[maxSize];
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int initialSize) {
        this.maxSize = initialSize;
        backingStore = new Entry[maxSize];
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.maxSize = initialSize;
        this.loadFactor = loadFactor;
        backingStore = new Entry[maxSize];
    }

    class Entry<K, V> {
        int hash;
        K key;
        V value;
        Entry<K, V> next;


        public Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public V get(Object key) {
        if (size > 0) {
            //first hash the key and check the index
            int hash = hash(key);
            int bucket = indexFor(hash, this.maxSize);
            Entry<K, V> target = backingStore[bucket];
            while (target != null) {
                if (target.hash == hash && target.key.equals(key)) {
                    return target.value;
                } else {
                    target = target.next;
                }
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        // if the size of the hashmap is above a certain threshold - we need to expand the backing store
        // and rehash the content
        if (size >= this.maxSize * this.loadFactor) {
            resize(this.maxSize * 2);
        }

        // lets put the value at the right place
        int hash = key.hashCode();
        int indexFor = indexFor(hash, this.maxSize);

        Entry<K, V> target = backingStore[indexFor];

        while (target != null) {
            // update value scenario
            if (target.hash == hash && target.key.equals(key)) {
                target.value = value;
                return;
            }
            target = target.next;
        }
        // idea is to add the node at the front of the linkedList
        backingStore[indexFor] = new Entry<>(hash, key, value, backingStore[indexFor]);
        size++;
    }

    private void resize(int newCapacity) {
        Entry<K, V>[] newStore = new Entry[newCapacity];

        for (Entry<K, V> oldEntry : this.backingStore) {
            Entry<K, V> oldHead = oldEntry;
            while (oldHead != null) {
                int newIndex = indexFor(oldHead.hash, newCapacity);
                newStore[newIndex] = new Entry<>(oldHead.hash, oldHead.key, oldHead.value, newStore[newIndex]);
                oldHead = oldHead.next;
            }
        }

        this.backingStore = newStore;
        this.maxSize = newCapacity;
    }

    private int indexFor(int hash, int length) {
        //trick to avoid negative hash code and hash value more than Integer.MAX_VALUE
        // 0x7fffffff means 0111 1111 1111 1111 1111 1111 1111 1111 .. doing a logical and with this value will unset the sign bit
        // even negative thing will become positive
        return (hash & 0x7fffffff) % length;
    }

    @Override
    public void remove(Object key) {
        if (size > 0 && containsKey(key)) {
            int hash = hash(key);
            int index = indexFor(hash, this.maxSize);
            Entry<K, V> target = backingStore[index];
            // no need to check since contains key already checked it
            if (target.next == null) { // check if its the first node
                backingStore[index] = target.next;
                size--;
                return;
            } else { // this is where you delete from a linkedList
                Entry<K, V> previous = target;
                target = target.next;
                while (target != null) {
                    if (target.hash == hash && target.key == target.key) {
                        previous.next = target.next;
                        size--;
                        return;
                    } else {
                        previous = target;
                        target = target.next;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        MyMap<Integer, Integer> myMap = new MyHashMap(4);
        myMap.put(1, 1);
        myMap.put(5, 5);
        myMap.put(9, 9);
        myMap.put(4, 4);
        myMap.put(4, 5);

        System.out.println(myMap.size());
        myMap.remove(4);
        System.out.println(myMap.size());
    }
}
