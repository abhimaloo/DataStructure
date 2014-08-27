package com.leetcode.linkedlist.impl;

/**
 * Created by abhimaloo on 8/25/14.
 */
public class Node {
    public int data;
    public Node next;
    public Node random;

    public Node(int data, Node next, Node random) {
        this.data = data;
        this.next = next;
        this.random = random;
    }
}
