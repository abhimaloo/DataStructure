package com.crackCode.arraysAndstrings;

/**
 * Write a method to replace all spaces in a string with ‘%20’
 * Created by abhimaloo on 9/13/14.
 */
public class ReplaceAllSpaces {

    public static String s = "Abhi Is Dumb";

    public static String replaceSpaces(String s) {
        // count all the spaces first
        int spaceCount = 0;

        for(int i= 0 ; i< s.length(); i++) {
            if(s.charAt(i) == ' ') {
                spaceCount ++;
            }
        }

        char[] s2 = new char[s.length() + 2*spaceCount];
        int j = 0;
        for( int i = 0; i< s.length(); i++) {
            if(s.charAt(i) == ' '){
                s2[j++] = '%';
                s2[j++] = '2';
                s2[j++] = '0';
            } else{
                s2[j++] = s.charAt(i);
            }
        }

        return new String(s2);

    }


    public static void main(String[] args) {
        System.out.println(replaceSpaces(s));
    }
}
