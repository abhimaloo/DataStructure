package com.interview.linkedlist;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You must do this in-place without altering the nodes' values.
 * ex - Input- 1->2->3->4->5->6
 * Output -  1->6->2->5->3->4
 * Created by abhimaloo on 7/30/14.
 */
public class ReorderList extends  MyLinkedList{

    /**
     * pretty enggagged code -
     * first go to mid point -  use turtle and hair approach
     * now reverse the sublist from midPoint to end
     *
     * Now interleave the first half and reversed second half
     */
    public void reorderList() {
        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        //slow is at n/2 .. reverse the list till end
        Node cursor = slow;
        //reversed will contain the reversed second half
        Node reversed = null;
        while(cursor!=null){
            Node temp = cursor.next;
            cursor.next = reversed;
            reversed = cursor;
            cursor = temp;
        }

        Node cursor1 = head;
        Node cursor2 = reversed;
        Node temp = null;
        //Now interleave first half and reversed second half
        while(cursor1 != slow){
           temp = cursor1.next;
           cursor1.next = cursor2;
           cursor2 = temp;
           cursor1 = cursor1.next;
        }


    }

    public static void main(String[] args) {
        ReorderList list = new ReorderList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(6);
        list.insert(7);

        list.reorderList();
        list.print();

    }
}
