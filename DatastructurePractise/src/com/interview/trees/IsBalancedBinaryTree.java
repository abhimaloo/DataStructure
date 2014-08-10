package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Check whether a BST or Binary tree is Balanced or not
 * Created by abhimaloo on 8/8/14.
 */
public class IsBalancedBinaryTree<T extends Comparable<T>> extends BST<T> {

    public int minHeight(BSTNode<T> root) {
        if(root == null) {
            return 0;
        }
        return 1 + Math.min(minHeight(root.left), minHeight(root.right));
    }

    public int maxHeight(BSTNode<T> root) {
        if(root == null) {
            return 0;
        }
        return 1 + Math.max(maxHeight(root.left), maxHeight(root.right));
    }


    public boolean isBalanced(BSTNode<T> root) {
        return maxHeight(root) - minHeight(root) <= 1;
    }

    public static void main(String[] args) {
        IsBalancedBinaryTree<Integer> tree = new IsBalancedBinaryTree<>();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,14);
        //tree.insert(tree.root,16);

        System.out.println(tree.isBalanced(tree.root));
    }


}
