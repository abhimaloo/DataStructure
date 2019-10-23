package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

 */
public class VerticalOrderTraversalOfBinaryTree {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, PriorityQueue<VPoint>> scoreMap = new TreeMap<>();
        preorder(root, 0, 0, scoreMap);
        for (PriorityQueue<VPoint> queue : scoreMap.values()) {
            List<Integer> list = new ArrayList<>();
            while (!queue.isEmpty()) {
                list.add(queue.poll().val);
            }
            result.add(list);
        }

        return result;
    }

    public void preorder(TreeNode root, int x, int y, TreeMap<Integer, PriorityQueue<VPoint>> map) {
        if (root == null) return;
        if (!map.containsKey(x)) {
            map.put(
                    x,
                    new PriorityQueue<>(
                            (p1, p2) -> {
                                if (Integer.compare(Math.abs(p1.y), Math.abs(p2.y)) == 0) {  // compare level closer to root
                                    return Integer.compare(p1.val, p2.val); // compare value
                                } else {
                                    return Integer.compare(Math.abs(p1.y), Math.abs(p2.y));
                                }
                            }));
        }
        map.get(x).offer(new VPoint(y, root.val));
        preorder(root.left, x - 1, y - 1, map);
        preorder(root.right, x + 1, y - 1, map);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        VerticalOrderTraversalOfBinaryTree obj = new VerticalOrderTraversalOfBinaryTree();
        obj.verticalTraversal(root);
    }
}

class VPoint {
    int y;
    int val;

    public VPoint(int y, int val) {
        this.y = y;
        this.val = val;
    }
}

