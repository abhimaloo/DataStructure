package com.interview.linkedlist;

/**
 * Given a linked list, reverse alternate nodes and append them to end of list. Extra allowed space is O(1)
 *
 * Ex - Input List:  1->2->3->4->5->6
 * Output List: 1->3->5->6->4->2
 * Created by abhishekm787 on 7/28/14.
 */
public class ReverseAlternateNodesAndAppendAtEnd extends  MyLinkedList{
    /**
     * trick is to use reverse logic like previous
     * we will need to merge reverse and delete procedure.
     *
     */
    public void reverseAlternateNode(){
        Node current = head;
        Node reversed = null;

        while(current.next!=null){
            Node next = current.next;
            if(next!=null){
               current.next = next.next;
               next.next = reversed;
               reversed = next;
            }
            if(current.next != null){
                current = current.next;
            }

        }
        current.next = reversed;

    }

    public static void main(String[] args) {
        ReverseAlternateNodesAndAppendAtEnd list = new ReverseAlternateNodesAndAppendAtEnd();
        list.insert(1);
        list.insert(2);
        //list.insert(3);
        //list.insert(4);
        //list.insert(5);
        //list.insert(6);

        list.reverseAlternateNode();
        list.print();
    }

}
