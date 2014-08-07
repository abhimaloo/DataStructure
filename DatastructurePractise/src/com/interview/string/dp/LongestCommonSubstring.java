package com.interview.string.dp;

/**
 * Created by abhimaloo on 8/6/14.
 */
public class LongestCommonSubstring {
    public static String s1 = "GeeksForGeeks";
    public static String s2 = "GeeksFun";

    public static String findLongestCommonSubstring(String s1, String s2) {

        int length[][] = new int[s1.length()+1][s2.length()+1];
        int maxLength = Integer.MIN_VALUE;
        int max_end = -1;
        //base case .. Empty string matches
        length[0][0] = 1;

        for(int i=0; i <= s1.length(); i++) {
            for(int j=0; j <= s2.length(); j++) {

                if(i==0 || j==0) {
                     length[i][j] = 0;
                } else if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    length[i][j] = length[i-1][j-1] +1;
                    if(maxLength < length[i][j]) {
                        maxLength = length[i][j];
                        max_end = i-1;
                    }
                } else {
                    length[i][j] = 0;
                }

            }
        }

        StringBuilder builder = new StringBuilder();
        for( int i= max_end-maxLength+1 ; i<=max_end ; i++ ) {
            builder.append(s1.charAt(i));
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(findLongestCommonSubstring(s1, s2));
    }

}
