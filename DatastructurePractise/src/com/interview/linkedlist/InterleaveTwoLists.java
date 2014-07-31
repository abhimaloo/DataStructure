package com.interview.linkedlist;

/**
 * Problem  - Interleave two linked list of different sizes
 * Created by abhimaloo on 7/30/14.
 */
public class InterleaveTwoLists extends  MyLinkedList{


    /**
     * trick is teo keep two pointers and keep them swapping accordingly
     * @param otherHead
     */
    public void interleave(Node otherHead){
        Node cursor1 = head;
        Node cursor2 = otherHead;
        Node temp = null;
        while(cursor1!=null) {
            // save the next link
            temp = cursor1.next;
            //null checking is to fix uneven length on either list
            if(cursor2 !=null) {
                //ingterleave the link
                cursor1.next = cursor2;
            } else {
                break;
            }
            //null checking is to fix uneven length on either list
            if(temp!=null) {
                //swap the saved link for next iteration
                cursor2 = temp;
            } else {
                break;
            }
            // increment the counter
            cursor1 = cursor1.next;
        }

    }

    public static void main(String[] args) {
        InterleaveTwoLists list = new InterleaveTwoLists();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);

        InterleaveTwoLists list1 = new InterleaveTwoLists();
        list1.insert(5);
        list1.insert(6);
        list1.insert(7);
        list1.insert(8);
        list1.insert(9);
        list1.insert(10);

        list1.interleave(list.head);
        list1.print();
    }
}
