package com.leetcode2022.datastructures;

public class MyLinkedList {
    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head = null;

    public Node append(int data) {
        Node newNode = new Node(data, null);
        if (head == null) {
            head = newNode;
            return head;
        } else {
            Node pointer = head;
            while (pointer.next != null)
                pointer = pointer.next;
            pointer.next = newNode;
            return newNode;
        }
    }

    public void reverse() {
        Node reverse = null; // keep the reversed list
        Node cursor = head; // cursor
        Node front = null; // remaining list temp
        while (cursor != null) {
            front = cursor.next;  // save the forward next list into temp
            cursor.next = reverse; // break the forward link and add the current link to reversed linkedList
            reverse = cursor; // update the reverse head
            cursor = front; // move the cursor to the next element
        }
        head = reverse;
    }

    public int findKthElementFromLast(int k) {
        if (k < 1 || head == null) {
            return -1;
        }

        Node front = head;
        Node back = head;
        // make distance between front and back equals to k
        for (int i = 0; i < k; i++) {
            if (front != null) {
                front = front.next;
            } else {
                return -1;
            }
        }

        while (front != null) {
            front = front.next;
            back = back.next;
        }

        return back.data;
    }

    public void deleteAll(int data) {
        Node front = head;
        Node back = null;

        while (front != null) {
            if (front.data == data) {
                // case of 1 node matching data
                if (back != null) {
                    back.next = front.next;
                } else {
                    head = head.next;
                }
                front = front.next;
            } else {
                back = front;
                front = front.next;
            }

        }
    }

    public void print() {
        Node pointer = head;
        System.out.println("------------------------");
        while (pointer != null) {
            System.out.println(pointer.data);
            pointer = pointer.next;
        }
        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        MyLinkedList myList = new MyLinkedList();
        myList.append(1);
        myList.append(2);
        myList.append(3);
        myList.append(2);
        myList.append(4);

        // test print
        //myList.print();

        // test delete
        // myList.deleteAll(2);
        //myList.deleteAll(3);
        // myList.deleteAll(4);
        // myList.deleteAll(1);
        // myList.print();

        //test reverse
        myList.reverse();
        myList.print();
        //System.out.println(myList.findKthElementFromLast(2));

    }


}


