package com.interview.linkedlist;

/**
 * Create a double linked list
 * Created by abhishekm787 on 7/29/14.
 */
public class DoublyLinkedList<T extends Comparable<T>> {
    protected DoublyNode<T> head;
    protected DoublyNode<T> tail;
    protected int size = 0;

    public DoublyNode<T> insert(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data,null,null);

        if(head ==null){
            head = newNode;
            tail = newNode;
            size++;
            return newNode;
        }

        DoublyNode<T> cursor = tail;
        cursor.next = newNode;
        newNode.previous = cursor;
        tail = newNode;

        size ++;
        return newNode;
    }

    public DoublyNode<T>  insertAtFront(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data,null,null);
        if(head ==null){
            head = newNode;
            tail = newNode;
            size++;
            return newNode;
        }

        head.previous = newNode;
        newNode.next = head;
        head = newNode;

        size ++;
        return newNode;
    }

    public void print(){
        DoublyNode<T> cursor = head;
        while(cursor!= null) {
            System.out.println(cursor.data);
            cursor = cursor.next;
        }
    }

    public void printReverse() {
        DoublyNode<T> cursor = tail;
        while(cursor!= null) {
            System.out.println(cursor.data);
            cursor = cursor.previous;
        }
    }

    public DoublyNode<T> removeLast() {
        DoublyNode<T> tobeRemoved = tail;
        tail.previous.next = null;
        tail = tail.previous;
        size --;
        return tobeRemoved;
    }

    public DoublyNode<T> moveToFront(DoublyNode<T> node) {
      if(node == head){
          return head;
      }

      if(node.next!=null) {
          //if node is a middle node
          node.previous.next = node.next;
          node.next.previous = node.previous;
      } else{
         //if the node is tail node
          node.previous.next = null;
          tail = node.previous;
      }

      node.previous = null;
      node.next = head;
      head = node;
      return head;
    }

    public DoublyNode<T> remove(T data) {
        DoublyNode<T> cursor = head;
        while(cursor != null && cursor.data.compareTo(data) !=0) {
            cursor = cursor.next;
        }
        //now delete the node
        //handle base cases
        DoublyNode<T> toBeRemoved = cursor;
        if(cursor == head) {
            cursor.next.previous = null;
            head = head.next;
        } else if(cursor == tail && head != tail){
             cursor.previous.next = null;
             tail = tail.previous;
        } else {
            //for middle nodes
            cursor.previous.next = cursor.next;
            cursor.next.previous = cursor.previous;
        }
        size --;
        return toBeRemoved;
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> doublyList = new DoublyLinkedList<>();
        doublyList.insert(1);
        doublyList.insert(2);
        doublyList.insert(3);
        doublyList.insert(4);
        doublyList.insert(5);
        doublyList.insertAtFront(6);
        doublyList.removeLast();
        doublyList.print();
        System.out.println("-----------");


    }


}
