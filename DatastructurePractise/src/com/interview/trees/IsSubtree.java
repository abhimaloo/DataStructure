package com.interview.trees;

import com.interview.trees.impl.BST;
import com.interview.trees.impl.BSTNode;

/**
 * Given two binary trees, check if the first tree is subtree of the second one.
 * A subtree of a tree T is a tree S consisting of a node in T and all of its descendants in T.
 *
 *
 * http://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary-tree-set-2/
 * Created by abhimaloo on 8/9/14.
 */
public class IsSubtree<T extends Comparable<T>> extends BST<T> {

    /**
     * trick is like naive pattern search - every node in haysack will be compared against the root node of needle
     * if we find the needle root node match .. we try matching the complete tree .. if match do not succeed we go to
     * left and right subtree of haysack
     * @param haysack
     * @param needle
     * @return
     */
    public boolean isSubtree(BSTNode<T> haysack, BSTNode<T> needle) {
        boolean matched = false;

        // unsuccessfull search
        if(haysack == null) {
            return false;
        }

         // look for match between the first node of each of the trees
        if(haysack.data.compareTo(needle.data) == 0) {
            // if match is found .. try matching the complete tree from there
            matched =  isMatch(haysack,needle);
        }

        // if complete tree has not been matched perfectly ..
        // try in left and right subtree
        if(!matched) {
            matched = isSubtree(haysack.left, needle) || isSubtree(haysack.right, needle);
        }

        return matched;
    }


    private boolean isMatch(BSTNode<T> haysack, BSTNode<T> needle) {
        // if both the nodes are null its considered as match
        if(haysack == null && needle == null) {
            return true;
        }
        // if one of them is null and other one is not .. consider it as not match
        if((haysack == null && needle!= null)|| (needle == null && haysack != null)) {
            return false;
        }
        // finally compare the data .. if the data does not match return right from here
        if(haysack.data.compareTo(needle.data) != 0){
            return false;
        }

        // if everything has been succeded till this far .. procedd to left and right subtree of both
        return isMatch(haysack.left, needle.left) && isMatch(haysack.right, needle.right);
    }

    public static void main(String[] args) {

        IsSubtree<Integer> tree = new IsSubtree<>();
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

        IsSubtree<Integer> tree2 = new IsSubtree<>();
        tree2.insert(tree2.root,6);
        tree2.insert(tree2.root,5);
        tree2.insert(tree2.root,7);


        System.out.println(tree.isSubtree(tree.root, tree2.root));
    }

}
