package com.leetcode2022.blind75.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        nodeQueue.offer(root);
        levelQueue.offer(1);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int level = levelQueue.poll();
            System.out.println(level + ": " + node.val);

            if (node.left != null) {
                nodeQueue.offer(root.left);
                levelQueue.offer(level + 1);
            }
            if (node.right != null) {
                nodeQueue.offer(root.right);
                levelQueue.offer(level + 1);
            }
        }
    }

}
