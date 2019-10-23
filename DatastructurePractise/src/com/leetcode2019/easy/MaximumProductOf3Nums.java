package com.leetcode2019.easy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumProductOf3Nums {
    public static int maximumProduct(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 3) return nums[0] * nums[1] * nums[2];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(3, Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(3);
        for (int num : nums) {
            if (num < 0) {
                if (maxHeap.size() < 3) {
                    maxHeap.offer(num);
                } else if (maxHeap.peek() > num) {
                    maxHeap.poll();
                    maxHeap.offer(num);
                }
            } else {
                if (minHeap.size() < 3) {
                    minHeap.offer(num);
                } else if (minHeap.peek() < num) {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
        }


        // now 3 cases arise - >
        // a) 1 or No Negative values -> find top 3 from positive (minHeap) and multiply them
        if (maxHeap.size() <= 1) {
            int result = 1;
            while (!minHeap.isEmpty()) result *= minHeap.poll();
            return result;
        } else {
            // Now 2 cases arise - >
            // a) No postive values -> then multiple all the negative ones and return
            // b) Math.max(positiveProduct, maxPositive * (minimimum 2 negatives))
            int negativeProduct = 1;
            int negativeMin = maxHeap.peek();
            while (!maxHeap.isEmpty()) {
                negativeProduct *= maxHeap.poll();
            }

            if (minHeap.isEmpty()) {
                return negativeProduct;
            } else {
                int postiveProduct = 1;
                int maxPositive = 0;
                while (!minHeap.isEmpty()) {
                    maxPositive = minHeap.poll();
                    postiveProduct *= maxPositive;
                }
                return Math.max(postiveProduct, maxPositive * (negativeProduct / negativeMin));
            }
        }
    }

    public static int maxProduct2(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num < min2) {
                min1 = min2;
                min2 = num;
            } else if (num < min1) {
                min1 = num;
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);

    }

    public static void main(String[] args) {
        System.out.println(maxProduct2(new int[]{-4, -3, -2, -1, 1, 2, 50}));
    }
}
