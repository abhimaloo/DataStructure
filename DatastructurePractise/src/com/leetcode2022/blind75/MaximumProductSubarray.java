package com.leetcode2022.blind75;

/*
    Given an integer array nums, find a
subarray
 that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.



Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.


Constraints:

1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

solution hints:
- Unlike sum, a negative total sum does not indicate the exit condition; in case of product; we should maintain
for a subarray ending at i, the max product subarray would be one of the following -
i. nums[i] is the maximum number
ii. max[i-1] * nums[i] (for positive values)
iii. min[i-1] * nums[i] (for negative value)

 */
public class MaximumProductSubarray {
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int maxProduct = nums[0];
        int minEndingHere = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // negative case
            if (nums[i] < 0) {
                //swap max with min
                int temp = minEndingHere;
                minEndingHere = maxEndingHere;
                maxEndingHere = temp;
            }
            // all positive value cases
            maxEndingHere = Math.max(maxEndingHere * nums[i], nums[i]);
            minEndingHere = Math.min(minEndingHere * nums[i], nums[i]);
            maxProduct = Math.max(maxEndingHere, maxProduct);
        }

        return maxProduct;
    }
}
