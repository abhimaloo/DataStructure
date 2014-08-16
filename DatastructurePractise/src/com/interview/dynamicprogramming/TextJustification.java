package com.interview.dynamicprogramming;

/**
 * Given a sequence of words, and a limit on the number of characters that can be put in one line (line width).
 * Put line breaks in the given sequence such that the lines are printed neatly.
 * Assume that the length of each word is smaller than the line width.
 *
 * For example, consider the following string and line width M = 15
 *  "Geeks for Geeks presents word wrap problem"

 * Following is the optimized arrangement of words in 3 lines
 * Geeks for Geeks
 * presents word
 * wrap problem
 *
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
 * Algo - https://www.youtube.com/watch?v=ENyox7kNKeY
 * Created by abhimaloo on 8/15/14.
 */
public class TextJustification {

    public static String [] words = {"Geeks", "for", "Geeks", "presents", "word", "wrap", "problem"} ;
    public static int lineLength = 15;


    /**
     * Trick is to Use DP .
     * Assume all the suffixes of current word list
     * any suffix can be described as suffix(i)  where i is the word from which suffix starts and goes till end
     * Now every suffix i can have a index(word) j which will be printed on a new Line.
     *
     * Cost of printing a word in a line will be (LengthOfLine - length of substring)^2 if length of substring is less than length of line
     * else cost  = infinite
     *
     * We will start from back and will construct the table called justifyCost[i]  which indicates the cost of justifying the substring starting from ith word
     *
     * justifyCost[i]  min ( justifyCost[j] + cost(i-j) [cost(i-j) determine the substring from word i till word j starts(exclusive of word at j) since j will be printed in next line
     *  also record justifyPoint[i]  - where i refers to the j(word) which lead to the minimum justifyCost[i] - this is for reconstruction
     *
     * finally you will have print information in justify Point.
     * keep going to the child pointer for chaging the line when print word
     *
     *
     *
     * @param words
     * @param lineLength
     */
    public static void textJustifiction(String[] words, int lineLength) {
        // this keeps the cost
        int[] justifyCost = new int[words.length+1];
        //this keeps the points from where we recived optimal or minimal cost
        int[] justifyPoints = new int[words.length+1];

        // base case  .. justify cost for empty string is 0
        justifyCost[words.length] = 0;

        int[] suffixLength = new int[words.length+1];

        // calculate suffix length
        for( int i = words.length-1; i >=0; i--) {
            suffixLength[i] = suffixLength[i+1]+ words[i].length();
        }

        // go backward from right to left
        for( int i = words.length-1; i >=0; i--) {
            // intially set the cost to be near infinite
            justifyCost[i] = Integer.MAX_VALUE - 900;
            // for every possible j starting from i+1 to n try calculating the cost.. pick the one with minimum cost
            for( int j = i+1; j <justifyCost.length; j++) {
                // length of the substring from (i-j) excluding word j
                int subStringlength = suffixLength[i] - suffixLength[j] ;

                // add one space per two words
                subStringlength += j -i -1;

                //calculate cost of substring(i to j)
                int cost  = 0;
                // if substring length is less than line length
                if(subStringlength <= lineLength) {
                    // cost will be equal to square of difference
                    cost = (lineLength - subStringlength) * (lineLength - subStringlength);
                } else {
                    // else cost will be infinite
                    cost = Integer.MAX_VALUE - 1000;
                }

                // now apply the main rule find minimum from justifyCost[j] + cost(i to j)
                if(justifyCost[j]+ cost < justifyCost[i]) {
                    justifyCost[i] = justifyCost[j] + cost;
                    // record the minimum point which yield to minimum value
                    justifyPoints[i] = j;
                }

            }
        }


        // Print the justified string
        int nextLine = justifyPoints[0];
        for( int i = 0; i<words.length; i++) {
            // if i becomes next line change the line while printing
            if( i == nextLine) {
                System.out.println();
                nextLine = justifyPoints[i];
            }
            // print the word
            System.out.print(words[i]);
            // determine when to print a single space
            if(i!=nextLine-1){
                System.out.print(" ");
            }
        }


    }

    public static void main(String[] args) {
        textJustifiction(words, lineLength);
    }



}
