package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Created by abhimaloo on 8/27/14.
 */
public class BalancedBinaryTree extends BST<Integer> {
    public int minDepth(BSTNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    public int maxDepth(BSTNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public boolean isBalanced(BSTNode<Integer> root) {
        return maxDepth(root) - minDepth(root) <=1 ;
    }

    public static void main(String[] args) {
        BalancedBinaryTree tree = new BalancedBinaryTree();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);
        System.out.println( tree.isBalanced(tree.root));

    }
}
