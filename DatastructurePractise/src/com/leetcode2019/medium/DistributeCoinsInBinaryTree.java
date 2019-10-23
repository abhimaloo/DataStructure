package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

/*
https://leetcode.com/problems/distribute-coins-in-binary-tree/
 */
public class DistributeCoinsInBinaryTree {
    /*
    post order traversal
     */
    public static int distributeCoins(TreeNode root, int moves) {
        if (root == null) {
            return moves;
        }
        moves = distributeCoins(root.left, moves);
        moves = distributeCoins(root.right, moves);

        // now we will go up from leaf nodes
        if (root.left != null) {
            if (root.left.val != 1) {
                int diff = root.left.val - 1;
                moves += Math.abs(diff);
                root.val += diff;
                root.left.val = 1;
            }
        }

        if (root.right != null) {
            if (root.right.val != 1) {
                int diff = root.right.val - 1;
                moves += Math.abs(diff);
                root.val += diff;
                root.right.val = 1;
            }
        }
        return moves;
    }
}
