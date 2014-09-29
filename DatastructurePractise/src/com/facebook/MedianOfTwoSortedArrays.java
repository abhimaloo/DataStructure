package com.facebook;

import java.util.Arrays;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class MedianOfTwoSortedArrays {
    public static int[] s1 = {1,2,3,4};

    public static int[] s2 = {7,8,9,10,11};

    public static int median(int[] s1, int length1, int[] s2, int length2, int k) {
       if(length1 > length2) {
           median(s2,length2, s1, length1, k);
       }

       if(length1 == 0) {
           return s2[k-1];
       }
       if(length2 == 0) {
           return s1[k-1];
       }

       if(k == 1) {
           return Math.min(s1[0] ,s2[0]);
       }

       int a = Math.min(k/2, length1);

       int b = k -a;

       if (s1[a-1]<=s2[b-1]) {
           return median(Arrays.copyOfRange(s1,a,s1.length), length1-a ,s2,length2,k-a);
       } else {
           return median(s1, length1 ,Arrays.copyOfRange(s2,a,s2.length),length2-b,k-b);
       }
    }

    public static int median(int[] s1, int[] s2) {
        if(s1 == null || s2 == null){
            return -1;
        }

        int total = s1.length + s2.length;

        if(total %2 == 0) {
            return (median(s1,s1.length, s2, s2.length, total/2) +  median(s1,s1.length, s2, s2.length, (total/2) +1))/2;
        } else{
            return median(s1,s1.length, s2, s2.length, (total/2) +1);
        }

    }

    public static void main(String[] args) {

        System.out.println(median(s1, s2));
    }



}
