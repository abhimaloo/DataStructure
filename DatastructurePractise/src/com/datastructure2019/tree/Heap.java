package com.datastructure2019.tree;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    public List<HeapNode> store = new ArrayList<>();
    private int index = -1;

    public Heap() {
        // adding a dummy node at index 0
        this.store.add(++index, new HeapNode(-1, -1));
    }

    public void insert(HeapNode node) {
        if (node != null) {
            store.add(++index, node);
        }
        bubbleUp(index);
    }

    private void bubbleUp(int index) {
        if (store.get(index).key > store.get(index / 2).key || index == 1) {
            return;
        } else {
            // swap parent and child
            swap(index, index / 2);
            bubbleUp(index / 2);
        }
    }

    private void swap(int idx1, int idx2) {
        HeapNode temp = store.get(idx1);
        store.set(idx1, store.get(idx2));
        store.set(idx2, temp);
    }

    public HeapNode removeMin() {
        if (store.size() == 1) {
            return null;
        }
        // only root node left, no need to heapify
        if (index == 1) {
            return store.remove(index--);
        }
        // pluck the root node
        HeapNode response = store.get(1);
        // reheap if heap is bigger than 1 node
        if (index > 1) {
            // insert the last node at root
            store.set(1, store.get(index));
            // pluck it form the bottom
            store.remove(index--);
            bubbleDown(1);
        }
        return response;
    }

    public void bubbleDown(int idx) {
        //check for left child
        int leftChild = store.size() - 1 >= idx * 2 ? store.get(idx * 2).key : Integer.MAX_VALUE;
        int rightChild = store.size() - 1 >= (idx * 2) + 1 ? store.get((idx * 2) + 1).key : Integer.MAX_VALUE;
        if (leftChild <= rightChild && store.get(idx).key > leftChild) {
            swap(idx, idx * 2);
            bubbleDown(idx * 2);
        } else if (leftChild >= rightChild && store.get(idx).key > rightChild) {
            swap(idx, (idx * 2) + 1);
            bubbleDown((idx * 2) + 1);
        } else {
            return;
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(new HeapNode(5, 0));
        heap.insert(new HeapNode(4, 0));
        heap.insert(new HeapNode(3, 0));
        heap.insert(new HeapNode(2, 0));
        heap.insert(new HeapNode(1, 0));

        System.out.println(heap.removeMin().key);
        System.out.println(heap.removeMin().key);
        System.out.println(heap.removeMin().key);
        System.out.println(heap.removeMin().key);
        System.out.println(heap.removeMin().key);
    }
}

class HeapNode {
    int key;
    int value;

    public HeapNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
