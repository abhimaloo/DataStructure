package com.interview.arrays;

/**
 * you have an array of numbers from 0 to N-1,
 * one of the numbers is removed, and replaced with a number already in the array which makes a duplicate of that number.
 * How can we detect this duplicate in time O(N)
 *
 * Ex - [0, 1, 1, 2, 3, 5]
 * 1 is repeated and 4 is missing
 * Created by abhimaloo on 7/25/14.
 */
public class FindDuplicateNumberInArrayWithRangeN {
    public static int [] input = {0, 1, 1, 2, 3, 5};

    /** let x be the duplicate number and y be the missing number
     * T(s) = sum + x -y
     * x-y = T(s) - sum
     * x-y = n(n-1)/2 - sum    ....eq 1
     * let x-y be value a
     *
     *
     * T(s^2) = sumSquare +x^2 -y^2
     * (x+y)(x-y)  = n(n-1)(2n-1)/6 - sumSquare   ..eq 2
     *
     *
     * replacing x-y from 1 into 2
     * x+y = (n(n-1)(2n-1)/6 - sumSqaure)/a   .. eq 3
     *
     * let x+y value be b
     *
     * now by adding eq1 and eq 3
     *
     * x = a+b/2
     *
     * y = a-b/2
     *
     *
     *
     * @param input
     */
    public static void findDuplicateAndMissingNumber(int[] input){

        int sum =0;
        int sumOfSQuare = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
            sumOfSQuare += input[i] * input[i];
        }

        //arith sum of range n is n(n-1)/2
        int arithSum = (input.length)* (input.length-1)/2;

        //arith sum of squares are n(n-1)(2n-1)/6
        int arithSquareSum = (input.length)* (input.length-1)*(2* input.length -1)/6;

        int diffSum = arithSum -sum;
        int diffSquare = (arithSquareSum-sumOfSQuare)/diffSum;

        System.out.println("missing number is :"+ (diffSum+diffSquare)/2);
        System.out.println("duplicate number is :"+ (diffSquare-diffSum)/2);

    }

    public static void main(String[] args) {
        findDuplicateAndMissingNumber(input);
    }


}
