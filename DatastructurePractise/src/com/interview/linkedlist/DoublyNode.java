package com.interview.linkedlist;

/**
 * Created by abhishekm787 on 7/29/14.
 */
public class DoublyNode<T extends Comparable<T>> {
    public T data;
    public DoublyNode<T> next;
    public DoublyNode<T> previous;

    public DoublyNode(T data, DoublyNode<T> next, DoublyNode<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
}
