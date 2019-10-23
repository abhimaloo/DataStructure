package com.leetcode2019.medium;

/*
https://leetcode.com/problems/copy-list-with-random-pointer/
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.



Example 1:



Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.


Note:

You must return the copy of the given head as a reference to the cloned list.

 */
public class CopyListRandomPointer {
    public static RandomNode copyRandomList(RandomNode head) {
        RandomNode cursor = head;
        RandomNode clone = null;
        if (head == null) return null;
        // step 1 interleave the linkedList
        while (cursor != null) {
            RandomNode newNode = new RandomNode();
            newNode.val = cursor.val * 10;
            newNode.next = cursor.next;
            cursor.next = newNode;
            cursor = newNode.next;
        }

        clone = head.next;
        cursor = head;
        // step 2 point random pointers
        while (cursor != null) {
            if (cursor.random != null) {
                cursor.next.random = cursor.random.next;
            }
            cursor = cursor.next.next;
        }

        // step 3 break the interleaving
        cursor = head;
        while (cursor != null && cursor.next != null) {
            RandomNode nextNode = cursor.next;
            cursor.next = nextNode.next;
            cursor = nextNode;
        }

        return clone;
    }

    public static void main(String[] args) {
        RandomNode node1 = new RandomNode(1);
        RandomNode node2 = new RandomNode(2);
        node1.next = node2;
        node2.next = null;
        node1.random = node2;
        node2.random = node2;
        RandomNode clone = copyRandomList(node1);
    }
}

class RandomNode {
    public int val;
    public RandomNode next;
    public RandomNode random;

    public RandomNode() {
    }

    public RandomNode(int _val) {
        val = _val;
    }

    public RandomNode setNext(RandomNode next) {
        this.next = next;
        return this;
    }

    public RandomNode setRandom(RandomNode random) {
        this.random = random;
        return this;
    }
};
