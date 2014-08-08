package com.interview.string;

/**
 * Given a string s1 and a string s2, write a snippet to say whether s2 is a rotation of s1 using only one call to strstr routine?
 (eg given s1 = ABCD and s2 = CDAB, return true, given s1 = ABCD, and s2 = ACBD , return false)

 http://www.geeksforgeeks.org/a-program-to-check-if-strings-are-rotations-of-each-other-or-not/
 * Created by abhimaloo on 8/7/14.
 */
public class CheckIfStringIsARotation {

    public static String text = "ABCD";
    public static String rotation = "CDAB";

    public static boolean isRotation(String text, String rotation) {
        String buffer = rotation + rotation;
        //now check wthere text is a substring of rotation or not
        //try naive pattern search
        for( int i=0; i< buffer.length(); i++) {
            int j = 0;
            for (; j<text.length(); j++) {
               if(buffer.charAt(i+j) != text.charAt(j)){
                   break;
               }
            }
            if (j == text.length()) {
                return true;
            }

        }

        return false;
    }

    public static void main(String[] args) {

        System.out.println(isRotation(text, rotation));
    }



}
