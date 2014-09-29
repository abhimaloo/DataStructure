package com.facebook;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class EditDistance {
    public static String s1 = "MAN";
    public static String s2 = "WOMAN";

    public static int editDistance(String s1, String s2) {
        if(s1 == null || s2 == null) {
            return -1;
        }

        int[][] distance = new int[s1.length()+1][s2.length()+1];

        for(int i = 0; i<= s1.length(); i++) {
            distance[i][0] = i;
        }

        for(int i = 0; i<= s2.length(); i++) {
            distance[0][i] = i;
        }

        for(int i = 1; i<= s1.length(); i++) {
            for( int j = 1; j<= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    distance[i][j] = distance[i-1][j-1];
                } else {
                    distance[i][j] = Math.min(distance[i-1][j-1], Math.min(distance[i][j-1], distance[i-1][j])) +1;
                }
            }
        }


        return distance[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        System.out.println(editDistance(s1, s2));
    }
}
