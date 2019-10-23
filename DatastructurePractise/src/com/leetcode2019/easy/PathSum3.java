package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

/*
https://leetcode.com/problems/path-sum-iii/
 */
public class PathSum3 {
    /*
    Idea is to think like that - either a root will be present in the sum path or not.
    if it is present -> iteratively find sum from its value
    if not -> recurse or left child and right child
     */
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum); // for left subchild and right subchild we are recursing on the same method
        // but for root we are calling the helper method dfs
    }

    public static int dfs(TreeNode root, int sum) {
        if (root == null) return 0;
        return (root.val == sum ? 1 : 0) + dfs(root.left, sum - root.val) + dfs(root.right, sum - root.val);
    }

}
