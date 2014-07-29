package com.interview.linkedlist;

/**
 * Created by abhishekm787 on 7/28/14.
 */
public class MyLinkedList {
    public Node head;

    public void insert(int data){
        Node cursor = head ;
        Node newNode = new Node(data,null);
        if(cursor == null){
            head = newNode;
            return;
        }

        while(cursor.next!=null){
            cursor = cursor.next;
        }
        cursor.next = newNode;

    }

    /**
     * please remember this version..
     * take care of the first node case
     * @param data
     */
    public void remove(int data){
        Node current = head;
        Node previous = null;

        while(current!=null){
            if(current.data == data) {
                if(previous !=null) {
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

    /**
     * main logic for removing duplicates is
     * start from index n(n is 2 ...n) element
     * keep removing elements form 1 to n-1 if any of them has the same data
     */
    public void removeDuplicates(){
        Node cursor = head.next;
        while(cursor!=null){
            Node current  = head;
            Node previous = null;

            //remove logic starting from left to right
            while(current!=cursor){
                if(current.data == cursor.data){
                    if(previous!=null){
                        previous.next = current.next;
                    } else{
                        head = head.next;
                    }
                    current = current.next;
                } else {
                    previous = current;
                    current = current.next;
                }
            }

            cursor = cursor.next;
        }

    }

    /**
     * always use this method to reverse
     */
    public void reverse(){
        Node current = head;
        Node reverse = null;

        while(current!=null){
           Node temp = current.next;
           current.next = reverse;
           reverse = current;
           current = temp;
        }
        head = reverse;

    }


    public void print(){
        Node cursor = head;
        while(cursor!=null){
            System.out.println(cursor.data);
            cursor = cursor.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);

        list.print();

        list.reverse();


    }
}
