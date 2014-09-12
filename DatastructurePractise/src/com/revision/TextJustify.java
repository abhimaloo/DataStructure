package com.revision;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

 Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Solution should look like
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]

 * Created by abhishekm787 on 9/11/14.
 */
public class TextJustify {

    public static String[] words =  {"This", "is", "an", "example", "of", "text", "justification."};
    public static int maxLength = 16;

    /**
     * Idea is to use dynamic programming ..
     * consider all suffixes of the sentence we want to pack..
     * eg -
     * justification.
     * text justification.
     * of text justification.
     * example of text justification.
     * an example of text justification.
     * is an example of text justification.
     * This is an example of text justification.
     *
     * one more thing to consider, Badness score for packing i to j elements in a line  =
     * if(length(i to j-1) <= maxLength) { badness = (maxLength - length(i to j-1))^2 }
     * else badness  = INFINITE
     * Here, 'i' is the index which
     *
     * NOW, we will need to start from back and consider each suffix by trying every word as i and j
     * finally find out the badness score for each one and pick the minimum of each j..
     *
     *
     *
     * @param words
     * @param maxLength
     */

    public static void justifyText(String[] words, int maxLength) {

       int[] justifyCost = new int[words.length+1];
       int[] justificationPoints = new int[words.length +1];

       justifyCost[words.length] = 0;

       for(int i = words.length-1; i >= 0; i--) {
           // assig justify cost to infinite for now
           justifyCost[i] = Integer.MAX_VALUE;
           for( int j = i+1; j<words.length+1; j++) {
               int length = 0;
               for( int k = i; k<j;k++) {
                   length += words[k].length();
               }
               //add spaces to the length as well
               length += j-i-1;

               int cost = Integer.MAX_VALUE;

               if(length <= maxLength) {
                   cost = (maxLength - length) * (maxLength - length);
               }
               // find the minimum cost
               if(cost != Integer.MAX_VALUE && justifyCost[i] > justifyCost[j] + cost) {
                    justifyCost[i] = justifyCost[j] + cost;
                    justificationPoints[i] = j;
               }

           }
       }





    }

    public static void main(String[] args) {

        justifyText(words,maxLength);
    }

}
