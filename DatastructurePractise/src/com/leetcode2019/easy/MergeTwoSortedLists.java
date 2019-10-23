package com.leetcode2019.easy;

public class MergeTwoSortedLists {
    public static ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
        ListNode response = new ListNode(0);
        ListNode cursor = response;
        while (list1 != null || list2 != null) {
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

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
