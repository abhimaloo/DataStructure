package com.interview.linkedlist;

/**
 * Given a Doubly Linked List which has data members sorted in ascending order.
 * Construct a Balanced Binary Search Tree which has same data members as the given Doubly Linked List.
 * The tree must be constructed in-place (No new node should be allocated for tree conversion)
 * Created by abhimaloo on 7/30/14.
 */
public class DoublyLinkedLIstToBST<T extends Comparable<T>> extends DoublyLinkedList<T>{
    /**
     * trick is to find the median and assign it as root..
     * recursively keep finding medians and make them right child and left child
      * @return
     */
    public void inOrder(DoublyNode<T> node) {
        if(node!=null) {
            inOrder(node.previous);
            System.out.println(node.data);
            inOrder(node.next);
        }
    }

    public DoublyNode<T> convertToBST(DoublyNode<T> start, DoublyNode<T> end) {
        DoublyNode<T> left = start;
        DoublyNode<T> right = end;
        DoublyNode<T> root = null;
        if(left==null || right ==null) {
            return root;
        }

        //have to handle data comparision step for even sized nodes
        if(left==right || left.data.compareTo(right.data) >0){
            left.previous = null;
            left.next = null;
            return left;
        }


        boolean isEvenSized = false;
        //try finding the middle element
        while(left.next!= right.previous){
            //for even sized list
            if(left.next == right && right.previous ==left) {
                isEvenSized = true;
                break;
            }

            left = left.next;
            right = right.previous;
        }

        //make the left(even) or middle element(odd) root
        root = right.previous;

        if(isEvenSized){

          //very important .. have to handle even sized lists
          root.previous = convertToBST(start!=root?start:null , root.previous!=start? root.previous:null);
          root.next = convertToBST(root.next, end);
        } else {
            //very easy for odd sized list
            root.previous = convertToBST(start, left);
            root.next = convertToBST(right,end);
        }

        return root;
    }

    public static void main(String[] args) {
        DoublyLinkedLIstToBST<Integer> list = new DoublyLinkedLIstToBST<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        ///list.insert(6);
        //list.insert(7);


        list.inOrder(list.convertToBST(list.head,list.tail));
    }


}
