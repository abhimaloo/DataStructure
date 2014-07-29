package com.interview.linkedlist;

/**
 * Given a singly linked list, write a function to swap elements pairwise. For example,
 * if the linked list is 1->2->3->4->5->6->7 then the function should change it to 2->1->4->3->6->5->7
 *
 * Created by abhishekm787 on 7/28/14.
 */
public class PairwiseSwapNodes extends  MyLinkedList{

    /** trick is to keep two pointers front and back
     * keep swapping them and maintain a pointer called swapped which points to the last element got reversed
     * increment the both the pointers again
     *
     */
    public void pairwiseSwap(){
        //handle edge case
        if(head ==null){
            return;
        }
        Node back = head;
        Node front = head.next;

        Node swapped = null;


        while(front!=null && back!=null) {
            //this is swapping logic
            Node temp = front.next;
            front.next = back;
            back.next = temp;
            //this is to set head
            if(swapped == null){
                head = front;
            } else {
                //this with attach the result with old result
                swapped.next = front;
            }
            swapped = front.next;
            //incerement both the pointers
            back = temp;
            if(temp!=null){
                //increment front if there is a possibility
                front = temp.next;
            }
        }
    }

    public static void main(String[] args) {
        PairwiseSwapNodes list = new PairwiseSwapNodes();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        //list.insert(6);

        list.pairwiseSwap();
        list.print();

    }
}
