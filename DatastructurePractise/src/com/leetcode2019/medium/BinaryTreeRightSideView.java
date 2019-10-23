package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        nodeQueue.add(root);
        levelQueue.add(0);
        while (!nodeQueue.isEmpty()) {
            TreeNode n = nodeQueue.remove();
            int level = levelQueue.remove();
            if (level == res.size()) {
                res.add(n.val);
            }

            if (n.right != null) {
                nodeQueue.add(n.right);
                levelQueue.add(level + 1);
            }

            if (n.left != null) {
                nodeQueue.add(n.left);
                levelQueue.add(level + 1);
            }
        }
        return res;
    }
}
