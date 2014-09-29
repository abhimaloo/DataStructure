package com.facebook;

import com.crackCode.linkedlist.LinkedList;
import com.crackCode.linkedlist.Node;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class RemoveDuplicateFromSortedList {
   public static Node<Integer> removeDuplicateLeaveDistinct(Node<Integer> head) {
       if(head == null || head.next == null) {
           return head;
       }

       Node<Integer> front = head;
       Node<Integer> prev = null;
       while(front!= null) {

           int data = front.data;
           Node<Integer> cursor = front.next;
           while(cursor!= null && cursor.data.equals(data)) {
               cursor = cursor.next;
           }

           if(cursor != front.next) {
               if(prev == null) {
                   head = cursor;
                   front = cursor;
               } else{
                   prev.next = cursor;
                   front = cursor;
               }
           } else{
               prev = front;
               front = front.next;
           }

       }

       return head;
   }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(1);
        list.insert(1);
        list.insert(3);
        list.insert(3);
        list.insert(4);
        list.insert(4);


        list.head = removeDuplicateLeaveDistinct(list.head);

    }


}
