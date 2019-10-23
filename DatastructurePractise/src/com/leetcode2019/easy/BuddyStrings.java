package com.leetcode2019.easy;

/**
 * https://leetcode.com/problems/buddy-strings/
 * <p>
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = "ab", B = "ba"
 * Output: true
 * Example 2:
 * <p>
 * Input: A = "ab", B = "ab"
 * Output: false
 * Example 3:
 * <p>
 * Input: A = "aa", B = "aa"
 * Output: true
 * Example 4:
 * <p>
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 * Example 5:
 * <p>
 * Input: A = "", B = "aa"
 * Output: false
 * <p>
 * <p>
 * Note:
 * <p>
 * 0 <= A.length <= 20000
 * 0 <= B.length <= 20000
 * A and B consist only of lowercase letters.
 */
public class BuddyStrings {
    /**
     * Solution:
     * You need to verify certain cases :
     * 1) A.length != B.length   => false
     * 2) NUmber of mismatches between A and B are greater than 2   => false
     * 3) 2nd Mismatch cannot be corrected via swap => False
     * 4) Find if A has atleast one character repeated ; Set a flag
     * 5) Finally - return True if - (NUmber of mismatches == 2) OR (mismatch == 0 && atleast 1 repeated character in A)
     */
    public static boolean buddyStrings(String a, String b) {
        int[] letterA = new int[26];
        boolean sameWithOneRepeatedChar = false;
        int previousMismatch = -1;
        int mismatch = 0;

        if (a.length() != b.length()) {
            return false;
        }

        for (int i = 0; i < a.length(); i++) {
            if (letterA[a.charAt(i) - 'a'] > 0) {
                sameWithOneRepeatedChar = true;
            } else {
                letterA[a.charAt(i) - 'a']++;
            }

            if (mismatch > 2) {
                return false;
            }

            if (a.charAt(i) != b.charAt(i)) {
                if (previousMismatch != -1) {
                    if (a.charAt(previousMismatch) != b.charAt(i) || a.charAt(i) != b.charAt(previousMismatch)) {
                        return false;
                    }
                } else {
                    previousMismatch = i;
                }
                mismatch++;
            }
        }

        return mismatch == 2 || (sameWithOneRepeatedChar && mismatch == 0);
    }

    public static void main(String[] args) {
        System.out.println(buddyStrings("ab", "ca"));
    }
}
