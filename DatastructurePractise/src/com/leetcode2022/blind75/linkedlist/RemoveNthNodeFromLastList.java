package com.leetcode2022.blind75.linkedlist;

/*
Given the head of a linked list, remove the nth node from the end of the list and return its head.



Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
 */
public class RemoveNthNodeFromLastList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode front = head;
        ListNode back = head;
        ListNode backBack = null;

        // idea is to keep the pointers "n-1" distance away
        for (int i = 0; i < n - 1; i++) {
            front = front.next;
        }

        while (front.next != null) {
            backBack = back;
            back = back.next;
            front = front.next;
        }

        if (backBack == null) {
            return head.next;
        } else {
            backBack.next = back.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        ListNode newHead = removeNthFromEnd(head, 2);

    }
}
