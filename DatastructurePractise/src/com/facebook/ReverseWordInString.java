package com.facebook;

/**
 * Created by abhimaloo on 9/26/14.
 */
public class ReverseWordInString {

    public static String s = "My Name Is Abhishek";

    public static String reverseWords(char[] s) {
        if(s == null || s.length == 0){
            return null;
        }
        //step one is to reverse the string
        reverse(s, 0, s.length -1);
        int start = 0;
        int end = 0;
        while(end < s.length && start < s.length) {

            // move to the first character of the word by skipping whitespaces
            while(start < s.length && s[start] == ' '){
                start++;
            }

            end = start;
            while(end+1 < s.length && s[end+1] != ' '){
                end ++;
            }

            // found the word hence reverse it
            reverse(s, start, end);
            start = end+1;
        }

        return new String(s);
    }


    private static void reverse(char[] s, int left, int right) {

        while(left < right){
            char temp  = s[left];
            s[left]  = s[right];
            s[right] = temp;
            left ++;
            right --;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverseWords(s.toCharArray()));
    }

}
