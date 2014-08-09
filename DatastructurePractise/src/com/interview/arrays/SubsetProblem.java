package com.interview.arrays;

/**
 * How many ways are there to select k elements out of n elements
 * Created by abhimaloo on 8/8/14.
 */
public class SubsetProblem {

    public static int [] input = {1,2,3};
    /**
     * select k elements from n elements .. how many ways can you do it
     * @param n
     * @param k
     * @return
     */
    public static int select(int n, int k) {
        //base case
        // if k becomes 0 (select 0 from n elements can be done in 1 way) .. k ==n selecting k out of
        // n elements where n is equals to k is also 1 (select all the k elements at once)
        if(k==0 || k ==n) {
            return 1;
        }
        // cases where k is part of n and the solution + cases where n is noy part of the solution
        return select(n-1,k-1) + select(n-1,k);
    }

    public static void printAllSubset(int[] set) {

        for( int i =0; i<= set.length; i ++) {
            boolean[] ifPrint = new boolean[set.length];
            printSet(set, ifPrint, 0, i);
        }
    }


    public static void printSet(int[] set, boolean[] ifPrint, int start, int remain) {

        // if remaining elements to be processed is 0 .. then print the set
        if(remain == 0) {
            System.out.println("");
            System.out.print("{");

            for( int i=0; i< ifPrint.length; i++) {
                if(ifPrint[i]){
                    System.out.print(" " + set[i] + " ");
                }
            }
            System.out.print("}");
        } else {


            if(start+remain <= set.length ){
                // apply the backtracking thing here
                // iterate through the array
                for( int i=start; i< set.length; i++) {
                    // make sure the ith position has false on it
                    if(!ifPrint[i]) {
                        //turn on the print flag for ith position
                        ifPrint[i] = true;
                        // recurse to the next steps(meaning selecting it as a part of solution)
                        // increment the i+1th position and reduce the remaining amount  by 1
                        printSet(set,ifPrint,i+1,remain-1);
                        // again unset/unrecord like typical backtracking.. which means now i is not part of solution
                        ifPrint[i] = false;
                    }
                }
            }

        }
    }






    public static void main(String[] args) {
        printAllSubset(input);

    }
}
