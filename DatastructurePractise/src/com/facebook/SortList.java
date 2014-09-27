package com.facebook;

import com.crackCode.linkedlist.LinkedList;
import com.crackCode.linkedlist.Node;

/**
 * Created by abhimaloo on 9/26/14.
 */
public class SortList {

    //apply merge sort
    public static Node<Integer> sortLinkedList(Node<Integer> head) {
        if(head == null) {
            return null;
        }

        if(head.next == null) {
            return head;
        }

        Node<Integer>[] leftAndRight = divideByMidPoint(head);

       return merge(sortLinkedList(leftAndRight[0]), sortLinkedList(leftAndRight[1]));
    }


    public static Node<Integer> merge(Node<Integer> left, Node<Integer> right) {

        if(left == null){
            return right;
        }
        if(right == null) {
            return left;
        }

        Node<Integer> sorted = null;
        if(left!= null && right!= null) {
            if(left.data.compareTo(right.data) <= 0){
                sorted = left;
                sorted.next = merge(left.next, right);
            } else {
                sorted = right;
                sorted.next = merge(left, right.next);
            }
        }

        return sorted;
    }



    private static Node[] divideByMidPoint(Node<Integer> head) {
        Node[] result = new Node[2];
        // for single node linkedList
        if(head!= null && head.next == null) {
            result[0] = head;
            result[1] = null;
            return result;
        }


        Node slow = head;
        Node fast = head;
        Node prev = null;

        while(fast!= null && fast.next!=null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;

        result[0] = head;
        result[1] = slow;
        return result;

    }

    public static void main(String[] args) {
        LinkedList<Integer> unsortedList = new LinkedList<>();
        unsortedList.insert(8);
        unsortedList.insert(3);
        unsortedList.insert(7);
        unsortedList.insert(5);
        unsortedList.insert(1);

        Node<Integer> sortedRoot = sortLinkedList(unsortedList.head);

        while(sortedRoot!= null) {
            System.out.println(sortedRoot.data);
            sortedRoot = sortedRoot.next;
        }


    }


}
