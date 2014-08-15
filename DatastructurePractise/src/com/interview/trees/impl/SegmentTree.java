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
    public SegmentTreeNode buildSegmentTreeForSum(int[] input, int start, int end) {

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
            SegmentTreeNode leftSegment = buildSegmentTreeForSum(input, start, mid);
            SegmentTreeNode rightSegment = buildSegmentTreeForSum(input, mid + 1, end);
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


    // same as above this time we need to put minimum in the parent node
    public SegmentTreeNode buildSegmentTreeForMinValue( int[] input, int start, int end) {
        if(start > end) {
            return null;
        }

        if(start == end){
            return new SegmentTreeNode(input[start],null, null, start, end);
        }

        int mid = (start + (end))/2;
        SegmentTreeNode leftSegment = buildSegmentTreeForMinValue(input, start, mid);
        SegmentTreeNode rightSegment = buildSegmentTreeForMinValue(input, mid+1, end);

        return new SegmentTreeNode(Math.min(leftSegment.data, rightSegment.data), leftSegment, rightSegment, leftSegment.fromIndex, rightSegment.toIndex);
    }

    // same as above only catch is to find min among left and right subchild
    public int findMinForRange(SegmentTreeNode root, int start, int end) {
        if(root.fromIndex >= start && root.toIndex <=end ){
            return root.data;
        }
        // return +Infinite if root falls off exteremely .. so that it cannot impact the minimum detection
        if(root.toIndex < start || root.fromIndex > end) {
            return Integer.MAX_VALUE;
        }

        return Math.min(findMinForRange(root.left, start, end), findMinForRange(root.right, start, end));

    }



    public static void main(String[] args) {
        int[] input = {1,3,5,7,9,11};
        SegmentTree sumTree = new SegmentTree();
        sumTree.root = sumTree.buildSegmentTreeForSum(input, 0, input.length - 1);
        System.out.println(sumTree.getSum(sumTree.root, 0, 4));

        SegmentTree rangeMinimumTree = new SegmentTree();
        rangeMinimumTree.root = rangeMinimumTree.buildSegmentTreeForMinValue(input, 0, input.length -1);
        System.out.println(rangeMinimumTree.findMinForRange(rangeMinimumTree.root, 0, 5));

    }

}
