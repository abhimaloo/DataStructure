package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/** Inorder successor and Predecessor of a node of BST
 *
 * Created by abhimaloo on 8/9/14.
 */
public class InorderSuccessor<T extends Comparable<T>> extends BST<T> {

    /**
     * trick is to find the node while maintaining an account of the node whenever you go to left subtree ..
     * finally when you reach the node .. there are only two cases -
     * a) Node has a right subTree - return the right node ..(since right children is inorder successor)
     * b) Node does not have a right subtree - return the node from where you took left.
     *
     */
    public BSTNode<T> inOrderSuccessor(BSTNode<T> root, BSTNode<T> target, BSTNode<T> leftTaker) {
        if( root == null) {
            return root ;
        }
        // check if you reached the node or not
        if(root.data.compareTo(target.data) == 0) {
            // if the node has right subtree
            if(root.right != null) {
                // find min in right subtree
                root = root.right;
                while(root.left != null){
                    root = root.left;
                }
                return root;
            }
        } else if(root.data.compareTo(target.data) > 0) {
            // while going left record the current root from where you took left turn
            return inOrderSuccessor(root.left, target, root);
        } else {
            // while going right you dont have to record that
            return inOrderSuccessor(root.right, target, leftTaker);
        }

        return leftTaker;
    }

    /**
     * trick is to find the node while maintaining an account of the node whenever you go to right subtree ..
     * finally when you reach the node .. there are only two cases -
     * a) Node has a left subTree - return the left node ..(since left children is inorder predecessor)
     * b) Node does not have a left subtree - return the node from where you took right.
     *
     */
    public BSTNode<T> inOrderPredecessor(BSTNode<T> root, BSTNode<T> target, BSTNode<T> rightTaker) {
        if( root == null) {
            return root ;
        }
        // check if you reached the node or not
        if(root.data.compareTo(target.data) == 0) {
            // if the node has left subtree
            if(root.left != null) {
                //find max in left subtree
                root  = root.left;
                while(root.right != null) {
                    root = root.right;
                }
                return root;
            }
        } else if(root.data.compareTo(target.data) > 0) {
            // while going left you dont care to record right taker
            return inOrderPredecessor(root.left, target, rightTaker);
        } else {
            // while going right you record the point from where you took right
            return inOrderPredecessor(root.right, target, root);
        }

        return rightTaker;
    }


    public static void main(String[] args) {
        InorderSuccessor<Integer> tree = new InorderSuccessor<>();

        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,2);
        tree.insert(tree.root,6);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);
        tree.insert(tree.root,1);
        tree.insert(tree.root,5);
        tree.insert(tree.root,7);

        //System.out.println(tree.inOrderSuccessor(tree.root, tree.find(tree.root, 8), null).data);
        System.out.println(tree.inOrderPredecessor(tree.root, tree.find(tree.root, 8), null).data);

    }


}
