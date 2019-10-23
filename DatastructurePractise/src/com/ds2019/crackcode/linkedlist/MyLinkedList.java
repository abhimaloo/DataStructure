package com.ds2019.crackcode.linkedlist;

public class MyLinkedList {
    public Node head = null;

    public static class Node {
        public int data;
        public Node next = null;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public Node add(int data) {
        Node cursor = head;
        Node newNode = new Node(data, null);
        if (cursor == null) {
            head = newNode;
            return head;
        }

        while (cursor.next != null) {
            cursor = cursor.next;
        }

        cursor.next = newNode;
        return head;
    }

    public void delete(int data) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.data == data) {
                if (previous != null) {
                    previous.next = current.next;
                } else {
                    head = head.next;
                }
                current = current.next;
            } else {
                previous = current;
                current = current.next;
            }
        }
    }


    public void print() {
        Node cursor = head;
        while (cursor != null) {
            System.out.print(" " + cursor.data + " -> ");
            cursor = cursor.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Add to linked list
        MyLinkedList list = new MyLinkedList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.print();
        list.delete(1);
        list.print();


    }

}

