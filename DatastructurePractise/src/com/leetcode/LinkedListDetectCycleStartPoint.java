package com.leetcode;

import com.maloo.linkedlist.LinkedList;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * https://oj.leetcode.com/problems/linked-list-cycle-ii/
 * Created by abhimaloo on 8/23/14.
 */
public class LinkedListDetectCycleStartPoint extends LinkedList{

    public Node findCycleStart(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast!= null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }

        Node cursor = head;

        while(cursor != null) {
            if(slow == cursor) {
                return cursor;
            }
            cursor = cursor.next;
            slow = slow.next;

        }

        return null;
    }

    public static void main(String[] args) {
        LinkedListDetectCycleStartPoint list = new LinkedListDetectCycleStartPoint();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(6);
        list.insert(7);
        list.makeCircular(2);
        System.out.println(list.findCycleStart(list.head).data);
    }
}
