package com.leetcode2022.top_facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
We can shift a string by shifting each of its letters to its successive letter.

For example, "abc" can be shifted to be "bcd".
We can keep shifting the string to form a sequence.

For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.



Example 1:

Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
Example 2:

Input: strings = ["a"]
Output: [["a"]]


Constraints:

1 <= strings.length <= 200
1 <= strings[i].length <= 50
strings[i] consists of lowercase English letters.
 */
public class GroupShiftedStrings {

    /*
    Intuition : We need to group valid shifts togather
    a valid shift in case of numbers would be : 246 -> 357 (here every digit is increased by 1)
    now imagine the digit is- 435 -> 546. if we need to normalize the first digit to 1; then for first number I need to reduce the first digit by '3' ,second digit by 3 and third digit by 3 as well.
    435 becomes 102 (102 would be the start of the shifting pattern for 435 if nto considering rotations for now)
    similarly 546 becomes 102 (subtract 4 from 5, subtract 4 from 4 and subtract 4 from 6)
    So the intuition is clear that every shift representation of a number if reduced to start from "1" would become the same number (as we saw 102 in above case)

    Apply same principle to abc letters
    we want the shiftings to be normalized by starting from 'a'. the same shifting sequence will share the same orginal shift pattern
    hence to group them togather we can use the original shift pattern as a hash key for a hashmap

    rest is simple
     */
    public char shift(char letter, int shift) {
        // here since a-z is cyclic we could end up with negative elements and mod may not work fine
        // hence we are using a trick of adding 26 to the numerator since mod will come out positive that way
        return (char) ((letter - shift + 26) % 26 + 'a');
    }

    public String getHash(String s) {
        char[] result = new char[s.length()];
        int shift = s.charAt(0);  // this is teh amount of shift needed
        for (int i = 0; i < s.length(); i++) {
            result[i] = shift(s.charAt(i), shift);
        }
        return String.valueOf(result);
    }

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> output = new HashMap<>();

        for (String word : strings) {
            String key = getHash(word);
            if (!output.containsKey(key)) {
                output.put(key, new ArrayList<>());
            }
            output.get(key).add(word);
        }

        List<List<String>> groups = new ArrayList<>();
        for (List<String> values : output.values()) {
            groups.add(values);
        }

        return groups;
    }
}
