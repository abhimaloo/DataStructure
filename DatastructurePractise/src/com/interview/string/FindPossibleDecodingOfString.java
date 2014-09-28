package com.interview.string;

/**
 * Let 1 represent ‘A’, 2 represents ‘B’, etc. Given a digit sequence,
 * count the number of possible decodings of the given digit sequence.
 *
 * Input:  digits[] = "121"
 * Output: 3
 * The possible decodings are "ABA", "AU", "LA"
 *
 * http://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/
 *
 * Created by abhishekm787 on 8/4/14.
 */
public class FindPossibleDecodingOfString {
    /**
     * trick is to apply a recursive formula
     * base case  - count becomes 1 if string is of size 1 or zero
     * start from last digit. if last digit is non zero recurse on string without the last digit on.
     * pick the last two digits and make sure that it forms a number between the range 1-26 .. if its true ..
     * add the count with recursive call to string with last 2 characters plucked off.
     *
     * @param s
     * @return
     */
    public static int findPossibleDecoding(String s) {
        if(s.length()==0 || s.length() ==1) {
            return 1;
        }

        int count = 0;
        //very smart approach .. 0 at any digit will not increase a count.. it will be the part of earlier count
        if(s.charAt(s.length()-1) >'0') {
          count  = findPossibleDecoding(s.substring(0,s.length()-1));
        }
        // first condition means anything less than 20 and second condition means anything between 20 to 26 ..
        if(s.charAt(s.length()-2) <'2' || (s.charAt(s.length()-2) == '2' && s.charAt(s.length()-1) <'7')){
            count += findPossibleDecoding(s.substring(0,s.length()-2));
        }

        return count;

    }


    public static int findPossibleDecodingDP(String input) {
        int[] count = new int[input.length()+1];
        count[0] = 1;
        count[1] = 1;
        for( int i=2; i<= input.length(); i++){
            count[i] = 0;
            if(input.charAt(i-1) > '0') {
                count[i] = count[i-1];
            }

            if(input.charAt(i-2) <'2' || (input.charAt(i-2) == '2' && input.charAt(i-1) <'7')){
                count[i] += count[i-2];

            }
        }

        return count[input.length()];

    }

    public static void main(String[] args) {
        //System.out.println(findPossibleDecoding("999"));
        System.out.println(findPossibleDecodingDP("012"));
    }
}
