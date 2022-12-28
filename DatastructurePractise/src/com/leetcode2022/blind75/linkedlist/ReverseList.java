package com.leetcode2022.blind75.linkedlist;

/*
Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode cursor = head;
        ListNode reverse = null;
        ListNode front = null;
        while (cursor != null) {
            /*
            Steps for example : 1->2->3->4
            cursor = 1
            reverse = null
            front = null

            1 - save 2 pointer in originalNext pointer
            2 - break the link between 1 -> 2 and instead make 1 the head of reversed list i.e cursor.next = reverse
            3. 1 should now be treated a new reversed head i.e reverse = cursor
            4. move the cursor to the original next - i.e. cursor = originalNext

             */
            front = cursor.next; // save the next pointer of the original list node
            cursor.next = reverse; // break the next connection and point it as the front node for the reverse list
            reverse = cursor;  // make the front node -> new reverse list
            cursor = front; // move the cursor to the saved next node of original list
        }

        head = reverse;  // finally change the head to be reverse
        return head;
    }

}
