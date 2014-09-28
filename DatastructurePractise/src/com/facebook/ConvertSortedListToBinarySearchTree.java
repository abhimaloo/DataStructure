package com.facebook;

import com.crackCode.linkedlist.LinkedList;
import com.crackCode.linkedlist.Node;
import com.interview.trees.impl.BSTNode;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class ConvertSortedListToBinarySearchTree {

    public static BSTNode<Integer> convert(Node<Integer> head) {
        if(head == null) {
            return null;
        }

        Node<Integer> slow = head ;
        Node<Integer> fast = head;
        Node<Integer> prev = null;

        while(fast!=null && fast.next!= null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(prev != null) {
            prev.next = null;
        } else{
            head = null;
        }
        BSTNode<Integer> root = new BSTNode<>(slow.data, null, null);
        root.left = convert(head);
        root.right = convert(slow.next);
        return root;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(2);
        list.insert(4);
        list.insert(6);
        list.insert(8);
        list.insert(10);
        list.insert(12);
        list.insert(14);

        BSTNode<Integer> root = convert(list.head);


    }

}
