package com.interview.linkedlist;

/**
 * Given only a pointer to a node to be deleted in a singly linked list, how do you delete it?
 * Ex- 1->2->3->4->5  . node given is 3
 * Output should be  - 1->2->4->5
 * Created by abhimaloo on 7/30/14.
 */
public class DeleteNodeGIvenAccessToThatNodeOnly extends MyLinkedList{

    public void deleteNode(Node node) {
        Node prev = null;

        if(node.next!=null) {

            while(node.next!=null){
              node.data = node.next.data;
              prev = node;
              node = node.next;
            }
            prev.next = null;
        }
    }

    public static void main(String[] args) {
        DeleteNodeGIvenAccessToThatNodeOnly list = new DeleteNodeGIvenAccessToThatNodeOnly();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);

        list.deleteNode(list.head.next.next);
        list.print();
    }
}
