package com.interview.linkedlist;

/**
 * Given a singly linked list, remove all the nodes which have a greater value on right side.
 * The list 12->15->10->11->5->6->2->3->NULL should be changed to 15->11->6->3->NULL
 * Created by abhimaloo on 7/30/14.
 */
public class DeleteNodesWithGreaterValueOnRightSide extends MyLinkedList{

    /**
     * trick is to reverse the list first
     * Now traverse the list from left to right
     * keep the maxElement dound so far and compare it with current node
     * if currentNode is smaller than MAX element found so far .. delete that node.
     * finally reverse the linkedList again
     */
    public void deleteNodes(){
        //reverse the list
        reverse();
        int max = Integer.MIN_VALUE;
        Node cursor = head;
        Node previous = null;
        //repeatedly delete nodes ..if node is less than the MAX value found so far
        while(cursor!=null){
            if(cursor.data < max) {
                if(previous == null){
                    head = head.next;
                } else {
                    previous.next = cursor.next;
                }
            } else {
                max = cursor.data;
                previous = cursor;
            }
            cursor = cursor.next;
        }

        //reverse again
        reverse();

    }

    public static void main(String[] args) {
        DeleteNodesWithGreaterValueOnRightSide list = new DeleteNodesWithGreaterValueOnRightSide();
        list.insert(12);
        list.insert(15);
        list.insert(10);
        list.insert(11);
        list.insert(5);
        list.insert(6);
        list.insert(2);
        list.insert(3);

        list.deleteNodes();
        list.print();

    }

}
