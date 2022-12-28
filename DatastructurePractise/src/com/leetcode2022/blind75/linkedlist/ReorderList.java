package com.leetcode2022.blind75.linkedlist;

/*
You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 */
public class ReorderList {
    public static void reorderList(ListNode head) {
        // idea is to first find the mid point of the list;
        // break the list into 2 parts
        // reverse the second half
        // interleave it with first one

        ListNode slow = head;
        ListNode slower = null;
        ListNode fast = head;
        ListNode firstHalf = head;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slower = slow;
                fast = fast.next;
                slow = slow.next;
            }
        }

        // break the list
        ListNode midPoint = null;
        if (slower != null) {
            midPoint = slower.next;
            slower.next = null;
        }

        // reverse the midPoint list
        ListNode reversed = null;
        ListNode originalNext = null;
        ListNode cursor = midPoint;

        while (cursor != null) {
            originalNext = cursor.next;
            cursor.next = reversed;
            reversed = cursor;
            cursor = originalNext;
        }

        // reversed and head are the two linkedLists which needs to be interleaved
        ListNode left = firstHalf;
        ListNode right = reversed;
        while (left != null && right != null) {
            ListNode temp1 = left.next;
            ListNode temp2 = right.next;
            if (left.next != null) {
                right.next = left.next;
            }
            left.next = right;
            left = temp1;
            right = temp2;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        //ListNode five = new ListNode(5);
        head.next = two;
        two.next = three;
        three.next = four;
        //four.next = five;
        reorderList(head);
    }

}
