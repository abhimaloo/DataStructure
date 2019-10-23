package com.leetcode2019.hard;

import com.datastructure2019.tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Example:
 * <p>
 * You may serialize the following tree:
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeBinaryTree {

    public static String DELIMETER = "/";
    public static String NULL = "X";

    public static String serialize(TreeNode root) {
        if (root == null) {
            return NULL + DELIMETER;
        } else {
            return String.valueOf(root.val) + DELIMETER + serialize(root.left) + serialize(root.right);
        }
    }

    public static TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        LinkedList<String> nodes = new LinkedList<String>();
        nodes.addAll(Arrays.asList(data.split(DELIMETER)));
        return buildTree(nodes);
    }

    public static TreeNode buildTree(LinkedList<String> data) {
        String val = data.remove();
        if (!val.equals(NULL)) {
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = buildTree(data);
            node.right = buildTree(data);
            return node;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(1)
                .setLeft(
                        new TreeNode(2)
                ).setRight(
                        new TreeNode(3).setLeft(new TreeNode(4)).setRight(new TreeNode(5))
                );
        String ser = serialize(tree);
        System.out.println(ser);
        //TreeNode root = deserialize(ser);
    }
}
