package com.maloo.dynamicprogramming;

/**
 * Created by abhishekm787 on 7/7/14.
 */
public class LongestIncreasingSubsequence {
    public static int[] a = {10, 22, 9, 33, 21, 50, 41, 60};


    /**
     * Concept is very easy
     * for every value in array try to find out the length of increasing subsequence if it is part of the solution
     * which means from start of the array till the i'th index..if there is a smaller subsequence which extends till the ith element
     * i'th element will get the maximum length of such subsequences on its left.
     * retrack array and max index is used to reconstruct the solution
     */
    public static void find() {
        int []longSeq = new int[a.length];
        int [] retrack = new int[a.length];
         int max  = Integer.MIN_VALUE;
         int max_index = Integer.MIN_VALUE;;
        //set longest length of array sized i as 1
        for( int i=0; i< longSeq.length; i ++){
            longSeq[i] = 1;
            retrack[i] = i;
        }

        for( int i = 1 ; i < a.length; i ++) {

            for( int j =0; j<i ; j ++){
                if(a[j] <= a[i] && longSeq[j]+1 > longSeq[i]){
                    longSeq[i] = longSeq[j] +1;
                    // to reconstruct the solution
                    retrack[i] = j;
                    if(longSeq[i]>max){
                        max = longSeq[i];
                        max_index = i ;
                    }
                    // reconstruction code ends here

                }
            }

        }

        System.out.println("Length of Longest sub-sequence is "+ longSeq[max_index]);

        //reconstruct the solution
        System.out.print(a[max_index]);
        while(true){
            if(retrack[max_index]!=max_index){
                System.out.print(" "+a[retrack[max_index]]+" " );
                max_index = retrack[max_index];
            } else {
                break;
            }

        }


    }

    public static void main(String[] args) {
         find();
    }
}
