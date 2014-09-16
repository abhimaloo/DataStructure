package com.crackCode.arraysAndstrings;

/**
 * Implement an algorithm to determine if a string has all unique characters. What if you can not use additional data structures?
 * Created by abhimaloo on 9/13/14.
 */
public class UniqueCharacters {

    public static String s = "abhishek";

    // Assuming string only contains alphabets and all of them are lower case(or not)
    public static boolean findDuplicate(String s) {
        int mask = 0;
        for(int i=0; i< s.length(); i++) {
            int val =  s.charAt(i) - 'a';
            // logical AND of mask with 1<<val will determine if a particular bit is set or not
            if((mask & 1<<val) > 0 ) {   // left shifting one with a value means setting a particular bit
                return true;
            }
            mask = mask | 1<<val;
        }

        return false;
    }

    // Assuming String contains now special characters
    public static boolean findDuplicateII(String s) {
        boolean[] dup = new boolean[256];

        for(int i = 0; i < s.length(); i++) {
            if(dup[s.charAt(i)]) {
                return true;
            }
            dup[s.charAt(i)] = true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(findDuplicateII(s));
    }

}
