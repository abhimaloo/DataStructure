package com.crackCode.linkedlist;

/**
 * Created by abhimaloo on 9/14/14.
 */
public class LinkedList<T> {
    public Node<T> head = null;

    public void insert(T data) {

        Node<T> newNode = new Node<>(data, null);
        if(head == null) {
            head = newNode;
            return;
        }
        Node<T> cursor = head;
        while(cursor.next != null) {
            cursor = cursor.next;
        }

        cursor.next = newNode;

    }

    public void removeDuplicate() {
       Node<T> forward = head;

       while(forward != null) {

           Node<T> reaper = head;
           Node<T> prev = null;
           while(reaper != forward) {
               if(reaper.data.equals(forward.data)) {
                   if(prev == null) {
                       head = head.next;
                   } else {
                       prev.next = reaper.next;
                   }
                   reaper = reaper.next;
               } else {
                   prev = reaper;
                   reaper = reaper.next;
               }

           }

           forward = forward.next;
       }

    }

    public Node<T> findKthFromLastElement(int k) {
        Node<T> forward = head ;
        for( int i = 0; i < k-1; i++) {
            if(forward.next != null) {
                forward = forward.next;
            } else {
                return null; // error condition
            }

        }

        Node<T> target = head;
        while(forward.next != null) {
            forward = forward.next;
            target = target.next;
        }

        return target;

    }


    public void makeCircular(Node<T> target) {

        Node<T> cursor = head;
        while(cursor.next != null) {
            cursor = cursor.next;
        }
        cursor.next = target;
    }


    public Node<T> findCycleStart() {

        Node slow = head;
        Node fast = head;

        do {
          if(slow.next!=null) {
              slow = slow.next;
          } else {
              return null;
          }

          if(fast.next != null && fast.next.next!=null) {
              fast  =fast.next.next;
          } else {
              return null;
          }

        } while(slow != fast) ;

        Node cursor = head;
        while(slow != cursor) {
            if(slow.next != null) {
                slow = slow.next;
            } else {
                return null;
            }

            if(cursor.next != null) {
                cursor = cursor.next;
            } else {
                return null;
            }
        }

        return cursor;


    }



    public void deleteNode(Node<T> delete) {
        Node<T> forward = delete;
        Node<T> prev = null;

        while(forward.next != null) {
            forward.data = forward.next.data;
            prev = forward;
            forward = forward.next;
        }
        prev.next = null;

    }


    public void print() {
        Node<T> cursor = head;
        while(cursor!=null) {
            System.out.println(cursor.data.toString());
            cursor = cursor.next;
        }
    }



    public static void main(String[] args) {
        LinkedList<Character> list = new LinkedList<>();
        list.insert('F');
        list.insert('O');
        list.insert('L');
        list.insert('L');
        list.insert('O');
        list.insert('W');
        list.insert(' ');
        list.insert('U');
        list.insert('P');
        //list.print();
        //list.removeDuplicate();
        //list.print();

        list.makeCircular(list.findKthFromLastElement(7));
        System.out.println(list.findCycleStart().data.toString());




    }

}
