package com.interview.trees.impl;

/**
 * Created by abhishekm787 on 8/14/14.
 */
public class SegmentTree {

    public SegmentTreeNode root;

    public SegmentTreeNode buildSegmentTree(int[] input, int start, int end) {

        if(start > end) {
            return null;
        } else {

            if(start == end) {
                return new SegmentTreeNode(input[start], null, null,start,start);
            }

            int mid = (start + end)/2;
            SegmentTreeNode leftSegment = buildSegmentTree(input, start, mid);
            SegmentTreeNode rightSegment = buildSegmentTree(input, mid+1, end);
            // merge step  - create a new node which has left Segment and right segment as children and their sum as parent
            return new SegmentTreeNode(leftSegment.data + rightSegment.data, leftSegment,rightSegment,leftSegment.fromIndex,rightSegment.toIndex);
        }
    }

    public static void main(String[] args) {
        int[] input = {1,3,5,7,9,11};
        SegmentTree tree = new SegmentTree();
        tree.root = tree.buildSegmentTree(input,0, input.length-1);

    }

}
