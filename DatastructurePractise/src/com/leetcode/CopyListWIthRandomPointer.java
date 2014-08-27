package com.leetcode;

import com.leetcode.linkedlist.impl.LinkedListRandom;
import com.leetcode.linkedlist.impl.Node;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

 Return a deep copy of the list.

 https://oj.leetcode.com/problems/copy-list-with-random-pointer/
 * Created by abhimaloo on 8/25/14.
 */
public class CopyListWIthRandomPointer extends LinkedListRandom{

    /**
     * Trick is to apply these steps -
     * a) Add a duplicate node in front of every node of the linked list. for ex - 1->2->3->4-5>  will become 1->1->2->2->3->3->4->4->5->5
     * b) Now assign randome pointers like -  node.next.random = node.random.next
     * c) Now break the interleaving into two separate lists
     * @param head
     * @return
     */
    public Node clone(Node head) {
        Node cursor = head;
        // add a copy of node after every node with data copied
        while(cursor != null) {
            Node copyNode = new Node(cursor.data ,cursor.next, null);
            cursor.next = copyNode;
            cursor = copyNode.next;
        }

        // now populate the arbitary pointer
        cursor = head;
        while(cursor != null && cursor.next != null) {
            if(cursor.random != null) {
                cursor.next.random = cursor.random.next;
            }
            cursor = cursor.next.next;
        }

        Node copy = head.next;
        Node response = copy;
        cursor = head;
        // here we break the interleaving between two lists one by one
        while(cursor!= null && cursor.next != null && copy!= null) {
            cursor.next = copy.next;
            if(cursor.next!= null) {
                copy.next = cursor.next.next;
            }

            cursor = cursor.next;
            copy = copy.next;
        }

        return response;
    }


    public static void main(String[] args) {
        CopyListWIthRandomPointer list = new CopyListWIthRandomPointer();
        list.insert(list.head, 1, null);
        list.insert(list.head, 2, null);
        list.insert(list.head, 3, null);
        list.insert(list.head, 4, null);
        list.insert(list.head, 5, null);



        list.findAndSetRandom(list.head, 2, 5);
        list.findAndSetRandom(list.head, 1, 4);
        list.findAndSetRandom(list.head, 3, 4);
        list.findAndSetRandom(list.head, 4, 2);
        list.findAndSetRandom(list.head, 5, 1);

        list.print(list.head);

        System.out.println("----------------------------------");

        Node clone  = list.clone(list.head);
        list.print(clone);

    }
}
