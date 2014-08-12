package com.interview.trees.impl;

/**
 * Created by abhishekm787 on 8/12/14.
 */
public class BinaryTreeNode<T extends Comparable<T>> {
    public T data;
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;

    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryTreeNode<T> addLeft(T data) {
        this.left = new BinaryTreeNode<>(data, null ,null);
        return this;
    }

    public BinaryTreeNode<T> addRight(T data) {
        this.right = new BinaryTreeNode<>(data, null ,null);
        return this;
    }

}
