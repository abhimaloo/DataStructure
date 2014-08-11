package com.interview.string;



/**
 * Given two strings string1 and string2, find the smallest substring in string1 containing all characters of string2 efficiently.

 * For Example:

 * Input string1: “this is a test string”
 * Input string2: “tist”
 * Output string: “t stri”
 *
 * http://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
 * Created by abhishekm787 on 8/7/14.
 */
public class SmallestWindowContainingAllCharactersOfOtherString {
    public static String s1 = "this is a test string";
    public static String s2 = "tist";


    /**
     * very tricky code. difficult to understand
     * keeps two bitmaps/charmaps arrays  - one is needTofind  .. other is hasFound ..
     * needToFind will keep character/frequency map of the pattern ..while hasFound will keep char/Frequncy map
     * elements found in the main string .
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String findSmallestWindow(String s1, String s2) {
       int[] needsTofind = new int[256];
       int[] hasFound = new int[256];

       //populate needsTofind array
        for(int i=0; i< s2.length(); i++) {
            needsTofind[s2.charAt(i)]++;
        }

       int begin = 0;
       int end = 0;
       int count = 0;
       int minLength = Integer.MAX_VALUE;
       int minStart = -1;
       int minEnd = -1;
        //maintain two pointers start and end.. fix the start and keep moving end
       for(;end <s1.length(); end++) {

           //if character does not matches anyone form the pattern
           if(needsTofind[s1.charAt(end)] == 0){
              continue;
           }
           // add the character to char map of hasFound
           hasFound[s1.charAt(end)]++;
           //if frequency of hasFound of char is less or equals to needsToFind .. increment count
           //it means that the if you find more than required times do add
           if(hasFound[s1.charAt(end)] <= needsTofind[s1.charAt(end)]) {
               count++;
           }



           //this means the window has been reached
           if(count == s2.length()) {
               // condense the window by moving begin pointer.. try to move begin if begin points to a character not in
               //pattern or if a character is founded in extra of the required . This loop makes sure that you dont
               // break window constraint
               while(needsTofind[s1.charAt(begin)] ==0 || hasFound[s1.charAt(begin)] > needsTofind[s1.charAt(begin)]) {
                   //if character at begin was found more than it its occurence in pattern ..
                   //move the begin pointer and decrease found character
                   if(hasFound[s1.charAt(begin)] > needsTofind[s1.charAt(begin)]) {
                       hasFound[s1.charAt(begin)]--;
                   }
                  begin ++;
               }
               //update the minLength if its greater
               int length = end - begin+1;
               if(length<minLength) {
                   minLength = length;
                   minEnd = end;
                   minStart = begin;
               }


           }


       }

       return s1.substring(minStart, minEnd+1);
    }

    public static void main(String[] args) {

        System.out.println(findSmallestWindow(s1, s2));
    }


}
