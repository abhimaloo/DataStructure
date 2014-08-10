package com.interview.trees.impl;

/**
 * Created by abhimaloo on 8/8/14.
 */
public class BSTNode<T extends Comparable<T>> {
    public T data;
    public BSTNode<T> left;
    public BSTNode<T> right;

    public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
