package com.leetcode2019.easy;

/*
https://leetcode.com/problems/plus-one/
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        if (digits[digits.length - 1] < 9) {
            digits[digits.length - 1]++;
            return digits;
        }

        for (int i = digits.length - 1; i >= 0; i--) {
            int incr = digits[i] + carry;
            carry = 0;
            if (incr > 9) {
                digits[i] = 0;
                carry = 1;
            } else {
                digits[i] = incr;
            }
        }

        if (carry == 1) {
            int[] newArray = new int[digits.length + 1];
            newArray[0] = 1;
            int index = 1;
            for (int num : digits) {
                newArray[index++] = num;
            }
            return newArray;
        }

        return digits;
    }
}
