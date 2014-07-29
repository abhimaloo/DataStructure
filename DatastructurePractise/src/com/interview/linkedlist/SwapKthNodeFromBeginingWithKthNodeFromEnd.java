package com.interview.linkedlist;

/**
 * Given a singly linked list, swap kth node from beginning with kth node from end
 * Created by abhimaloo on 7/28/14.
 */
public class SwapKthNodeFromBeginingWithKthNodeFromEnd extends  MyLinkedList{

    /**
     * No trick more labour
     * first get to k-1th element
     * then get to n-k-1th element
     * Now swap the elements
     * @param k
     */
    public void swapKthNode(int k) {
       Node cursor = head;
       // run for k-2 times to reach penultimate node
       for( int i=1; i<k-1; i++){
           cursor = cursor.next;
       }
       Node penultimate1 = cursor;

       Node chaser = head;
       Node penultimate2 = null;

       while(cursor.next!=null){
           penultimate2 = chaser;
           chaser = cursor;
           cursor = cursor.next;
       }

       //swap links of next nodes to penultimate node
       Node temp1 = penultimate1.next.next;
       Node temp2 = penultimate2.next.next;
       Node temp = penultimate1.next;

       penultimate1.next = penultimate2.next;
       penultimate2.next = temp;
       penultimate1.next.next = temp1;
       penultimate2.next.next = temp2;

    }

    public static void main(String[] args) {
        SwapKthNodeFromBeginingWithKthNodeFromEnd list = new SwapKthNodeFromBeginingWithKthNodeFromEnd();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        //list.insert(5);
        //list.insert(6);
       // list.insert(7);
       // list.insert(8);

        list.swapKthNode(2);
    }

}
