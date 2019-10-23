package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return null;
        List<List<Integer>> res = new ArrayList();
        Queue<TreeNode> nodeQueue = new LinkedList();
        Queue<Integer> levelQueue = new LinkedList();
        nodeQueue.offer(root);
        levelQueue.offer(0);
        while (!nodeQueue.isEmpty()) {
            TreeNode item = nodeQueue.remove();
            int level = levelQueue.remove();
            if (res.size() > level) {
                res.get(level).add(item.val);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(item.val);
                res.add(level, list);
            }


            if (item.left != null) {
                nodeQueue.offer(item.left);
                levelQueue.offer(level + 1);
            }

            if (item.right != null) {
                nodeQueue.offer(item.right);
                levelQueue.offer(level + 1);
            }
        }
        return res;
    }

    public static void levelOrder(TreeNode root, List<List<Integer>> result, int level) {
        if (result.size() == level) {
            result.add(level, new ArrayList<>());
        }

        result.get(level).add(root.val);
        if (root.left != null) {
            levelOrder(root.left, result, level + 1);
        }
        if (root.right != null) {
            levelOrder(root.right, result, level + 1);
        }
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(7)
                .setLeft(
                        new TreeNode(3)
                ).setRight(
                        new TreeNode(15).setLeft(new TreeNode(9)).setRight(new TreeNode(20))
                );
        levelOrder(tree);
    }
}
