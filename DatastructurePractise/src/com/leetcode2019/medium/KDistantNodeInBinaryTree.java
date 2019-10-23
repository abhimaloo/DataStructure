package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

import java.util.*;

public class KDistantNodeInBinaryTree {
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        if (root == null || target == null) return result;
        if (K == 0) {
            result.add(target.val);
            return result;
        }

        // mark all the parents
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        parentMap.put(root.val, null);
        findParent(root, parentMap);


        //  do BFS
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(target);
        levelQueue.offer(0);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int level = levelQueue.poll();
            visited.add(node.val);
            if (level == K) {
                result.add(node.val);
            }

            if (node.left != null && !visited.contains(node.left.val)) {
                queue.offer(node.left);
                levelQueue.offer(level + 1);
                visited.add(node.left.val);
            }

            if (node.right != null && !visited.contains(node.right.val)) {
                queue.offer(node.right);
                levelQueue.offer(level + 1);
                visited.add(node.right.val);
            }
            TreeNode parent = parentMap.get(node.val);
            if (parent != null && !visited.contains(parent.val)) {
                queue.offer(parent);
                levelQueue.offer(level + 1);
                visited.add(parent.val);
            }
        }

        return result;
    }

    public static void findParent(TreeNode root, Map<Integer, TreeNode> parentMap) {
        if (root == null) return;
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            findParent(root.left, parentMap);
        }

        if (root.right != null) {
            parentMap.put(root.right.val, root);
            findParent(root.right, parentMap);
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        distanceK(root, root.left, 2);
    }
}
