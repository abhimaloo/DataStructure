package com.revision;

/**
 * Created by abhimaloo on 9/18/14.
 */
public class MaxSumSubMatrix {

    public static int[][] input = {{1,   2,  -1,   -4,   -20},  //-2
                                   {-8, -3,   4,    2,    1 },   // 3
                                   {3,   8,   10,   1,    3 },     // 19
                                   {-4, -1,   1,    7,   -6 }    // 7
                                  };

    public static void maxSumSubmatrix(int[][] input) {

        int maxRowStart = 0;
        int maxRowEnd = 0;
        int maxColumnStart = 0;
        int maxColumnEnd = 0;
        int maxSum = 0;

        // fix the rows upper as i and lower as j
        for(int i = 0; i< input.length; i++) {
            for( int j = i ; j< input.length; j++) {

                int[] tempSum = new int[input[0].length];

                //calculate sum per column between rows i and j
                for(int k = 0; k< tempSum.length; k++) {

                    for( int l = i; l<= j; l++) {
                        tempSum[k] += input[l][k];
                    }
                }

                Pair p = findMaxSum(tempSum);
                if(maxSum < p.sum) {
                    maxSum = p.sum;
                    maxRowStart = i;
                    maxRowEnd = j;
                    maxColumnStart = p.col1;
                    maxColumnEnd = p.col2;
                }

            }
        }


        System.out.println("Max Sum :"+ maxSum + " Which lies in left Top :"+ maxRowStart+","+maxColumnStart+ " Ends at right bottom :"+ maxRowEnd+ " ,"+ maxColumnEnd);

    }

    static class Pair {
        public int col1;
        public int col2;
        public int sum;

        Pair(int col1, int col2, int sum) {
            this.col1 = col1;
            this.col2 = col2;
            this.sum = sum;
        }
    }

    public  static Pair findMaxSum(int[] input) {

        int maxSum = 0;
        int maxStart = 0;
        int maxEnd = 0;
        int sum = 0;

        for(int i=0; i< input.length; i++) {
            if(input[i] + sum > 0){
                sum += input[i];
                if(sum > maxSum) {
                    maxSum = sum;
                    maxEnd = i;
                }

            } else {
                sum = 0;
                maxStart = i+1;
                maxEnd = i+1;
            }
        }


        return new Pair(maxStart, maxEnd, maxSum);

    }


    public static void main(String[] args) {

        maxSumSubmatrix(input);

    }



}
