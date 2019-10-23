package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/boundary-of-binary-tree/
Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)

Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1

Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 */
public class BoundaryOfBinaryTree {
    public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        result.add(root.val);
        // get the left boundary
        leftPart(root.left, result);
        // we need to get bottom boundary
        leafNodes(root, root, result);
        // get right part
        rightPart(root.right, result);

        return result;
    }

    public static void leftPart(TreeNode root, List<Integer> leftBoundary) {
        if (root == null || (root.left == null && root.right == null)) return;

        leftBoundary.add(root.val);
        if (root.left != null) {
            leftPart(root.left, leftBoundary);
        } else {
            leftPart(root.right, leftBoundary);
        }
    }

    public static void leafNodes(TreeNode root, TreeNode absRoot, List<Integer> bottomBoundary) {
        if (root == null) return;

        leafNodes(root.left, absRoot, bottomBoundary);
        leafNodes(root.right, absRoot, bottomBoundary);

        if (root.left == null && root.right == null && root != absRoot) {
            // its a leaf node
            bottomBoundary.add(root.val);
        }
    }

    public static void rightPart(TreeNode root, List<Integer> rightBoundary) {
        if (root == null || (root.left == null && root.right == null)) return;

        if (root.right != null) {
            rightPart(root.right, rightBoundary);
        } else {
            rightPart(root.left, rightBoundary);
        }

        rightBoundary.add(root.val);
    }

    public static void main(String[] args) {
        boundaryOfBinaryTree(TreeNode.buildTree(new Integer[]{1}));
    }

}
