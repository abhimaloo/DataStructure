package com.leetcode;

import com.interview.trees.impl.BSTNode;

/**
 *
 * Given a binary tree, find the maximum path sum.

 The path may start and end at any node in the tree.

 For example:
 Given the below binary tree,

  1
 / \
2   3

Return 6.

 https://oj.leetcode.com/problems/binary-tree-maximum-path-sum/

 * Created by abhimaloo on 8/26/14.
 */
public class BinaryTreeMaxPathSum {

    public static int max_sum = 0;

    /**
     * trick is to do post order traversal - left , right , root
     * for each node which will be the lowermost subtree - you will get the max Sum of left subtree and right subtree
     * if any of the sum left or right is negative, dont add to the uber sum if not add root,data + left sum and + right sum
     * compare it with the max sum so far.. if its big update the maxSum so far
     *
     * finally return the max sum for this level by finding the max of left or right subtree sum and adding current root;s data in it
     *
     * @param root
     * @return
     */
    public static int findMaxPathSum(BSTNode<Integer> root) {
        if(root == null){
            return 0;
        }
        // recursively find left and right max sum
        int leftSum = findMaxPathSum(root.left);

        int rightSum = findMaxPathSum(root.right);

        int sum = root.data;
        // to handle negative sums .. if the sum is positive then only add the left subtree to the sum
        if(leftSum > 0) {
            sum += leftSum;
        }

        // same is true for right subtree
        if(rightSum > 0) {
            sum += rightSum;
        }

        // update the maxSum so far with this subtree or path
        max_sum = Math.max(max_sum, sum);

        // return the max sum for this level
        // it will be either left edge or right edge , which ever is maximum + root's data
        if(Math.max(leftSum, rightSum) > 0) {
            return Math.max(leftSum, rightSum) + root.data;
        } else {
            // if both of the left and right edges are -ve. just return the root's data.
            //which means sum does not includes a pth which goes to the leaf.. ti could be from any node to any node
            return root.data;
        }
    }


}
