package com.interview.trees.impl;

/**
 * Created by abhishekm787 on 8/14/14.
 */
public class SegmentTree {

    public SegmentTreeNode root;

    /**
     * Its like merge sort
     * keep breaking the trees into left segment and right segment till they become single node
     * keep combining left node and right node to create parent node.
     * @return
     */
    public SegmentTreeNode buildSegmentTree(int[] input, int start, int end) {

        if(start > end) {
            return null;
        } else {
            // for single index node
            if(start == end) {
                return new SegmentTreeNode(input[start], null, null,start,start);
            }
            //find mid point
            int mid = (start + end)/2;
            // recusrsively build left segment and right segment
            SegmentTreeNode leftSegment = buildSegmentTree(input, start, mid);
            SegmentTreeNode rightSegment = buildSegmentTree(input, mid+1, end);
            // merge step  - create a new node which has left Segment and right segment as children and their sum as parent
            // finally build root node
            return new SegmentTreeNode(leftSegment.data + rightSegment.data, leftSegment,rightSegment,leftSegment.fromIndex,rightSegment.toIndex);
        }
    }


    //whatever comes inside the range return it else return the sum of left child and right child
    //Top down approach - check if root's range is under the asking range ..if yes return root's data
    // else find the sum from left subtree and right subtree and add them togather.
    public int getSum(SegmentTreeNode root, int l, int r) {

        //if root lies between the range
        if (root.fromIndex >= l && root.toIndex <= r) {
            return root.data;
        }
        // if root lies beyond the left or right extreme
        if (root.toIndex < l || root.fromIndex > r) {
            return 0;
        }
        // recursively find left sum and right sum and return their addition
        return getSum(root.left, l, r) + getSum(root.right, l, r);
    }


    public static void main(String[] args) {
        int[] input = {1,3,5,7,9,11};
        SegmentTree tree = new SegmentTree();
        tree.root = tree.buildSegmentTree(input,0, input.length-1);
        System.out.println(tree.getSum(tree.root, 0,4));

    }

}
