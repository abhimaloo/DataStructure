package com.leetcode2019.easy;

/*
https://leetcode.com/problems/sort-array-by-parity/
 */
public class SortByParity {
    public static int[] sortArrayByParity(int[] A) {
        int index = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                int temp = A[index];
                A[index++] = A[i];
                A[i] = temp;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[] res = sortArrayByParity(new int[]{3, 1, 2, 4});
    }
}
