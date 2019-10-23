package com.leetcode2019.medium;

/*
https://leetcode.com/problems/add-two-numbers/
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.

 */
public class AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode first, ListNode second) {
        int carry = 0;
        ListNode head = null;
        ListNode prev = head;
        while (first != null || second != null) {
            int sum = ((first != null ? first.val : 0) + (second != null ? second.val : 0)) + carry;
            carry = sum / 10;
            sum = sum % 10;
            if (prev == null) {
                head = new ListNode(sum);
                prev = head;
            } else {
                prev.next = new ListNode(sum);
                prev = prev.next;
            }

            first = first != null ? first.next : first;
            second = second != null ? second.next : second;
        }
        // do not forget to consider the carry
        if (carry > 0) {
            prev.next = new ListNode(carry);
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode first = new ListNode(9);
        first.next = new ListNode(1);
        first.next.next = new ListNode(6);


        ListNode second = new ListNode(0);


        ListNode head = addTwoNumbers(first, second);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}

class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode setNext(ListNode n) {
        this.next = n;
        return this;
    }
}
