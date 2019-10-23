package com.leetcode2019.medium;

/*
https://leetcode.com/problems/inorder-successor-in-bst-ii/
 */
public class InorderSuccessorBSTII {
    /*
    Trick is to find -
    the leftmost child of right subtree if exist
    or
    either x is the left child; hence return parent
    or x is the right child hence keep looking at the parent which is bigger than the x value

     */
    public static Node inorderSuccessor(Node x) {
        Node cursor = null;
        if (x == null) {
            return cursor;
        }

        if (x.right != null) {
            cursor = x.right;
            while (cursor != null) {
                cursor = cursor.left;
            }
            return cursor;
        } else {
            cursor = x.parent;
            while (cursor != null && cursor.val < x.val) {
                cursor = cursor.parent;
            }
            return cursor;
        }
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
