package com.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhimaloo on 9/27/14.
 */
public class PalindromePartitioning {
    public static String s = "aab";

    public static List<List<String>> palindromicPartition(String s) {
        if(s == null || s.length() == 0) {
            return null;
        }

        List<List<String>> result = new ArrayList<>();
        partition(s, 0, new ArrayList<String>(), result);

        return result;
    }

    public static void partition(String s, int start, List<String> partition, List<List<String>> result) {
        if(start == s.length()) {
            ArrayList<String> temp = new ArrayList<>(partition);
            result.add(temp);
            return;
        }

        for( int i = start+1; i<= s.length(); i++) {
            String part = s.substring(start, i);
            if(isPalindrome(part)) {
                partition.add(part);
                partition(s,start+1,partition,result);
                //backtrack
                partition.remove(partition.size()-1);
            }
        }
    }

    public static boolean isPalindrome(String s) {
        if(s == null) {
            return false;
        }

        int left = 0;
        int right= s.length() -1;

        while(left < right) {
            if(s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        List<List<String>> result = palindromicPartition(s);
        for(List<String> list : result ) {
            for(String s: list) {
                System.out.print(" " + s + " ");
            }
            System.out.println();
        }

    }




}
