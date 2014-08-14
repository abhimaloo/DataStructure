package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Write a function to detect if two trees are isomorphic.
 * Two trees are called isomorphic if one of them can be obtained from other by a series of flips, i.e. by swapping left and right children of a number of nodes.
 * Any number of nodes at any level can have their children swapped. Two empty trees are isomorphic.
 * Created by abhishekm787 on 8/14/14.
 */
public class IsomorphicTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Trick is to traverse both the tree one node at a time ..
     * if both the nodes are null return true .
     * if either of them are null return false (not isomorphic)
     * check the data of both the roots, it should match ..
     * if it matches recursively calculate the two cases -
     * a) root1 -> left == root2-> left  and root1-> right == root2->right (even or no flips)
     * b) root1->right == root2->left and root1 -> left == root2 ->right(single or odd flips)
     * @param root1
     * @param root2
     * @return
     */
    public boolean isIsomorphic(BSTNode<T> root1, BSTNode<T> root2) {
        // empty tree is isomorphoic
        if(root1 == null && root2 == null) {
            return true;
        } else if(root1 == null || root2 == null) {  // one of them cant be null
            return false;
        } else {
            // both the root's data should match
            if(!(root1.data.compareTo(root2.data) == 0)) {
                return false;
            }

            // recursively calculate these two cases
            // two cases  - a) both subtree rooted are not flipped
            //              b) left child and right child match and vice versa
            return (isIsomorphic(root1.left,root2.left) && isIsomorphic(root1.right,root2.right))
                    || (isIsomorphic(root1.left,root2.right) && isIsomorphic(root1.right,root2.left));

        }

    }





}
