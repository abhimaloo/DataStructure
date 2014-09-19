package com.revision;

/**
 * Created by abhimaloo on 9/18/14.
 */
public class ReverseWordsOfSentence {
    public static String s = "My Name    is Abhishek";

    public static String reverseWords(char[] s) {

        reverseSentence(s, 0, s.length - 1);
        int start = 0;
        int end = 0;
        while(start <s.length && end < s.length) {

            while(start < s.length && s[start] == ' ') {
                start ++;
            }
            end = start;
            while(end < s.length && s[end] != ' '){
                end ++;
            }
            end --;
            reverseSentence(s, start, end);
            start = end +1;
        }

        return new String(s);

    }

    private static void reverseSentence(char[] s, int start, int end) {
        while(start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end --;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverseWords(s.toCharArray()) ) ;
    }

}
