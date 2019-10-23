package com.leetcode2019.medium;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * <p>
 * Example:
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * <p>
 * Given n will always be valid.
 * <p>
 * Follow up:
 * <p>
 * Could you do this in one pass?
 */
public class RemoveNThNodeFromEndOfList {
    public static ListNode removeNthFromLast(ListNode head, int n) {
        ListNode cursor = head;
        ListNode back = null;
        ListNode fwd = cursor;
        for (int i = 0; i < n - 1; i++) {
            if (fwd.next == null) {
                throw new RuntimeException("improper N");
            }
            fwd = fwd.next;
        }

        while (fwd.next != null) {
            back = cursor;
            cursor = cursor.next;
            fwd = fwd.next;
        }
        if (back == null) {
            return head.next;
        } else {
            back.next = cursor.next;
            return head;
        }
    }
}
