package com.leetcode.linkedlist.impl;

/**
 * Created by abhimaloo on 8/25/14.
 */
public class LinkedListRandom {
    public Node head = null;

    public Node insert(Node head, int data, Node random) {
        Node newNode = new Node(data,null,random);
        if(this.head == null) {
            this.head = newNode;
            return newNode;
        }

        while(head.next != null) {
            head = head.next;
        }
        head.next = newNode;

        return  newNode;
    }


    public Node findAndSetRandom(Node head, int data, int randomData) {
        Node cursor = head;
        while(cursor != null) {
            if(cursor.data == data) {
                Node cursor2 = head;
                while(cursor2 != null) {
                    if(cursor2.data == randomData) {
                        cursor.random = cursor2;
                        return cursor;
                    }

                    cursor2 = cursor2.next;
                }
            }

            cursor = cursor.next;
        }

        return null;
    }

    public void print(Node head) {
        while(head!=null) {
            System.out.println(" Data :" + head.data);
            if(head.random != null) {
                System.out.println( " Random : " + head.random.data);
            }

            head = head.next;
        }
    }

}
