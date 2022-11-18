package com.leetcode2022.datastructures;

import java.util.LinkedList;

public class BST {
    public TreeNode root = null;

    public static class TreeNode {
        public int data;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // insert into BT
    public TreeNode insert(TreeNode root, int data) {
        if (root == null) {
            TreeNode newNode = new TreeNode(data, null, null);
            if (this.root == null) {
                this.root = newNode;
            }
            return newNode;
        }

        if (root.data < data) {
            root.right = insert(root.right, data);
        } else {
            root.left = insert(root.left, data);
        }

        return root;
    }

    // find a value in BST
    public TreeNode find(TreeNode root, int data) {
        if (root == null || root.data == data) {
            return root;
        }

        if (root.data < data) {
            return find(root.right, data);
        } else {
            return find(root.left, data);
        }
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        System.out.println(" " + root.data + " ");
        inOrder(root.right);
    }

    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(" " + root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(" " + root.data + " ");
        }
    }

    public int maxDepth(TreeNode root, int depth) {
        if (root == null) return 0;

        depth = 1 + Math.max(maxDepth(root.left, depth + 1), maxDepth(root.right, depth + 1));
        return depth;
    }

    public boolean sameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        if (p.data == q.data) {
            return sameTree(p.left, q.left) && sameTree(p.right, q.right);
        }

        return false;

    }

    public void levelOrder(TreeNode root) {
        if (root != null) {
            LinkedList<TreeNode> nodeQueue = new LinkedList<>();
            LinkedList<Integer> levelQueue = new LinkedList<>();
            int level = 0;
            nodeQueue.offer(root);
            levelQueue.offer(level);
            while (nodeQueue.size() > 0) {
                TreeNode visited = nodeQueue.poll();
                level = levelQueue.poll();
                System.out.println("Level: " + level + " Data: " + visited.data);
                level++;
                if (visited.left != null) {
                    nodeQueue.offer(visited.left);
                    levelQueue.offer(level);
                }
                if (visited.right != null) {
                    nodeQueue.offer(visited.right);
                    levelQueue.offer(level);
                }
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(bst.root, 7);
        bst.insert(bst.root, 5);
        bst.insert(bst.root, 9);
        bst.insert(bst.root, 8);
        bst.insert(bst.root, 6);
        bst.insert(bst.root, 1);

        //bst.preOrder(bst.root);
        System.out.println(bst.maxDepth(bst.root, 0));
    }

}

