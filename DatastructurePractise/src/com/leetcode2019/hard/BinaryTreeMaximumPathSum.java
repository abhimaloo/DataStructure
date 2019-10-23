package com.leetcode2019.hard;

import com.datastructure2019.tree.TreeNode;

/*
https://leetcode.com/problems/binary-tree-maximum-path-sum/
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 */
public class BinaryTreeMaximumPathSum {
    public static int maxPathSum(TreeNode root) {
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;
        maxpathSum(root, maxSum);
        return maxSum[0];
    }

    /*
    Solution -
    For every Node - we need to make a decision whether this Node becomes the part of max-path coming from its parent.
    Or It has its own maxpath.
    For Case 1 (part of sub-path) -  find maximum of following 3 cases
        3 cases - a) root node is the endpoint of the path.
                  b) root node + left child is part of subpath
                  c) root node + right child is part of subpath

    For Max path -
    Either the maximum path goes through this root or not hence find the max of both the cases
    maxSum = Math.max(Case 1, sum root's data + left subpath + right subpath)
    update the maxSum for everyNode if its higher than before seen
     */
    public static int maxpathSum(TreeNode root, int[] maxSum) {
        if (root == null) return 0;
        // Case 1
        int maxLeft = maxpathSum(root.left, maxSum);
        int maxRight = maxpathSum(root.right, maxSum);

        int maxPathValue = Math.max(Math.max(maxLeft, maxRight) + root.val, root.val);
        // case 2
        int rootPathValue = Math.max(maxPathValue, root.val + maxLeft + maxRight);
        maxSum[0] = Math.max(maxSum[0], rootPathValue);

        return maxPathValue;
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(-10)
                .setLeft(
                        new TreeNode(9)
                ).setRight(
                        new TreeNode(20).setLeft(new TreeNode(15)).setRight(new TreeNode(7))
                );
        System.out.println(maxPathSum(tree));
    }
}
