package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 *
 * Given a binary tree, find its minimum depth.

 The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 https://oj.leetcode.com/problems/minimum-depth-of-binary-tree/
 * Created by abhimaloo on 8/27/14.
 */
public class MinDepth extends BST<Integer> {

    public int minDepth(BSTNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }


    public static void main(String[] args) {
        MinDepth tree = new MinDepth();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);
        System.out.println( tree.minDepth(tree.root));
    }

}
