package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Convert a BST to a sorted circular doubly-linked list in-place.
 * Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
 *
 * http://leetcode.com/2010/11/convert-binary-search-tree-bst-to.html
 * Created by abhimaloo on 8/12/14.
 */
public class BSTToCIrcularDoublyLinkedList<T extends Comparable<T>> extends BST<T> {

    /**
     * trick is to post order traversal.
     *
     * @param root
     * @return
     */
    public BSTNode<T> convertToCircularDLL(BSTNode<T> root) {
        // base case
        if(root == null) {
            return root;
        }

        // convert the left subtree to circular DLL
        BSTNode<T> leftList = convertToCircularDLL(root.left);
        // convert the right subtree to circular Dll
        BSTNode<T> rightList = convertToCircularDLL(root.right);


        // make root as a single node circular linked list
        // join both the heads to the root itself
        root.left = root;
        root.right = root;


        // NOw we will append CLL formed by left subtree, root and right subtree

        // append left list with root
        leftList = append(leftList, root);
        // append the right list with the result of (leftList+root)
        leftList = append(leftList, rightList);
        // return the merged List
        return leftList;
    }

    private BSTNode<T> append(BSTNode<T> a, BSTNode<T> b) {

        //if either of the lists are null return the one which is not null
        if(a == null && b != null) {
            return b;
        }

        if(b == null && a != null) {
            return a;
        }

        // find the last node of both the lists..
        // since a and b are circular a's Last node can be found by a.prev similarly for B's last
        // now we have first node of both the lists and last node of both the lists
        BSTNode<T> aLast = a.left;
        BSTNode<T> bLast = b.left;

        //make bidirectional connections
        //Join A's last node with B
        join(aLast,b);
        // join B's Last node with A
        join(bLast,a);

        // return A , since it is the leftmost node
        return a;
    }

    /**
     * simply join both the nodes bidirectionally
     * @param a
     * @param b
     */
    private void join(BSTNode<T> a, BSTNode<T> b) {
        // put b in A's next
        a.right = b;
        // put A in B's previous
        b.left = a;
    }

    public static void main(String[] args) {
        BSTToCIrcularDoublyLinkedList<Integer> tree = new BSTToCIrcularDoublyLinkedList<>();

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

        BSTNode<Integer> head = tree.convertToCircularDLL(tree.root);

    }
}
