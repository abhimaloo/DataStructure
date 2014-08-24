package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values iteratively.
 *
 * https://oj.leetcode.com/problems/binary-tree-inorder-traversal/
 * Created by abhimaloo on 8/23/14.
 */
public class InOrderIterative<T extends Comparable<T>>  extends BST<T> {

    public void inOrder(BSTNode<T> root) {
        LinkedList<BSTNode<T>> stack = new LinkedList<>();
        boolean done = false;
        BSTNode<T> current = root;
        while(!done) {
            if(current!= null) {
                stack.push(current);
                current = current.left;
            } else {
                if(!stack.isEmpty()) {
                    current = stack.pop();
                    System.out.println(current.data);
                    current = current.right;

                } else {
                    done = true;
                }

            }

        }

    }

    public static void main(String[] args) {
        InOrderIterative<Integer> tree = new InOrderIterative<>();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,2);
        tree.insert(tree.root,6);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);

        tree.inOrder(tree.root);
    }

}
