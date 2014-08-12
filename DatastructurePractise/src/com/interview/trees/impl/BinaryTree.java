package com.interview.trees.impl;


/**
 * Created by abhishekm787 on 8/12/14.
 */
public class BinaryTree<T extends Comparable<T>> {

    public BinaryTreeNode<T> root;

    public BinaryTreeNode<T> insertRoot(T data) {
        root = new BinaryTreeNode<>(data, null,null);
        return root;
    }

}
