package com.datastructure2019.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Traversals {
    public static void preorder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(" " + root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(" " + root.val + " ");
        inorder(root.right);
    }

    public static void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(" " + root.val + " ");
    }

    public static void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
            levelQueue.offer(1);
        }


        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int level = levelQueue.poll();
            System.out.println(level + " " + node.val);
            if (node.left != null) {
                queue.add(node.left);
                levelQueue.add(level + 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                levelQueue.add(level + 1);
            }
        }

    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(5)
                .setLeft(
                        new TreeNode(3).setLeft(new TreeNode(1)).setRight(new TreeNode(4))
                ).setRight(
                        new TreeNode(8).setLeft(new TreeNode(7)).setRight(new TreeNode(9))
                );
        levelOrder(tree);
    }
}
