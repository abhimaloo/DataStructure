package com.leetcode2022.top_facebook;

import java.util.*;

/*
Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.



Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Example 2:


Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]
Example 3:


Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]


Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */
public class BinaryTreeVerticalOrderTraversal {
    /*
    Intuition is similar to level order traversal - BFS
    instead of level use score which would decrease by 1 if you go left and increase by 1 if you go right
     */
    public static List<List<Integer>> verticalOrderBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        TreeMap<Integer, List<Integer>> sortedMap = new TreeMap<>();
        levelQueue.offer(0);
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int score = levelQueue.poll();
            sortedMap.putIfAbsent(score, new ArrayList<>());
            sortedMap.get(score).add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
                levelQueue.offer(score - 1);
            }

            if (node.right != null) {
                queue.offer(node.right);
                levelQueue.offer(score + 1);
            }
        }
        result.addAll(sortedMap.values());
        return result;


    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode root9 = new TreeNode(9);
        TreeNode root20 = new TreeNode(20);
        TreeNode root15 = new TreeNode(15);
        TreeNode root7 = new TreeNode(7);

        root.left = root9;
        root.right = root20;
        root20.left = root15;
        root20.right = root7;
        List<List<Integer>> order = verticalOrderBFS(root);


    }
}
