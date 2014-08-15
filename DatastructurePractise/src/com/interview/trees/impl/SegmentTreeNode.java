package com.interview.trees.impl;

/**
 * Created by abhishekm787 on 8/14/14.
 */
public class SegmentTreeNode {
    public int data;
    public SegmentTreeNode left;
    public SegmentTreeNode right;
    public int fromIndex;
    public int toIndex;

    public SegmentTreeNode(int data, SegmentTreeNode left, SegmentTreeNode right, int fromIndex, int toIndex) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }
}
