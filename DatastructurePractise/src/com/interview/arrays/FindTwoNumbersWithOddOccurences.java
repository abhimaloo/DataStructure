package com.interview.arrays;

/**
 * Given an unsorted array that contains even number of occurrences for all numbers except two numbers
 * Input: {12, 23, 34, 12, 12, 23, 12, 45}
 Output: 34 and 45
 * Created by abhimaloo on 7/24/14.
 */
public class FindTwoNumbersWithOddOccurences {

    public static int[] input = {12, 23, 34, 12, 12, 23, 12, 45, 45, 12};

    /**
     * basic trick  -
     * a) a xor a = 0
     * b) a xor 0 = a
     *
     * xor all the elements of the array. Final result will contain the xor of odd elements
     * since even occurence will cancel themselves(x^x =0). We will receive the result which is xor of
     * both the element with odd occurences.
     * let xor be 5 which means "0101" in binary. every 1 in the binary representation explains
     * that bit in x and y were not same on that position.
     *
     * We can exploit this thing by creating two groups of xor's one which has this bit set , other which does not have it set
     * value of both of these groups will give us the two numbers with odd occurences
     * @param arr
     */
    public static void recoverOddOccurring(int [] arr)
    {
        // basic error checking.
        int xor=arr[0];
        int x = 0, y = 0;
        // run xor on all the elements
        for(int i=1;i<arr.length;i++)
        {
            xor^=arr[i];
        }

        //find out the right most bit which is set in the result
        int right_most_set_bit= xor & ~(xor-1);

        // now iterate through the array and build groups
        for(int i=0;i<arr.length;i++)
        {
            // do a bitwise AND with every element, if same bit is set, result would be the integer containing only that bit
            if ((arr[i] & right_most_set_bit) == right_most_set_bit)
                x^=arr[i];
            else
                y^=arr[i];
        }

        System.out.println(" x: "+x+ ", y: "+y);

    }

    public static void main(String[] args) {
        recoverOddOccurring(input);
    }
}
