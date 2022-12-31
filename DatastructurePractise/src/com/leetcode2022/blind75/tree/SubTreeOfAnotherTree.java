package com.leetcode2022.blind75.tree;

import java.util.ArrayList;
import java.util.List;

/*
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.



Example 1:


Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true
Example 2:


Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false


Constraints:

The number of nodes in the root tree is in the range [1, 2000].
The number of nodes in the subRoot tree is in the range [1, 1000].
-104 <= root.val <= 104
-104 <= subRoot.val <= 104
 */
public class SubTreeOfAnotherTree {
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        List<TreeNode> nodeList = new ArrayList<>();
        find(s, t, nodeList);
        for (TreeNode node : nodeList) {
            if (checkIfSame(node, t)) {
                return true;
            }
        }

        return false;
    }

    private static void find(TreeNode s, TreeNode t, List<TreeNode> nodeList) {
        if (s == null) {
            return;
        }

        if (s.val == t.val) {
            nodeList.add(s);
        }

        find(s.left, t, nodeList);
        find(s.right, t, nodeList);
    }

    private static boolean checkIfSame(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            if (s == null && t == null) {
                return true;
            } else {
                return false;
            }
        }

        return s.val == t.val && checkIfSame(s.left, t.left) && checkIfSame(s.right, t.right);
    }

    public static void main(String[] args) {
        TreeNode s = new TreeNode(3);
        TreeNode s4 = new TreeNode(4);
        TreeNode s5 = new TreeNode(5);
        TreeNode s1 = new TreeNode(1);
        TreeNode s2 = new TreeNode(2);
        TreeNode s0 = new TreeNode(0);
        s.left = s4;
        s.right = s5;
        s4.left = s1;
        s4.right = s2;
        s2.left = s0;

        TreeNode t = new TreeNode(4);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        t.left = t1;
        t.right = t2;

        System.out.println(isSubtree(s, t));
    }


}
