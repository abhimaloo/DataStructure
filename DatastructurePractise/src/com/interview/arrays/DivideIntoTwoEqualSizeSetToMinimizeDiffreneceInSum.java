package com.interview.arrays;



/**
 * Given a set of n integers, divide the set in two subsets of n/2 sizes
 * each such that the difference of the sum of two subsets is as minimum as possible.
 * If n is even, then sizes of two subsets must be strictly n/2 and if n is odd,
 * then size of one subset must be (n-1)/2 and size of other subset must be (n+1)/2.

 For example, let given set be {1,5,6,2},
 the size of set is 4.
 Output for this set should be {1,6} and {5,2}.
 Both output subsets are of size 5 and sum of elements in both subsets is same (148 and 148)
 * Created by abhishekm787 on 7/16/14.
 */
public class DivideIntoTwoEqualSizeSetToMinimizeDiffreneceInSum {
    public static int[] input = {1,5,6,2};

   public static int findSum(int[] input) {
       int sum = 0;
       for (int i = 0; i < input.length; i++) {
           sum += input[i];

       }
       return sum;
   }

   public static void balancePartition(int[] input) {
       int sum = findSum(input);
       int[][] p = new int[input.length+1][sum+1];
       int[][] reconstruct = new int[input.length+1][sum+1];


       //base cases for DP
       for( int i =0; i<sum+1; i++){
           p[0][i] = 0;
           reconstruct[0][i] = 0;
       }

       //base cases for DP
       for(int i=0; i< input.length+1; i++){
           p[i][0] = 1;
           reconstruct[i][0] = 1;
       }

       for(int i=1; i<input.length+1; i++){
           for(int j = 1; j<sum+1; j++){
               //have to take care of boundary conditions .. negative values and underflow situations
               int lastTerm = p[i-1][j];
               int lastTermWithOptimalSolution =  j-input[i-1]>=0?p[i-1][j-input[i-1]]: 0;
               //have to write this way to get reconstruction algorithm to work
               if(lastTerm>lastTermWithOptimalSolution){
                   p[i][j] = lastTerm;
                   //fill in the index which is responsible for a '1'
                   reconstruct[i][j] = lastTerm ==0? -1:i-2;
               } else {
                   p[i][j] = lastTermWithOptimalSolution;
                   //fill in the index which is responsible for a '1'
                   reconstruct[i][j] = lastTermWithOptimalSolution ==0? -1 : i-1;
               }

               p[i][j] = Math.max(lastTerm, lastTermWithOptimalSolution);
           }
       }

       int midSum = sum/2;
       int diff = -1;
       for( int i=0; i<= midSum; i++){
           if(p[input.length][midSum-i] ==1){
               diff = i;
               break;
           }
       }

       System.out.println("Min Diff is :" + 2*diff);

       //difference is the sum of first sub set with correct balance
       //reconstruct the set
       System.out.println("First Set Contains");
       while (midSum-diff>=0){
           System.out.print(" "+ input[reconstruct[input.length][midSum-diff]]+ " ");
           diff += input[reconstruct[input.length][midSum-diff]];
       }



   }

    public static void main(String[] args) {
        balancePartition(input);
    }

}
