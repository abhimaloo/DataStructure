package com.leetcode;

/**
 * Created by abhimaloo on 9/12/14.
 */
public class BinarySearchInRotatedArray {
    public static int[] input = {3,4,5,6,7,1,2};

    public static int find(int[] input, int start, int end, int key) {
        if(start > end) {
            return -1;
        }

        int mid = (start + end)/2;
        if(input[mid] == key) {
            return mid;
        }

        // first half is sorted
        if(input[mid] >= input[start]) {
            if(key < input[mid] && key >= input[start]) {
                return find(input, start, mid-1, key);
            } else{
                return find(input, mid+1, end, key);
            }
        } else {
            // bottom half is sorted
            if(key > input[mid] && key <= input[end]) {
                return find(input, mid+1, end, key);
            } else{
                return find(input, start, mid-1, key);
            }


        }

    }

    public static void main(String[] args) {
        System.out.println(find(input, 0, input.length-1, 2));
    }
}
