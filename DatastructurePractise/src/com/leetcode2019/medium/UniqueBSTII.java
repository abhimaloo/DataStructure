package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/unique-binary-search-trees-ii/
 */
public class UniqueBSTII {
    public static List<TreeNode> uniqueBST(int N) {
        return bst(1, N);
    }

    public static List<TreeNode> bst(int lo, int hi) {
        List<TreeNode> res = new ArrayList<>();
        if (lo >= hi) {
            return res;
        }

        for (int i = lo; i <= hi; i++) {
            List<TreeNode> left = bst(lo, i - 1);
            List<TreeNode> right = bst(i + 1, hi);
            for (TreeNode ln : left) {
                for (TreeNode rn : right) {
                    TreeNode node = new TreeNode(i);
                    node.left = ln;
                    node.right = rn;
                    res.add(node);
                }
            }
        }
        return res;
    }
}
