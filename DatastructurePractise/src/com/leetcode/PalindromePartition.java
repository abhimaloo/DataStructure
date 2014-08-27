package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishekm787 on 8/26/14.
 */
public class PalindromePartition {

    public static void palindromePartition(int start, String s, List<String> partition, List<List<String>> result) {
        if(start == s.length()) {
           result.add(new ArrayList<String>(partition));
            return;
        }

        for( int i = start +1; i <= s.length(); i++) {
            if(isPalindrome(s.substring(start, i))){
                 partition.add(s.substring(start, i));
                 palindromePartition(i, s, partition, result);
                 partition.remove(partition.size()-1);
            }
        }


    }

    private static boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length()-1;

        while(left < right) {
            if(s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        List<List<String>> partitions = new ArrayList<>();


        palindromePartition(0, "abhi", new ArrayList<String>(), partitions);
        for(List<String> partition : partitions) {
            System.out.println();
            for(String palindrome : partition) {
                System.out.print(" (" + palindrome + ") ");
            }
            System.out.println();
        }

    }

}
