package com.leetcode2022.blind75.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.



Example 1:


Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]
Example 2:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 104].
-1000 <= Node.val <= 1000
 */
public class SerializeAndDeserializeBinaryTree {
    private static final String DELIMETER = "#";
    private static final String NULL = "X";

    public String serialize(TreeNode root) {
        if (root == null) {
            return NULL + DELIMETER;
        }

        return String.valueOf(root.val) + DELIMETER + serialize(root.left) + serialize(root.right);
    }

    public TreeNode deserialize(String data) {
        Queue<String> nodeList = new LinkedList<>();
        nodeList.addAll(Arrays.asList(data.split(DELIMETER)));
        return buildTree(nodeList);
    }

    public TreeNode buildTree(Queue<String> nodeList) {
        String node = nodeList.remove();
        if (!node.equals(NULL)) {
            TreeNode root = new TreeNode(Integer.parseInt(node));
            root.left = buildTree(nodeList);
            root.right = buildTree(nodeList);
            return root;
        } else {
            return null;
        }
    }

}
