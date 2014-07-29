package com.interview.linkedlist;

/**
 * Given a singly linked list, write a function to swap elements pairwise. For example,
 * if the linked list is 1->2->3->4->5->6->7 then the function should change it to 2->1->4->3->6->5->7
 *
 * Created by abhishekm787 on 7/28/14.
 */
public class PairwiseSwapNodes extends  MyLinkedList{

    public void pairwiseSwap(){
        Node back = head;
        Node front = head.next;
        boolean headSet = false;

        while(front!=null) {
            Node temp = front.next;
            front.next = back;
            back.next = temp;
            if(!headSet){
                head = front;
                headSet = true;
            }
            if(temp!=null && temp.next!=null){
                front = temp.next;
            }
            back = back.next;
        }
    }

    public static void main(String[] args) {
        PairwiseSwapNodes list = new PairwiseSwapNodes();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(6);

        list.pairwiseSwap();

    }
}
