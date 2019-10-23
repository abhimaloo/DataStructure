package com.leetcode2019.easy;

/*
https://leetcode.com/problems/reverse-linked-list/submissions/
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode reverseListHead = null;
        while (head != null) {
            ListNode next = head.next;  // save the next pointer
            head.next = reverseListHead;
            reverseListHead = head;
            head = next;
        }

        return reverseListHead;
    }

    public ListNode reverse(ListNode head, ListNode reverseListHead) {
        if (head != null) {
            ListNode next = head.next;
            head.next = reverseListHead;
            return reverse(next, head);
        }
        return reverseListHead;
    }
}
