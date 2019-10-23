package com.leetcode2019.medium;

import java.util.Stack;

/*
https://leetcode.com/problems/reorder-list/submissions/
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderList {
    public static void reorderList(ListNode head) {
        if (head == null) return;
        Stack<ListNode> st = new Stack();
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null) {
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
            slow = slow.next;
        }

        // slow is at mid point; start putting the nodes in the stack
        if (slow.next != null) {
            ListNode rev = slow.next;
            slow.next = null; // break the connection
            while (rev != null) {
                st.push(rev);
                rev = rev.next;
            }
        }
        // interleave after here
        ListNode il = head;
        while (il != null) {
            if (!st.isEmpty()) {
                ListNode rev = st.pop();
                rev.next = il.next;
                il.next = rev;
                il = rev.next;
            } else {
                il = il.next;
            }
        }
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(1).setNext(new ListNode(2).setNext(new ListNode(3).setNext(new ListNode(4).setNext(new ListNode(5)))));
        reorderList(list);
    }
}
