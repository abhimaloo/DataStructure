package com.interview.arrays.dp;

/**
 * find the longest common substring between two strings "abcde" and "xabcz".
 * result should be "abc"
 * Created by abhishekm787 on 7/16/14.
 */
public class LongestCommonSubstring {

    /**
     * trick is to use DP
     * maintain a length[m][n] array which contains length of common substring from
     * m mean 0...m and n mean 0..n indexes
     *
     * @param s1
     * @param s2
     * @return
     */
    public static char[] findLongestCommonSubstringUsingTries(char[] s1, char[] s2) {
        int[][] length = new int[s1.length][s2.length];
        int maxLength = 0; int maxi=-1;

        for(int i=0;i<s1.length; i++){
            for( int j=0; j< s2.length; j++) {
                // if the characters match
                if(s1[i] == s2[j]){
                    // increase the length by 1 since i-1 and j-1 substrings
                    length[i][j] = 1+ (i-1>=0 && j-1>=0 ? length[i-1][j-1]: 0);
                    //reconstruction logic
                    if(maxLength < length[i][j]) {
                        maxLength = length[i][j];
                        maxi = i;
                    }

                } else {
                    //otherwise find maximum of matching length by adding the last character on both the sides
                    length[i][j] = 0;
                }

            }
        }

        char[] response = new char[maxLength];
        while(maxLength>0){
            response[maxLength-1] = s1[maxi];
            maxLength--;
            maxi--;
        }

      return response;
    }

    public static void main(String[] args) {
        char[] lcs = findLongestCommonSubstringUsingTries("abcde".toCharArray(), "xabcz".toCharArray());
        for (int i = 0; i < lcs.length; i++) {
            System.out.println(lcs[i]);

        }
    }
}
