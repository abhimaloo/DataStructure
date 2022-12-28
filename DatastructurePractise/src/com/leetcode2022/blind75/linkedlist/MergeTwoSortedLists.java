package com.leetcode2022.blind75.linkedlist;

/*
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode response = new ListNode(0);
        ListNode cursor = response;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cursor.next = list1;
                list1 = list1.next;
            } else {
                cursor.next = list2;
                list2 = list2.next;
            }
            cursor = cursor.next;

        }

        if (list1 != null) {
            cursor.next = list1;
        }

        if (list2 != null) {
            cursor.next = list2;
        }

        return response.next;
    }
}
