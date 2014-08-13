package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Given a Binary Search Tree (BST), convert it to a Doubly Linked List(DLL).
 * The left and right pointers in nodes are to be used as previous and next pointers respectively in converted DLL.
 * The order of nodes in DLL must be same as Inorder of the given Binary Tree.
 * The first node of Inorder traversal (left most node in BT) must be head node of the DLL.
 *
 * http://www.geeksforgeeks.org/convert-a-given-binary-tree-to-doubly-linked-list-set-2/
 * Created by abhimaloo on 8/12/14.
 */
public class BST2DoublyLinkedList<T extends Comparable<T>> extends BST<T> {

    public static BSTNode head = null;

    /**
     * Simple steps -
     * a) Keep going to left and recursively convert left subtree to DLL
     * b) find the inorder predecessor in left subtree ( right most node in left subtree)
     * c) make this predecessor node as prev of root and make root next of predecessor
     *
     * d) recursively convert right subtree into DLL..
     * e) find inorder successor in right subtree (left most node in right subtree)
     * f) make successor node next of root
     * g) make root previous of successor
     *
     * @param root
     * @return
     */
    public BSTNode<T> convertToDLL(BSTNode<T> root) {
        if(root == null) {
            return root;
        }
        // this is to get hold of parent with not null left child ..
        if(root.left != null) {
            // recursively convert left subtree to DLL
            BSTNode<T> leftNode = convertToDLL(root.left);
            // assign head of doubly linked list ..
            if(head == null) {
                head = leftNode;
            }

            // find inorder predecessor
            for(;leftNode.right != null; leftNode = leftNode.right);

            // make root next of the predecessor
            leftNode.right  = root;
            // make predecessor prev of root
            root.left = leftNode;
        }
        // similarly for right subtree .. above condition gives you root which has notNull left child
        if(root.right != null) {
            // recursively convert right subtree to DLL
            BSTNode<T> rightNode = convertToDLL(root.right);
            // find inorder successor
            for(;rightNode.left != null; rightNode  = rightNode.left) ;
            // make root previous element of the successor
            rightNode.left = root;
            // make successot next node of the root
            root.right = rightNode;
        }

        return root;
    }



    public static void main(String[] args) {
        BST2DoublyLinkedList<Integer> tree = new BST2DoublyLinkedList<>();
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

        tree.convertToDLL(tree.root);

    }


}
