package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/all-possible-full-binary-trees/
 */
public class AllPossibleBinaryTrees {
    public static List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N == 0) {
            return res;
        }

        if (N == 1) {
            TreeNode root = new TreeNode(0);
            res.add(root);
            return res;
        }

        for (int i = 0; i <= N; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - i);

            for (TreeNode ln : left) {
                for (TreeNode rn : right) {
                    TreeNode root = new TreeNode(0);
                    root.left = ln;
                    root.right = rn;
                    res.add(root);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        List<TreeNode> roots = allPossibleFBT(3);

    }
}
