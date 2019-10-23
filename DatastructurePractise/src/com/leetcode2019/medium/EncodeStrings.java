package com.leetcode2019.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/encode-and-decode-strings/
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.
 */
public class EncodeStrings {
    public static String DELIM = "/";

    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();

        // do run length encoding
        for (String str : strs) {
            encoded.append(str.length()).append(DELIM).append(str);
        }
        return encoded.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        List<String> decoded = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int idx = s.indexOf(DELIM, i);
            int size = Integer.parseInt(s.substring(i, idx));
            decoded.add(s.substring(idx + 1, idx + size + 1));
            i = idx + size + 1;
        }
        return decoded;
    }

    public static void main(String[] args) {
        String encoded = encode(Arrays.asList("Hello", "World"));
        System.out.println(encoded);
        List<String> decoded = decode(encoded);

    }
}
