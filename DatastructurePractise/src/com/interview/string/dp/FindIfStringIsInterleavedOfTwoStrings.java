package com.interview.string.dp;

/**
 * Given three strings A, B and C. Write a function that checks whether C is an interleaving of A and B.
 * C is said to be interleaving A and B, if it contains all characters of A and B and order of all characters
 * in individual strings is preserved.
 *
 * Ex - A = "XXY"  B = ""XXZ"; C = "XXXXZY";
 * Created by abhimaloo on 8/5/14.
 */
public class FindIfStringIsInterleavedOfTwoStrings {
    public static String a = "XXY";
    public static String b = "XXZ";
    public static String c = "XXXXZY";

    /**
     * trick is to use dynamic programming
     * maintain a 2d array of boolean for keeping interleaved data about [i][j]
     * interleaved[i][j] represents whether string A(0..i) and B(0..j) are interleaved by looking at C(0..i+j-1)
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static boolean isInterleaved(String a, String b, String c) {
        boolean[][] interleave = new boolean[a.length()+1][b.length()+1];

        if(a.length()+b.length() !=c.length()) {
            return false;
        }

        for( int i=0; i<= a.length(); i++) {
            for( int j = 0; j <= b.length(); j++ ) {
                //for empty strings
                if(i==0 && j==0) {
                    interleave[i][j] = true;
                }
                //if either of them are empty .. if other one matches to target character ..it means
                // interleaving decision will be copied from the decision where this matching character was not present
                else if(i==0 && b.charAt(j-1) == c.charAt(i+j-1)) {

                    interleave[i][j] = interleave[i][j-1]; // since character of b and c matches we will copy the value of i and j-1 into i,j
                }
                //if b is empty and a matches c
                else if(j==0 && a.charAt(i-1) == c.charAt(i+j-1)) {

                    interleave[i][j] = interleave[i-1][j]; // since character of b and c matches we will copy the value of i and j-1 into i,j
                }

                //if a and c match but b does not match to c .. we will copy the decision from i-1 and j to i,j since ith element is matching with C's element
                else if(i!=0 && j!=0 && a.charAt(i-1) == c.charAt(i+j-1)  && b.charAt(j-1) != c.charAt(i+j-1)){

                    interleave[i][j] = interleave[i-1][j]; // i-1 is because ith element matched..so decide on the basis of i-1th element.
                }

                //if b and c matches but a and c does not .. copy i j-1 to i,j
                else if(i!=0 && j!=0 && a.charAt(i-1) != c.charAt(i+j-1) &&  b.charAt(j-1) == c.charAt(i+j-1)){

                    interleave[i][j] = interleave[i][j-1];
                }

                //if both a and b matches to c .. Do an OR of (i,j-1) (i-1,j)
                else if(i!=0 && j!=0 && a.charAt(i-1) == c.charAt(i+j-1) && b.charAt(j-1) == c.charAt(i+j-1)){

                    interleave[i][j] = interleave[i][j-1] || interleave[i-1][j];
                }

            }
        }

        return interleave[a.length()][b.length()];
    }

    public static void main(String[] args) {
        System.out.println(isInterleaved(a, b, c));
    }
}
