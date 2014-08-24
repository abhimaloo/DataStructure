package com.leetcode;


import com.maloo.linkedlist.LinkedList;


/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * https://oj.leetcode.com/problems/linked-list-cycle/
 * Created by abhimaloo on 8/23/14.
 */
public class LinkedListDetectCycle extends LinkedList {

    public boolean detectCycle(Node head) {

        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LinkedListDetectCycle list = new LinkedListDetectCycle();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(6);
        list.insert(7);
        list.makeCircular(1);
        System.out.println(list.detectCycle(list.head));

    }

}
