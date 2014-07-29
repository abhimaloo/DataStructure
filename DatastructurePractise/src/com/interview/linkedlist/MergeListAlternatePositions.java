package com.interview.linkedlist;

/**
 * Given two linked lists, insert nodes of second list into first list at alternate positions of first list.
 For example, if first list is 1->3->5->7 and second is 2->4->6, the first list should become
 * 1->2->3->4->5->6->7 and second list should become empty
 * Created by abhimaloo on 7/28/14.
 */
public class MergeListAlternatePositions extends  MyLinkedList{

    /**
     * trick is to keep the pointer for header of list 2
     * Now alternatively tie list 1 and list 2
     * @param list2
     */
    public void merge(MyLinkedList list2) {

        Node cursor = head;
        Node head2 = list2.head;

        while(cursor!=null && head2 != null) {

            Node temp2 = head2.next; //save the rest of the list of 2

            head2.next = cursor.next; // tie last end

            cursor.next = head2; // tie first end

            cursor = head2.next; // increment the counter

            head2 = temp2; // increment the counter for list 2

        }
        //update the head of list 2
        list2.head = head2;

    }

    public static void main(String[] args) {
        MergeListAlternatePositions list1 = new MergeListAlternatePositions();
        MergeListAlternatePositions list2 = new MergeListAlternatePositions();

        list1.insert(1);
        list1.insert(3);
        list1.insert(5);
        list1.insert(7);

        list2.insert(2);
        list2.insert(4);
        list2.insert(6);

        list1.merge(list2);
        list1.print();
        System.out.println("----------------");
        list2.print();

    }
}
