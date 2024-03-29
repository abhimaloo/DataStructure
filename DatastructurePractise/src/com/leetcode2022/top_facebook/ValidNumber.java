package com.leetcode2022.top_facebook;

/*
A valid number can be split up into these components (in order):

A decimal number or an integer.
(Optional) An 'e' or 'E', followed by an integer.
A decimal number can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One of the following formats:
One or more digits, followed by a dot '.'.
One or more digits, followed by a dot '.', followed by one or more digits.
A dot '.', followed by one or more digits.
An integer can be split up into these components (in order):

(Optional) A sign character (either '+' or '-').
One or more digits.
For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].

Given a string s, return true if s is a valid number.
 */
public class ValidNumber {
    public static boolean isNumber(String s) {
        if (s == null || s.trim().length() == 0) return false;
        s = s.trim();
        boolean numberSeen = false;
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberAfterE = true; // this flag means - if there is an e|E there is a number after that

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                numberSeen = true;
                numberAfterE = true;
            } else {
                if (s.charAt(i) == '.') {
                    if (eSeen || pointSeen) {
                        return false;
                    }
                    pointSeen = true;
                } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                    if (eSeen || !numberSeen) {
                        return false;
                    }
                    eSeen = true;
                    numberAfterE = false;
                } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                    if (i != 0 && !(s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {
                        return false;
                    }
                } else {
                    return false;
                }

            }
        }

        return numberSeen && numberAfterE;
    }

    public static void main(String[] args) {
        System.out.println(isNumber("005047e+6"));
    }
}
