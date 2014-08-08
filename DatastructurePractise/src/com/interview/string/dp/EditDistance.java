package com.interview.string.dp;

/**
 * Given two strings of size m, n and set of operations replace (R), insert (I) and delete (D) all at equal cost.
 * Find minimum number of edits (operations) required to convert one string into another.
 * Ex - MAN and WOMAN  edit distance is 2 we need to add two letters in front 'W' and 'O' to 'MAN'
 *
 * Created by abhimaloo on 7/29/14.
 */
public class EditDistance {
    public static int  findEditDistance(String s1, String s2) {
        // this array will keep distance[i][j]
        // where i is 0...i index Of prefix of S1 and j is 0..j index of prefix of s2
        int[][] distance = new int[s1.length()+1][s2.length()+1];

        //base cases  - Match empty string with empty string
        distance[0][0] = 0;
        // Match any element of String 1 to empty string
        for(int i=1; i< s1.length()+1; i++) {
            distance[i][0] = i; //very important ..value is i
        }
        //Match any element of String 2 with empty string
        for(int i=1; i< s2.length()+1; i++) {
            distance[0][i] = i; //very important .. value is i
        }

        for(int i=1;i<s1.length()+1; i++) {
            for( int j=1; j<s2.length()+1; j++) {
                //if the character matches
                if(s1.charAt(i-1) == s2.charAt(j-1) ) {
                    //no extra cost for this step.. inherit the old cost
                    distance[i][j] = distance[i-1][j-1];
                } else {
                    // plus one in the end is for mismatch
                    distance[i][j] = Math.min(Math.min(distance[i-1][j], distance[i][j-1]), distance[i-1][j-1])+1;
                }
            }
        }

        return distance[s1.length()][s2.length()];

    }

    public static void main(String[] args) {
        System.out.println(findEditDistance("MAN", "WOMAN"));
    }
}
