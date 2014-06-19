package com.maloo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a number N. You have to list the ways of obtaining N by using numbers from 1 to N – 1 any number of times
 * Example: If N = 4,  I can only use numbers from 1 to 3 . (1 to N – 1)
 * answer would be :  {1, 1, 1, 1} , {1, 1, 2}, {1, 3}, {2, 2}   , {3, 1}, {2, 1, 1}, {1, 2, 1}
 */
public class PermutationSum {


   private static int n;
   private static int[] a;

   public static void solve(int index, int remainingSum) {

       //if remaining sum is zero .. print the array and continue
       // base case
       if(remainingSum ==0) {
           printArray(index);
       }
       else if(remainingSum >0) {      // if remaining sum is still positive
           for( int i = 1; i<=n-1; i ++) {    // iterate through all the posibilities for that index
                a[index] = i;                // record the index and unrecord by overwriting in recursed way
               solve(index+1,remainingSum -i);   // recursively thr to find out next index from there
           }
       }  // thats where back tracking happens when remaining sum < 0 or negative

   }

   private static void printArray(int index){
       for(int i=0; i <index; i++) {
           System.out.print(" "+a[i]+ " ");
       }
       System.out.println("");
   }



    public static void main(String args[]) {
        n = 4;
        a = new int[n];
        solve(0, n);
    }
}
