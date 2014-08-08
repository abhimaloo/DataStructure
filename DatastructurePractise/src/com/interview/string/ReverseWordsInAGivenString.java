package com.interview.string;

/**
 * Example: Let the input string be “i like this program very much”.
 * The function should change the string to “much very program this like i”
 *
 * http://www.geeksforgeeks.org/reverse-words-in-a-given-string/
 * Created by abhimaloo on 8/7/14.
 */
public class ReverseWordsInAGivenString {
    public static String input = "i like this program very much";

    public static String reverseWords(String input) {
        //first reverse the string
        char[] buff = input.toCharArray();
        int left = 0;
        int right = buff.length-1;
        while(left<right) {
            char temp = buff[right];
            buff[right] = buff[left];
            buff[left] = temp;
            right--;
            left++;
        }

        //now traverse a word and reverse it
        left =0;
        while(true) {
            right = left;
            //move right to the space
            for(;right<input.length() && buff[right]!=' '; right ++) {
                continue;
            }
            //save the space as pivot
            int pivot = right;
            //adjust the right to a character before space
            right = right-1;
            //reverse the string from left to right
            while(left<right){
                char temp = buff[right];
                buff[right] = buff[left];
                buff[left] = temp;
                right--;
                left++;
            }
            //check if pivot has reached or exceeded the string length
            if(pivot>=input.length()-1){
                break;
            } else {
                // take the left index to the next letter
               for(left=pivot; left<input.length() && buff[left] == ' '; left++){
                   continue;
               }
            }

        }

        return String.valueOf(buff);
    }

    public static void main(String[] args) {
        System.out.println(reverseWords(input));
    }

}
