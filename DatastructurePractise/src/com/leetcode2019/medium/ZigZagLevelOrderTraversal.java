package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class ZigZagLevelOrderTraversal {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        zigzag(root, 0, res);
        return res;
    }

    public static void zigzag(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return;

        if (res.size() == level) {
            res.add(new ArrayList<>());
        }

        // here is the trick. If levels are Even apppend the value to the array list
        // otherwise add it at front
        if (level % 2 == 0) {
            res.get(level).add(root.val);
        } else {
            res.get(level).add(0, root.val);
        }
        zigzag(root.left, level + 1, res);
        zigzag(root.right, level + 1, res);
    }

}
