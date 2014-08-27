package com.leetcode;

/**
 *
 * Follow up for problem "Populating Next Right Pointers in Each Node".

 What if the given tree could be any binary tree? Would your previous solution still work?

 Note:

 You may only use constant extra space.
 For example,
 Given the following binary tree,
      1
    /  \
   2    3
  / \    \
 4   5    7
 After calling your function, the tree should look like:
        1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL


 * Created by abhimaloo on 8/27/14.
 */
public class PopulateNextRightPointers2 {

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
     * if root has right child .. assign right child's next pointer to root's next child pointer's left child or right child which ever is present in the order (trick .. just imagine the link from 5 to 7)
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
            // root's right child's next pointer
            TreeNode nextChild = null;
            // if root has a next pointer
            if(root.next != null) {
                // check if next pointer has a left child
                if(root.next.left != null) {
                    // if yes assign left child of next pointer int nextChild
                    nextChild = root.next.left;
                } else {
                    // otherwise assign right child of next pointer even if its null to the next child
                    nextChild = root.next.right;
                }
            }

            root.right.next = nextChild;
        }
        // recurse on left subtree
        populate(root.left);
        //recurse on right subtree
        populate(root.right);

    }


    public static void main(String[] args) {
        TreeNode root  = new TreeNode(1, new TreeNode(2,new TreeNode(4,null,null,null),new TreeNode(5,null,null,null), null),  new TreeNode(3,null,new TreeNode(7,null,null, null), null), null);
        populate(root);

    }
}
