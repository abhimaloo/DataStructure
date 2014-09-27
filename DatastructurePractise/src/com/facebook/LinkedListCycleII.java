package com.facebook;

import com.crackCode.linkedlist.Node;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Created by abhimaloo on 9/26/14.
 */
public class LinkedListCycleII {

    public static Node<Integer> cycleStart(Node<Integer> head) {

        //zero or single node
        if(head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!= null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }
        //it means no cycles were found
        if(fast.next == null) {
            return null;
        }

        Node cursor = head;
        while(cursor != slow) {
            cursor = cursor.next;
            slow = slow.next;
        }

        return cursor;
    }
}
