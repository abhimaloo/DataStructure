package com.leetcode2019.hard;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/text-justification/
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
 */
public class TextJustification {
    /*
    Dp exhaustuve approach.
    Idea is to consider all suffixes of words. Now for every i where i'th word is the start of suffix, figure out the jth word which will start the next line while keeping the current lines cost minimum
    // cost function would be - (length of ith word to jth words + spaces - maxLength)^2  if maxLength < else Infinite

     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int[] dp = new int[words.length + 1];
        // jth word would not on any new line since there is no word left
        dp[words.length] = 0;

        int[] lineStart = new int[words.length + 1];
        int[] suffixLength = new int[words.length + 1];

        for (int i = words.length - 1; i >= 0; i--) {
            suffixLength[i] = suffixLength[i + 1] + words[i].length();
        }

        for (int i = words.length - 1; i >= 0; i--) {
            if (i == words.length - 1) {
                dp[i] = 0;
            } else {
                dp[i] = Integer.MAX_VALUE - 1000;// so that it does not overflow
            }
            for (int j = i + 1; j < words.length; j++) {
                int length = suffixLength[i] - suffixLength[j];
                length += j - i - 1; // since j is not included in the cost, we would have spaces  =  j-1 - i
                int cost = length < maxWidth ? dp[j] + (int) Math.pow(maxWidth - length, 2) : Integer.MAX_VALUE - 900;
                if (cost < dp[i]) {
                    dp[i] = cost;
                    lineStart[i] = j;
                }
            }
        }

        int nextLine = lineStart[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i == nextLine) {
                result.add(sb.toString());
                nextLine = lineStart[nextLine];
                sb = new StringBuilder();
            }
            sb.append(words[i]);
            if (i != nextLine - 1) {
                sb.append(" ");
            }
        }

        if (nextLine == 0) result.add(sb.toString());

        return result;
    }

    public static List<String> justifyGreedy(String[] words, int maxWidth) {
        int left = 0;
        List<String> result = new ArrayList<>();

        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            result.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }

        return result;
    }

    private static int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        int sum = words[right++].length();

        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth)
            sum += 1 + words[right++].length();

        return right - 1;
    }

    private static String justify(int left, int right, String[] words, int maxWidth) {
        if (right - left == 0) return padResult(words[left], maxWidth);

        boolean isLastLine = right == words.length - 1;
        int numSpaces = right - left;
        int totalSpace = maxWidth - wordsLength(left, right, words);

        String space = isLastLine ? " " : blank(totalSpace / numSpaces);
        int remainder = isLastLine ? 0 : totalSpace % numSpaces;

        StringBuilder result = new StringBuilder();
        for (int i = left; i <= right; i++)
            result.append(words[i])
                    .append(space)
                    .append(remainder-- > 0 ? " " : "");

        return padResult(result.toString().trim(), maxWidth);
    }

    private static int wordsLength(int left, int right, String[] words) {
        int wordsLength = 0;
        for (int i = left; i <= right; i++) wordsLength += words[i].length();
        return wordsLength;
    }

    private static String padResult(String result, int maxWidth) {
        return result + blank(maxWidth - result.length());
    }

    private static String blank(int length) {
        return new String(new char[length]).replace('\0', ' ');
    }


    public static void main(String[] args) {
        String[] input = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        fullJustify(input, 16);
    }
}
