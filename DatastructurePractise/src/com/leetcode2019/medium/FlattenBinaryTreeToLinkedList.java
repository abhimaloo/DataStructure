package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

/*
https://leetcode.com/problems/flatten-binary-tree-to-linked-list/submissions/
 */
public class FlattenBinaryTreeToLinkedList {
    public static TreeNode prev = null;

    /*
    Trick is to go post order;
    visit the right child first save the value in previous node
    visit left child
    then while visiting root
     */
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        // post order
        flatten(root.right);
        flatten(root.left);
        // build the chain by putting previous nodes in root.right
        // nullify the left child because by now left child would already been chained.
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
