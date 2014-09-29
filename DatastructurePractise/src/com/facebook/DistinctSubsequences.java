package com.facebook;

/**
 * Created by abhimaloo on 9/27/14.
 */
public class DistinctSubsequences {
    public static String s = "RABBBIT";
    public static String t = "RABBIT" ;

    /**
     * we check every element of s with every other element of t..
     * if we find a character match  - that means,
     * @param s
     * @param t
     * @return
     */
    public static int numOfDistinctSubsequences(String s, String t) {

        int[][] count = new int[s.length()+1][t.length()+1];

        for( int i = 0; i<= s.length(); i++) {
            count[i][0] = 1;
        }

        for( int i = 1; i<=s.length(); i++) {
            for( int j = 1; j<=t.length(); j++) {
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    count[i][j] = count[i-1][j-1] + count[i-1][j];
                } else {
                    count[i][j] = count[i-1][j];
                }
            }
        }

        return count[s.length()][t.length()];
    }

    public static void main(String[] args) {
        System.out.println(numOfDistinctSubsequences(s, t));
    }


}
