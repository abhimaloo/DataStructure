package com.interview.linkedlist;

/**
 * Given two numbers represented by two linked lists, write a function that returns sum list.
 *
 * Input:
 * First List: 5->6->3  // represents number 563
 * Second List: 8->4->2 //  represents number 842
 *
 * Created by abhishekm787 on 7/29/14.
 */
public class AddTwoNumbersUsingLinkedList {

    /**
     * Trick is very easy.. reverse both the numbers and then add them the way you add two numbers
     * keep a running carry variable. do check whether the carry is nonZero after the iteration
     * because carry can also create a node
     * @param list1
     * @param list2
     * @return
     */
    public static MyLinkedList add(MyLinkedList list1, MyLinkedList list2){
        //first reverse both the list, so that unit place can come to head
        list1.reverse();
        list2.reverse();
        MyLinkedList resposne = new MyLinkedList();
        int carry = 0;
        Node cursor1 = list1.head;
        Node cursor2 = list2.head;
        while(cursor1 != null || cursor2 != null) {

            int sum  = carry ;
            if(cursor1!=null){
                sum += cursor1.data;
                cursor1 = cursor1.next;
            }

            if(cursor2!=null) {
                sum += cursor2.data;
                cursor2 = cursor2.next;
            }

            carry = sum / 10;
            resposne.insert(sum % 10);

        }
        //very important to remember
        if(carry!=0){
            resposne.insert(carry);
        }

        resposne.reverse();
        return resposne;
    }


    public static void main(String[] args) {
        MyLinkedList list1 = new MyLinkedList();
        list1.insert(3);
        list1.insert(2);
        list1.insert(1);

        MyLinkedList list2 = new MyLinkedList();

        list2.insert(9);
        //list2.insert(9);
        //list2.insert(9);

        add(list1,list2).print();
    }
}
