package com.crackCode.tree;


import com.interview.trees.impl.BSTNode;

/**
 * Created by abhimaloo on 9/14/14.
 */
public class IsBalanced {

    public static int minHeight(BSTNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        return 1 + Math.min(minHeight(root.left), minHeight(root.right));
    }

    public static int maxHeight(BSTNode<Integer> root) {
        if(root == null) {
            return 0;
        }

        return 1 + Math.max(maxHeight(root.left), maxHeight(root.right));
    }

    public boolean isBalanced(BSTNode<Integer> root) {
        return maxHeight(root) <= minHeight(root) +1;
    }




}
