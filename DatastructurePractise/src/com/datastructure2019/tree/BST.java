package com.datastructure2019.tree;

public class BST {
    TreeNode root = null;

    public TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            TreeNode newNode = new TreeNode(val);
            if (this.root == null) {
                this.root = newNode;
            }
            return newNode;
        }

        if (root.val < val) {
            root.right = insert(root.right, val);
        } else {
            root.left = insert(root.left, val);
        }

        return root;
    }

    public TreeNode find(TreeNode root, int val) {
        if (root == null) {
            return root;
        }

        if (root.val == val) {
            return root;
        } else if (root.val < val) {
            return find(root.right, val);
        } else {
            return find(root.left, val);
        }
    }

    public TreeNode findMin(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        return findMin(root.left);
    }

    public TreeNode remove(TreeNode root, int val) {
        if (root == null) {
            return root;
        }

        if (root.val == val) {
            //check if root is leaf node or parent of single subtree
            if (root.left == null || root.right == null) {
                return root.left == null ? root.right : root.left;
            }

            // if both children exist
            TreeNode minNode = findMin(root.right);
            root = remove(root, minNode.val);
            root.val = minNode.val;
        } else if (root.val < val) {
            root.right = remove(root.right, val);
        } else {
            root.left = remove(root.left, val);
        }

        return root;
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(bst.root, 7);
        bst.insert(bst.root, 1);
        bst.insert(bst.root, 4);
        bst.insert(bst.root, 5);
        bst.insert(bst.root, 9);

        System.out.println(bst.find(bst.root, 4).val);
    }
}