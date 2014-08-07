package com.interview.string;

/**
 * Given two strings str1 and str2, write a function that prints all interleavings of the given two strings.
 * You may assume that all characters in both strings are different
 *
 * Input: str1 = "AB",  str2 = "CD"
 * Output:
 * ABCD
 * ACBD
 * ACDB
 * CABD
 * CADB
 * CDAB
 *
 * http://www.geeksforgeeks.org/print-all-interleavings-of-given-two-strings/
 * Created by abhimaloo on 8/6/14.
 */
public class PrintAllInterleavingOfGivenString {
    public static String s1 = "AB";
    public static String s2 = "CD";

    /**
     * idea is to use recursion
     *
     */
    public static void printAllInterleavings(String s1, String s2, char[] interleave , int index) {
        //if both the strings are empty .. print the interleaved string since it will have some data in it
        if(s1.length()==0 && s2.length() ==0) {

            for(int i=0; i< interleave.length; i++) {
                System.out.print(interleave[i]);
            }
            System.out.println();
        }

        //if s1 is not finished
        if(s1.length() != 0) {
            //get the first char out of s1 and push it into interleave
            interleave[index] = s1.charAt(0);
            //recurse by removing the first char from s1.. and pass index+1 to the next step
            printAllInterleavings(s1.substring(1), s2,interleave,index+1);
        }

        //if s2 is not finished
        if(s2.length() != 0) {
            //get the first char out of s2 and push it to interleave
            interleave[index] = s2.charAt(0);
            //recurse by removing first char from s2 and pass index +1 to the next step
            printAllInterleavings(s1, s2.substring(1),interleave,index+1);
        }

    }

    public static void main(String[] args) {
        printAllInterleavings(s1,s2,new char[s1.length()+s2.length()],0);
    }
}
