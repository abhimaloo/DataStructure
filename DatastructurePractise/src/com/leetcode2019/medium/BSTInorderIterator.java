package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

import java.util.Stack;

public class BSTInorderIterator {
    Stack<TreeNode> stack = new Stack<>();

    public BSTInorderIterator(TreeNode root) {
        pushAll(root);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        if (hasNext()) {
            TreeNode node = stack.pop();
            int value = node.val;
            pushAll(node.right);
            return value;
        } else {
            throw new RuntimeException("No More values");
        }
    }

    public void pushAll(TreeNode root) {
        if (root != null) {
            stack.push(root);
            pushAll(root.left);
        }
    }


    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(7)
                .setLeft(
                        new TreeNode(3)
                ).setRight(
                        new TreeNode(15).setLeft(new TreeNode(9)).setRight(new TreeNode(20))
                );
        BSTInorderIterator itr = new BSTInorderIterator(tree);
        System.out.println(itr.next());
        System.out.println(itr.next());
    }
}
