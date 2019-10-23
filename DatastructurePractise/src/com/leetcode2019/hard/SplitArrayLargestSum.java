package com.leetcode2019.hard;

/*
https://leetcode.com/problems/split-array-largest-sum/
Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
 */
public class SplitArrayLargestSum {
    /*
    Trick here is to understand the problem more closely
    min(largestSum while dividing the array into m parts)
    so the largest Sum ranges are -
    largest - Sum(all elements) where m = 1 ( which means do not even divide)
    smalles - max(allElements) this happens when we do n splits where n is size of array itself

    Now we need to apply binarySearch to minimize the sum with m splits
     */
    public static int splitArray(int[] nums, int m) {
        int low = 0;
        int high = 0;
        for (int num : nums) {
            low = Math.max(low, num);   // find max element
            high += num;      // find sum of all the elements
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            int chunks = split(nums, mid);
            if (chunks > m) {  // splits are too many which means sum is less; hence search into right half
                low = mid + 1;
            } else {
                high = mid;  // it measn sum is too high ;hence search into left half.
            }
        }

        return low;
    }

    /*
        this method is responsible for finding how many pieces do we need to break the array into so that targetSum can be achived
        We are trying to group the arrays into subsets whose sum is < targetSum. trying to find how many such subarrays can be made
     */
    private static int split(int[] nums, int targetSum) {
        int pieces = 1;  // start the first piece ( probably the complete array at once) .. if we find the sum has exhausted we can break it into pieces
        int sum = 0;
        for (int num : nums) {
            if (sum + num > targetSum) {  // cumulative sum with this newer num will go above the limit;
                pieces++;  //hence increase the split/chunk count
                sum = num;  //start this newer chuck with "num" as sum
            } else {
                sum += num;  // else keep maintaining cumulative sum
            }
        }

        return pieces;
    }

    public static void main(String[] args) {
        System.out.println(splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }
}
