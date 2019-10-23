package com.datastructure2019.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode setLeft(TreeNode left) {
        this.left = left;
        return this;
    }

    public TreeNode setRight(TreeNode right) {
        this.right = right;
        return this;
    }

    public static TreeNode buildTree(Integer[] levelOrder) {
        List<Integer> list = new ArrayList<>();
        for (Integer a : levelOrder) {
            list.add(a);
        }
        list.add(0, null);
        return buildTree(list, 1);
    }

    public static TreeNode buildTree(List<Integer> levelOrder, int index) {
        if (index >= levelOrder.size()) return null;
        Integer val = levelOrder.get(index);
        if (val != null) {
            TreeNode root = new TreeNode(levelOrder.get(index));
            root.left = buildTree(levelOrder, 2 * index);
            root.right = buildTree(levelOrder, 2 * index + 1);
            return root;
        }
        return null;
    }

}
