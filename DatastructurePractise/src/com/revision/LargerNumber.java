package com.revision;

/**
 * Given a number whose digits are unique, find the next larger number that can be formed with those digits.
 *
 * Solution - http://stackoverflow.com/questions/9368205/given-a-number-find-the-next-higher-number-which-has-the-exact-same-set-of-digi
 * Created by abhimaloo on 9/17/14.
 */
public class LargerNumber {

    public static String largerNumber(char[] s) {

        for( int i = s.length -2; i>=0; i--) {

            // we found the ith element which is in decreasing order starting from right to left
            if(s[i] < s[i+1]) {
                // find the element to the right of i which is greater than ith element but smallest among all greater element
                int swapIndex = -1;
                for(int j = i+1; j < s.length; j++) {
                    if(s[j] > s[i]) {
                        swapIndex = j;
                    }
                }

                // swap the element with the ith element
                swap(s,i,swapIndex);

                // reverse the array after position i to end
                int left = i+1;
                int right = s.length -1;
                while(left<right) {
                    swap(s, left, right);
                    left++;
                    right--;
                }
                break;
            }

        }

        return new String(s);

    }


    public static void swap(char[] s, int leftIndex, int rightIndex){
        char temp = s[leftIndex];
        s[leftIndex] = s[rightIndex];
        s[rightIndex] = temp;
    }


    public static void main(String[] args) {
        String num = "9123";
        System.out.println(largerNumber(num.toCharArray()));
    }
}
