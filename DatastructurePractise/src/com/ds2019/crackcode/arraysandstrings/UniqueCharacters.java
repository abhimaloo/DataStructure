package com.ds2019.crackcode.arraysandstrings;

public class UniqueCharacters {

    // with extra memory
    public static boolean hasUniqueCharacters(String s) {
        boolean[] mask = new boolean[256];

        for (char c : s.toCharArray()) {
            if (mask[c]) {
                return false;
            } else {
                mask[c] = true;
            }
        }

        return true;
    }

    // with mask
    public static boolean hasUniqueCharactersUsingMask(String s) {
        int mask = 0;

        for (char c : s.toCharArray()) {
            int asciiVal = c;
            if ((mask & (1 << asciiVal)) > 0) {
                return false;
            } else {
                mask |= (1 << asciiVal);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(hasUniqueCharactersUsingMask("Abhis"));
    }
}
