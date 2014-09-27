package com.facebook;

import com.crackCode.linkedlist.LinkedList;
import com.crackCode.linkedlist.Node;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

 You must do this in-place without altering the nodes' values.

 For example,
 Given {1,2,3,4}, reorder it to {1,4,2,3}.

 * Created by abhimaloo on 9/26/14.
 */
public class ReorderList {

    public static Node<Integer> reOrder(Node<Integer> head) {

        //take care of zero size and single size lists
        if(head == null || head.next == null) {
            return head;
        }

        // first break the list into two equal parts and reverse the later part
        Node<Integer> slow = head;
        Node<Integer> fast = head;
        Node<Integer> prev = null;

        while(fast!= null && fast.next!= null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // break the list
        prev.next = null;

        Node<Integer> leftHead = head;
        Node<Integer> rightHead = reverse(slow);

        // interleave both the lists
        while(leftHead!= null && rightHead!= null) {
            Node<Integer> temp = leftHead.next;
            leftHead.next = rightHead;
            leftHead = leftHead.next;
            rightHead = temp;
        }

        return head;
    }

    private static Node reverse(Node head) {

        Node reversed = null;
        while(head!= null) {
            Node temp = head.next;
            head.next = reversed;
            reversed = head;
            head = temp;
        }

        return reversed;
    }

    public static void main(String[] args) {
        LinkedList<Integer> unsortedList = new LinkedList<>();
        unsortedList.insert(1);
        unsortedList.insert(2);
        unsortedList.insert(3);
        unsortedList.insert(4);
        unsortedList.insert(5);
        unsortedList.insert(6);
        unsortedList.insert(7);

        Node<Integer> reOrderedHead = reOrder(unsortedList.head);
        while(reOrderedHead!= null) {
            System.out.println(reOrderedHead.data);
            reOrderedHead = reOrderedHead.next;
        }

    }

}
