package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values iteratively.
 *
 * https://oj.leetcode.com/problems/binary-tree-preorder-traversal/
 * Created by abhimaloo on 8/23/14.
 */
public class PreOrderIterative<T extends Comparable<T>>  extends BST<T> {

    public void preOrder(BSTNode<T> root) {
        LinkedList<BSTNode<T>> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            BSTNode node  = stack.pop();
            System.out.println(node.data);
            if(node.right != null) {
                stack.push(node.right);
            }

            if(node.left != null) {
                stack.push(node.left);
            }

        }
    }

    public static void main(String[] args) {
        PreOrderIterative<Integer> tree = new PreOrderIterative<>();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,2);
        tree.insert(tree.root,6);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);

        tree.preOrder(tree.root);
    }
}
