package com.leetcode;

import com.interview.linkedlist.MyLinkedList;
import com.interview.linkedlist.Node;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

 You must do this in-place without altering the nodes' values.

 https://oj.leetcode.com/problems/reorder-list/
 * Created by abhimaloo on 8/23/14.
 */
public class ReorderList extends MyLinkedList {

    /**
     * trick is to first find the midPoint and break the list into two halves .. reverse the second half and then interleave
     * the two haves
     * @param root
     */
    public void reOrder(Node root) {
        Node slow = root;
        Node fast = root;
        Node prev = null;
        // find the mid point
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // break the bond between two halves
        prev.next = null;


        Node cursor = slow;
        Node reverse = null;
        // reverse the second half
        while(cursor!=null) {
            Node temp = cursor.next;
            cursor.next = reverse;
            reverse = cursor;
            cursor = temp;
        }

        Node cursor1 = head;
        Node cursor2 = reverse;

        // interleave first half and reversed second half
        while(cursor1!=null && cursor2!=null) {
            Node temp = cursor1.next;
            cursor1.next = cursor2;
            cursor2 = temp;
            cursor1 = cursor1.next;
        }
    }

    public static void main(String[] args) {
        ReorderList list = new ReorderList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(6);
        list.insert(7);

        list.reOrder(list.head);
        list.print();
    }



}
