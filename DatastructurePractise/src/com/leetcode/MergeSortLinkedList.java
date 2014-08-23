package com.leetcode;

import com.interview.linkedlist.MyLinkedList;
import com.interview.linkedlist.Node;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * https://oj.leetcode.com/problems/sort-list/
 * Created by abhimaloo on 8/22/14.
 */
public class MergeSortLinkedList extends MyLinkedList{

    /**
     * trick is to use concept of merge sort
     * keep dividing the list into two equal parts
     * finally keep merging them together
     * @param node
     * @return
     */
    public Node sort(Node node) {
        // for 0 node or 1 node list
        if(node == null || (node!= null && node.next == null)){
            return node;
        }
        // divide the list into two parts nodes[0] contains left list while node[1] contains right List
        Node[] nodes = findBothHalves(node);
        // finally merge them togather
        return merge(sort(nodes[0]), sort(nodes[1]));
    }

    //this is a tricky subroutine
    private Node merge(Node left, Node right) {
        Node result = null;
        // base case .. if left node has become null return right node
        if(left == null) {
            return right;
        }

        // if right node has become null return left node
        if(right == null) {
            return left;
        }

        // check if left is smaller or equals to the right or not
        if(left.data <= right.data){
            // assign left to the left
            result = left;
            // find next of the left recursively ..
            // look carefully we have fed left -> next as left since left has already been part of result
            result.next = merge(left.next, right);
        } else {
            //same as counterpart
            result = right;
            result.next = merge(left, right.next);
        }

        return result;
    }

    // this subroutine finds middle point
    private Node[] findBothHalves(Node node) {
        Node[] nodes = new Node[2];
        //slow pointer
        Node slow = node;
        //fast pointer
        Node fast = node;
        // previous to slow
        Node prev = null;

        while(fast != null && fast.next != null) {
            // keep rpevious one behind slow
            prev = slow;
            // move slow by one
            slow = slow.next;
            // move fast by 2
            fast = fast.next.next;
        }

        // break the list into two .. 0 to mid -1 and mid to end
        prev.next = null;
        nodes[0] = node;
        nodes[1] = slow;
        return nodes;
    }

    public static void main(String[] args) {

        MergeSortLinkedList list = new MergeSortLinkedList();
        list.insert(5);
        list.insert(2);
        list.insert(4);
        list.insert(3);
        list.insert(1);
        list.insert(6);



        list.head = list.sort(list.head);
        list.print();
    }

}
