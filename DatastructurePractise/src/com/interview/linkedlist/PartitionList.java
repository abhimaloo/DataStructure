package com.interview.linkedlist;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come
 * before nodes greater than or equal to x.
 *
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 * Created by abhimaloo on 7/30/14.
 */
public class PartitionList extends MyLinkedList{
    /**
     * trick is to create two sublists. One for smaller elements
     * Other for equal or big elements
     * finally hook them together and set the head to the right position as well
     * @param k
     */
    public void partitionList(int k) {
        if(head==null && head.next ==null){
            return;
        }

        Node leftEnd = null;
        Node rightEnd = null;

        Node leftHook = null;
        Node rightHook = null;

        Node cursor = head;
        //iterate and investigate the data value
        while(cursor!=null) {
            //if data is small add this node to left list
            if(cursor.data<k) {
                if(leftEnd!=null){
                    leftEnd.next = cursor;
                    leftEnd = leftEnd.next;
                } else{
                    leftEnd = cursor;
                    leftHook = cursor;
                }
            } else {
                //else add this node to right list
                if(rightEnd!=null){
                    rightEnd.next = cursor;
                    rightEnd = rightEnd.next;
                } else{
                    rightEnd = cursor;
                    rightHook = cursor;
                }
            }

            cursor = cursor.next;
        }

        //hook them togather
        leftEnd.next = rightHook;
        //important. dont forget to put null at last of right end
        rightEnd.next = null;
        //set the head
        head = leftHook;
    }

    public static void main(String[] args) {
        PartitionList list = new PartitionList();
        list.insert(1);
        list.insert(4);
        list.insert(3);
        list.insert(2);
        list.insert(5);
        list.insert(2);

        list.partitionList(3);
        list.print();
    }
}
