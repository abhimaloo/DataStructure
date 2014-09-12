package com.revision;

/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

 You have the following 3 operations permitted on a word:

 a) Insert a character
 b) Delete a character
 c) Replace a character

 https://oj.leetcode.com/problems/edit-distance/
 * Created by abhishekm787 on 9/11/14.
 */
public class EditDistance {

    public static String s1 = "MAN";
    public static String s2 = "WOMAN";

    public static int findEditDistance(String s1, String s2) {

        int[][] distance = new int[s1.length()+1][s2.length()+2];

        //base case for matching a string with empty string
        for( int i = 0; i<=s1.length(); i++) {
            distance[i][0] = i;
        }
        //base case for matching a string with empty string
        for( int i = 0; i<=s2.length(); i++) {
            distance[0][i] = i;
        }


        for( int i = 1; i<= s1.length(); i++) {
            for( int j = 1; j<= s2.length(); j++) {
                // if character at the same index matches each other, no addiotnal cost is added inherit the i-1, j-1 cost
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    distance[i][j] = distance[i-1][j-1];
                } else {
                    // find minimum cost of plucking both characters, or one character from either of the string and add a penalty
                    distance[i][j] = Math.min(Math.min(distance[i-1][j-1], distance[i][j-1]), distance[i-1][j]) +1;
                }

            }
        }

        return distance[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        System.out.println("Distance : " + findEditDistance(s1, s2));
    }
}
