package com.leetcode2022.blind75.tree;

/*
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 3
Example 2:

Input: root = [1,null,2]
Output: 2


Constraints:

The number of nodes in the tree is in the range [0, 104].
-100 <= Node.val <= 100
 */
public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        return findDepth(root, 0);
    }

    public int findDepth(TreeNode root, int depth) {
        if (root == null) return 0;
        depth = 1 + Math.max(findDepth(root.left, 1 + depth), findDepth(root.right, 1 + depth));
        return depth;
    }

}
