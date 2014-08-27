package com.leetcode;

/**
 *
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

 Initially, all next pointers are set to NULL.

 Note:

 You may only use constant extra space.
 You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 For example,
 Given the following perfect binary tree,
       1
     /  \
    2    3
   / \  / \
  4  5  6  7
 After calling your function, the tree should look like:
        1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL


 https://oj.leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * Created by abhimaloo on 8/27/14.
 */
public class PopulateNextRightPointers {
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode  right;
        TreeNode  next;

        TreeNode(int data, TreeNode left, TreeNode right, TreeNode next) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    /**
     * trick is to preOrder traversal.
     * if root has left child .. assign left child's next pointer to root's right child
     * if root has right child .. assign right child's next pointer to root's next child pointer's left child (trick .. just imagine the link from 5 to 6)
     * recurse on left subtree and recurse on right subtree
     * @param root
     */
    public static void populate(TreeNode root) {
        if(root == null) {
            return;
        }
        // root has left child
        if(root.left != null) {
            // root's left child's next pointer would be root's right child
            root.left.next = root.right;
        }

        // if root has right  child
        if(root.right != null) {
            // root's right child's next pointer would be root's next's left child if root has a next element set other wise null
            root.right.next = root.next != null ? root.next.left : null;
        }
        // recurse on left subtree
        populate(root.left);
        //recurse on right subtree
        populate(root.right);

    }


    public static void main(String[] args) {
        TreeNode root  = new TreeNode(1, new TreeNode(2,new TreeNode(4,null,null,null),new TreeNode(5,null,null,null), null),  new TreeNode(3,new TreeNode(6,null,null, null),new TreeNode(7,null,null, null), null), null);
        populate(root);

    }

}
