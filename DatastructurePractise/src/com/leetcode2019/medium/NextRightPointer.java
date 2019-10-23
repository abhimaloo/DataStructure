package com.leetcode2019.medium;

/*
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 */
public class NextRightPointer {
    /**
     * Idea is to start level by level since its a complete binary tree
     * Keep going to the left child
     * Now
     */
    public LNode connect(LNode root) {
        LNode levelStart = root;
        while (levelStart != null) {
            LNode cur = levelStart;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.right != null && cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            levelStart = levelStart.left;
        }
        return root;
    }
}

class LNode {
    public int val;
    public LNode left;
    public LNode right;
    public LNode next;

    public LNode() {
    }

    public LNode(int _val, LNode _left, LNode _right, LNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
