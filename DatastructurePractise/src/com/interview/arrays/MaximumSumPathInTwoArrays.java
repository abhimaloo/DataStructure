package com.interview.arrays;

/**
 * Given two sorted arrays such the arrays may have some common elements.
 * Find the sum of the maximum sum path to reach from beginning of any array to end of any of the two arrays.
 * We can switch from one array to another array only at common elements.
 *
 * ex - Input:  ar1[] = {2, 3, 7, 10, 12}, ar2[] = {1, 5, 7, 8}
 * Output: 35 ,35 is sum of 1 + 5 + 7 + 10 + 12
 * Created by abhishekm787 on 7/18/14.
 */
public class MaximumSumPathInTwoArrays {


    /**
     * trick is to use subroutine like merge() of merge sort
     * try to keep the sum till you find a common point.
     * the way to decide which array index to expand is by exploiting the property of sortedness
     * check if s[i] < s[j], if its true that means common point is somewhere ahead in s1, hence increase i
     * similarly if s[i] > s[j], advance the j..
     * if s[i]==s[j], it means you reached the common point, pick the max sum till this point and
     * reset all the counters
     * compare i and j element of s1 and s2 ..
     * @param s1
     * @param s2
     * @return
     */
    public static int findMaxPathSum(int[] s1, int[] s2) {
        int i = 0 ;
        int j = 0;
        int maxSum1 = 0;
        int maxSum2 = 0;
        int maxResultSum = 0;

        while(true){
            if(i == s1.length || j == s2.length){
                break;
            }

            if(s1[i] < s2[j]){
                maxSum1 += s1[i++];

            } else if(s1[i] > s2[j]){
                maxSum2 += s2[j++];

            } else {
                maxSum1 += s1[i++];
                maxSum2 += s2[j++];
                maxResultSum += Math.max(maxSum1,maxSum2);
                maxSum1 = 0;
                maxSum2 = 0;
            }
        }

        while(i!=s1.length){
            maxResultSum += s1[i++];
        }

        while(j!=s2.length){
           maxResultSum += s2[j++];
        }


        return maxResultSum;
    }

    public static void main(String[] args) {

        System.out.println(findMaxPathSum(new int[]{2, 3, 7, 10, 12}, new int[]{1, 5, 7, 8}));
    }
}
