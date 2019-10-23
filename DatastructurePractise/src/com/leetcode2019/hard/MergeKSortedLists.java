package com.leetcode2019.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    public static ListNode mergeKSortedLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        ListNode result = new ListNode(0);
        ListNode cursor = result;

        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            ListNode node = queue.remove();
            cursor.next = node;
            cursor = cursor.next;
            if (node.next != null) {
                queue.add(node.next);
            }
        }

        return result.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1).setNext(new ListNode(4).setNext(new ListNode(5)));
        lists[1] = new ListNode(1).setNext(new ListNode(3).setNext(new ListNode(3)));
        lists[2] = new ListNode(2).setNext(new ListNode(6));
        ListNode res = mergeKSortedLists(lists);

    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode setNext(ListNode next) {
        this.next = next;
        return this;
    }
}
