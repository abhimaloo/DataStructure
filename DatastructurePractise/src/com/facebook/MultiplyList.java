package com.facebook;

import com.crackCode.linkedlist.LinkedList;
import com.crackCode.linkedlist.Node;

/**
 * Created by abhimaloo on 9/26/14.
 */
public class MultiplyList {
    public static int multiply(Node<Integer> head1, Node<Integer> head2) {

        int returnValue = 0;
        int currentValue = 0;
        Node<Integer> cursor1 = head1;
        Node<Integer> cursor2 = head2;
        int carry = 0;

        while(cursor2!= null) {
            returnValue *= 10;
            while(cursor1!= null) {

                int temp = cursor1.data * cursor2.data;
                if(temp > 9) {
                   currentValue += (temp/10 + carry)*10;
                   carry = temp % 10;
                } else {
                   currentValue += temp %10 + carry;
                    carry = 0;
                }

                currentValue *= 10;
                cursor1 = cursor1.next;
            }

            cursor1 = head1;
            currentValue /= 10;
            currentValue += carry;

            returnValue += currentValue;
            currentValue = 0;
            carry = 0;
            cursor2 = cursor2.next;

        }

        return returnValue;

    }


    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.insert(3);
        list1.insert(2);
        list1.insert(3);

        LinkedList<Integer> list2 = new LinkedList<>();
        list2.insert(4);
        list2.insert(1);
        list2.insert(4);

        System.out.println(multiply(list1.head, list2.head) + ":"+ 123*213);

    }

}
