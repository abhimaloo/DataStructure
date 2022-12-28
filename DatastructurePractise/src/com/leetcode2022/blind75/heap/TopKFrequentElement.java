package com.leetcode2022.blind75.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.



Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]


Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.
 */
public class TopKFrequentElement {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyCount = new HashMap<>();
        int[] response = new int[k];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt((o) -> frequencyCount.get(o)));
        for (int num : nums) {
            Integer freq = frequencyCount.getOrDefault(num, 0);
            frequencyCount.put(num, freq + 1);
        }

        for (Map.Entry<Integer, Integer> entry : frequencyCount.entrySet()) {
            minHeap.offer(entry.getKey());
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            response[i] = minHeap.poll();
        }

        return response;
    }

    public static void main(String[] args) {
        int[] response = topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
    }
}
