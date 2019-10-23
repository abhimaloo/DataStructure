package com.leetcode2019.easy;

import com.datastructure2019.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/binary-tree-paths/
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList();
        paths(root, paths, "");
        return paths;
    }

    public void paths(TreeNode root, List<String> list, String path) {
        if (root == null) {
            return;
        }

        if (path.length() != 0) {
            path += "->";
        }
        path += root.val;

        if (root.left == null && root.right == null) {
            list.add(path);
            return;
        }
        paths(root.left, list, path);
        paths(root.right, list, path);
    }
}
