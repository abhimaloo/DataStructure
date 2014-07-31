package com.interview.linkedlist;

/**
 * Given a singly linked list, rotate the linked list counter-clockwise by k nodes.
 * Where k is a given positive integer.
 * For example, if the given linked list is 10->20->30->40->50->60 and k is 4,
 * the list should be modified to 50->60->10->20->30->40
 * Created by abhimaloo on 7/29/14.
 */
public class RotateList extends  MyLinkedList{
    public void rotateList(int k) {
        //for k as zero or non negative .. there will be no change in linked list
        if(k<1) {
            return;
        }
        Node cursor = head;
        //move K times
        for(int i=1; i<k; i++) {
            cursor = cursor.next;
        }

        Node rotator = cursor.next;
        while(rotator.next!=null) {
            rotator = rotator.next;
        }

        Node temp = head;
        head = cursor.next;
        rotator.next = temp;
        cursor.next = null;

    }

    public static void main(String[] args) {
        RotateList list = new RotateList();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);
        list.insert(50);
        list.insert(60);

        list.rotateList(4);
        list.print();
    }
}
