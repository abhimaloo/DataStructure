package com.maloo.linkedlist;


public class LinkedList {

    public Node head;

    public void insert(int data){
        Node newNode = new Node(data,null);

        if(head == null){
            head = newNode;
            return;
        }

        Node cursor = head;
        // go to the last node
        //its the pointer issue
        while(cursor.next!=null){
            cursor = cursor.next;
        }
        // Set the new node on the next of last node
        cursor.next = newNode;

    }

    /**
     * remove only one instance of data
     * @param data
     */
    public void remove(int data) {

        Node backward = null;
        Node forward = head;

        // two pointers backward and forward
        for(;forward!=null; backward = forward, forward=forward.next ){
            if(forward.data == data){
                // deletion at the middle or end
                if(backward !=null ){
                    backward.next = forward.next;
                } else {
                    //this is to cover first node data found
                    head = forward.next;
                }
                break;
            }
        }

    }

    /**
     * Approach is to move the cursor from left to right one by one and delete
     * delete any occurrences of it from the left of it
     */
    public void removeDuplicates(){
        Node cursor = head;

        while(cursor!=null){
            Node janitorPrev = null;
            Node janitor = head;
            //scan from left to right till cursor
            while(janitor!=cursor){
                 // if left pointer data matches cursor's data
                 if(janitor.data == cursor.data){
                     //if janitor is not at head
                    if(janitorPrev !=null){
                        // delete the node
                        janitorPrev.next = janitor.next;
                    } else {
                        // fix for first element as duplicate element
                        head = janitor.next;
                    }
                    break;
                 }
                janitorPrev = janitor;
                janitor = janitor.next;

            }
            cursor = cursor.next;
        }
    }


    public int findMidPoint(){
        Node tortoise = head;
        Node hare = head;
        // this is special for mid point since hare has to reach only till the last node
        while(hare.next!=null){
            tortoise = tortoise.next;
            hare = hare.next;
            if(hare!=null){
                hare = hare.next;
            }
        }

        return tortoise.data;

    }

    /**
     * Iterative approach for reversing a linked list
     * Very Easy
     */
    public void reverse(){

        Node temp = null;
        Node previous = null;
        Node cursor = head;

        //Work on one node at a time
        while(cursor!=null) {
           // temporarily save the next nodes of the current node
           temp = cursor.next;
           // set the previous (already reversed List) to the next of current node
           cursor.next = previous;
            // make the current node previous
           previous = cursor;
            // reload the next nodes from temp storage
           cursor = temp;
        }

        // previous contains all the nodes in reversed manner
        head = previous;
    }

    /**
     * Small method to create a loop
     * @param joinPoint
     */
    public void makeCircular(int joinPoint){
        Node anchor = head;
        Node target = head;

        while(target.next!=null){
            target = target.next;
            if(anchor.data != joinPoint){
                anchor = anchor.next;
            }
        }

        target.next = anchor;
    }

    /**
     * This algorithm finds the loop in the linked list
     * @return
     */
    public boolean findLoop(){
        Node tortoise = head;
        Node hare = head;

        while(hare!=null){
            tortoise = tortoise.next;
            hare = hare.next;
            if(hare!=null){
                hare = hare.next;
            }

            if(hare == tortoise){
                return true;
            }
        }

        return false;
    }

    /**
     * This algorithm finds the loop head
     * @return
     */
    public int findLoopHead(){
        Node tortoise = head;
        Node hare = head;

        while(hare!=null){
            tortoise = tortoise.next;
            hare = hare.next;
            if(hare!=null){
                hare = hare.next;
            }

            if(hare == tortoise){
                break;
            }
        }

        Node loopHeadFounder = head;

        while(loopHeadFounder!=null){
            if(hare==loopHeadFounder){
                return hare.data ;
            }
            loopHeadFounder = loopHeadFounder.next;
            hare = hare.next;
        }

        return -1;
    }


    public Node findKthElementFromLast(int k){
        Node back = head;
        Node forward = head;
        //move the forward node k-1 time
        for(;k>1;k--){
           if(forward!=null && forward.next!=null){
               forward = forward.next;
           }
        }
        // Now move one step each pointer at at time
        while(forward!=null && forward.next!=null){
            forward = forward.next;
            back = back.next;
        }

        return back;
    }

    public void print(){
        Node cursor = head;
        while(cursor!=null){
            System.out.println(cursor.data);
            cursor =cursor.next;
        }
    }

    public static class Node{
        public int data;
        public Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static LinkedList sortedMerge(LinkedList list1, LinkedList list2){
        LinkedList mergedResult = new LinkedList();
        Node list1cursor = list1.head;
        Node list2cursor = list2.head;

        while(list1cursor!=null && list2cursor!=null){

            if(list1cursor.data<list2cursor.data){
              mergedResult.insert(list1cursor.data);
              list1cursor = list1cursor.next;
            } else {
              mergedResult.insert(list2cursor.data);
              list2cursor = list2cursor.next;
            }
        }

        while(list1cursor!=null){
            mergedResult.insert(list1cursor.data);
            list1cursor = list1cursor.next;
        }

        while(list2cursor!=null){
            mergedResult.insert(list2cursor.data);
            list2cursor = list2cursor.next;
        }

        return mergedResult;
    }

    public static void main(String[] args) {
        LinkedList myList = new LinkedList();
        myList.insert(1);
        myList.insert(3);
        myList.insert(5);

        LinkedList myList2 = new LinkedList();
        myList2.insert(2);
        myList2.insert(4);
        myList2.insert(6);

        sortedMerge(myList,myList2).print();




    }
}
