package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class GenerateBST {
    public static List<TreeNode> generateBST(int n) {
        if (n <= 0) {
            return null;
        }

        List<TreeNode> trees = generateBST(1, n);
        return trees;
    }

    public static List<TreeNode> generateBST(int low, int high) {
        List<TreeNode> ret = new ArrayList<>();
        if (low > high) {
            ret.add(null);
            return ret;
        }

        for (int i = low; i <= high; i++) {
            List<TreeNode> leftList = generateBST(low, i - 1);
            List<TreeNode> rightList = generateBST(i + 1, high);
            for (TreeNode leftRoot : leftList) {
                for (TreeNode rightRoot : rightList) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftRoot;
                    node.right = rightRoot;
                    ret.add(node);
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        generateBST(4);
    }

}
