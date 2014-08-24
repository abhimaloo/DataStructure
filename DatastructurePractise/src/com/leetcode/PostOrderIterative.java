package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values iteratively.
 *
 * https://oj.leetcode.com/problems/binary-tree-postorder-traversal/
 * Created by abhimaloo on 8/23/14.
 */
public class PostOrderIterative<T extends Comparable<T>>  extends BST<T>{

    //maintain two stacks .. one for reverse postOrder and other for reversing it again
    public void postOrder(BSTNode<T> root) {

        LinkedList<BSTNode<T>> rpo = new LinkedList<>();
        LinkedList<BSTNode<T>> nodeStack = new LinkedList<>();

        nodeStack.push(root);
        while(!nodeStack.isEmpty()) {
            BSTNode<T> node = nodeStack.pop();
            rpo.push(node);

            if(node.left!= null) {
               nodeStack.push(node.left);
            }

            if(node.right != null) {
                nodeStack.push(node.right);
            }
        }

        while(!rpo.isEmpty()) {
            System.out.println(rpo.pop().data);
        }


    }

    public static void main(String[] args) {
        PostOrderIterative<Integer> tree = new PostOrderIterative<>();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,2);
        tree.insert(tree.root,6);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);

        tree.postOrder(tree.root);
    }
}
