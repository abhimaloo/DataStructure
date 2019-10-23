package com.datastructure2019.dp;

/*
https://leetcode.com/problems/edit-distance/
Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 */
public class EditDistance {
    /*
    DP Table

         ""  A  B  H  I
      ""  0  1  2  3  4
      A   1  0  1  2  3
      K   2  1  1  2  3
      H   3  2  2  1  2
      I   4  3  3  2  1


    Important metric to consider while filling up DP table
     ________________
    |Replace| Delete |
    |______ |________|
    |Insert |  Here  |
    |______ |________|

    For every [i,j] pair - we check if a[i] == b[j]  then inherit the editdistance from Dp[i-1][j-1] ; since no cost is required
    If a[i] != b[j]  Then 3 possibilities
    a) Replace a[i] by b[j] -> dp[i-1][j-1] + 1  -> It means chop of ith and jth word from a and b and make them same
    b) Insert into b[j] -> DP[i][j-1] + 1 -> it means reduce the first string by letter and then insert a characted similar to b[j]
    c) Delete from a[i]  -> DP[i-1][j] + 1 -> It means chop of the ith character from a and hope that either it matches a[i-1] or somewhere in suffix of a


     */
    public static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            if (word1 == word2) {
                return 0;
            }
            return word1 != null ? word1.length() : word2.length();
        }
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // dill the dp for 0th element which is empty string
        for (int i = 1; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = 1 + Math.min(dp[i + 1][j], Math.min(dp[i][j], dp[i][j + 1]));
                }
            }
        }

        //  Now the table continues
        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("ABHI", "AKHI"));
    }

}
