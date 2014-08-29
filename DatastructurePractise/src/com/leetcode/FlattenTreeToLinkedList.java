package com.leetcode;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 *
 * Given a binary tree, flatten it to a linked list in-place.

 For example,
 Given

     1
    / \
   2   5
 / \    \
 3  4    6
 The flattened tree should look like:
 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6

 https://oj.leetcode.com/problems/flatten-binary-tree-to-linked-list/

 * Created by abhimaloo on 8/27/14.
 */
public class FlattenTreeToLinkedList extends BST<Integer> {

    /**
     * Trick is make all the left pointers as right pointers and put right pointer to left subtree's right most node's right child
     * @param root
     */
    public void flatten(BSTNode<Integer> root) {

        while(root != null) {
            // if left child is present
             if(root.left != null) {
                 // find the right most child of left subtree
                 BSTNode<Integer> pre = root.left;
                 while(pre.right!= null) {
                     pre = pre.right;
                 }
                 // pre node.. set the right child at the right node
                 pre.right = root.right;
                 // transfer left child of root to right child
                 root.right = root.left;
                 //mark left child as null
                 root.left = null;

             }
            // go to the next nod ..since right node represents next node
            root = root.right;
        }
    }

    public static void main(String[] args) {
        FlattenTreeToLinkedList tree = new FlattenTreeToLinkedList();
        tree.insert(tree.root,8);
        tree.insert(tree.root,4);
        tree.insert(tree.root,12);
        tree.insert(tree.root,2);
        tree.insert(tree.root,6);
        tree.insert(tree.root,10);
        tree.insert(tree.root,14);

        tree.flatten(tree.root);

    }


}
