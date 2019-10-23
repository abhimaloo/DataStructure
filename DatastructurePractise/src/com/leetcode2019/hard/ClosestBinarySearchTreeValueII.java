package com.leetcode2019.hard;

import com.datastructure2019.tree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/closest-binary-search-tree-value-ii/
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?


 */
public class ClosestBinarySearchTreeValueII {
    /*
    this approach is On average case. We need to do better than this. We need to prune the tree the moment we have k closest elements
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        PriorityQueue<TreeNode> maxHeap = new PriorityQueue<>(Comparator.<TreeNode>comparingDouble((n) -> Math.abs(n.val - target)).reversed());
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            double diff = Math.abs(node.val - target);
            if (maxHeap.size() == k) {
                if (diff < Math.abs(maxHeap.peek().val - target)) {
                    maxHeap.poll();
                    maxHeap.offer(node);
                }
            } else {
                maxHeap.offer(node);
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        result = maxHeap.stream().map(n -> n.val).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }


    /*
    This approach is InOrder traversal of tree.
     */
    public static List<Integer> closestKValuesII(TreeNode root, double target, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        inorder(root, target, k, list);
        Collections.reverse(list);
        return list;
    }

    /*
    Trick is to think this way
    - keep a Queue of size K
    Keep going in Inorder way; that way you are processing elements in sorted order
    Now for evevery element when the queue has reaches size k -
    if difference of (smallest element - target) < (currval - target) then anything bigger than currValue would also have bigger difference hence prune the tree
    else if diference of currentval -  target is smaller than smallest - target. It means anything bigger than currvalue could also be eligible for closest elements
     */
    public static void inorder(TreeNode root, double target, int k, LinkedList<Integer> queue) {
        if (root == null) return;
        inorder(root.left, target, k, queue);
        if (queue.size() == k) {
            if (Math.abs(queue.peekFirst() - target) > Math.abs(root.val - target)) {
                queue.removeFirst();
            } else {
                return;
            }
        }
        queue.add(root.val);
        inorder(root.right, target, k, queue);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree(new Integer[]{4, 2, 5, 1, 3, null, null, null, null, null, null});
        closestKValuesII(root, 3.714286, 2);
    }
}
