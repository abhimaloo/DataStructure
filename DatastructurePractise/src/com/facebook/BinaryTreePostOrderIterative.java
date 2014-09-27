package com.facebook;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

import java.util.LinkedList;

/**
 * Created by abhimaloo on 9/26/14.
 */
public class BinaryTreePostOrderIterative {

    // trick is to use reverse pre order and keep putting the data to another stack .. later print the stack
    public static void postOrder(BSTNode<Integer> root) {
        if(root == null) {
            return;
        }
        LinkedList<BSTNode<Integer>> revPreOrderStack = new LinkedList<>();
        LinkedList<BSTNode<Integer>> postOrderStack = new LinkedList<>();

        revPreOrderStack.push(root);
        while(!revPreOrderStack.isEmpty()) {
            BSTNode<Integer> node = revPreOrderStack.pop();
            postOrderStack.push(node);
            if(node.left != null) {
                revPreOrderStack.push(node.left);
            }

            if(node.right!= null) {
                revPreOrderStack.push(node.right);
            }

        }

        while(!postOrderStack.isEmpty()) {
            System.out.println(postOrderStack.pop().data);
        }

    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,2);
        tree.insert(tree.root,6);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);

        postOrder(tree.root);
    }

}
