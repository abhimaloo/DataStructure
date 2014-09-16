package com.crackCode.linkedlist;

/**
 * Created by abhimaloo on 9/14/14.
 */
public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }
}
