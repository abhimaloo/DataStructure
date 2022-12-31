package com.leetcode2022.blind75.tree;

import java.util.LinkedList;

/*
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.



Example 1:


Input: root = [3,1,4,null,2], k = 1
Output: 1
Example 2:


Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3


Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 104
0 <= Node.val <= 104


Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
 */
public class KthSmallestElementInBST {

    /*
    Idea is to do InOrder Traversal Iteratively.
    While processing the element keep a counter and come out of the method once the counter reaches 0
     */
    public int kthSmallest(TreeNode root, int k) {
        if (k == -1 || root == null) {
            return -1;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        boolean done = false;
        TreeNode curr = root;
        while (!done) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                if (!stack.isEmpty()) {
                    curr = stack.pop();
                    if (--k == 0) {
                        return curr.val;
                    }
                    curr = curr.right;
                } else {
                    done = true;
                }
            }
        }

        return -1;

    }

}
