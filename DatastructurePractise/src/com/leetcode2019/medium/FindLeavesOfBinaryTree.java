package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/find-leaves-of-binary-tree/
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.



Example:

Input: [1,2,3,4,5]

          1
         / \
        2   3
       / \
      4   5

Output: [[4,5,3],[2],[1]]


Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         /
        2


2. Now removing the leaf [2] would result in this tree:

          1


3. Now removing the leaf [1] would result in the empty tree:

          []
 */
public class FindLeavesOfBinaryTree {
    /*
    Trick is calculate height of each node from leaf.
    height is number of edges any node to leadNodes.
     */
    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        if (root == null) return result;
        height(root, result);
        root = null;
        return result;
    }

    public static int height(TreeNode root, List<List<Integer>> result) {
        if (root == null) return -1;

        int level = 1 + Math.max(height(root.left, result), height(root.right, result));

        if (result.size() == level) {
            result.add(level, new ArrayList<>());
        }

        result.get(level).add(root.val);
        root.left = root.right = null;
        return level;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree(new Integer[]{1, 2, 3, 4, 5, null, null, null, null, null, null});
        findLeaves(root);
    }

}
