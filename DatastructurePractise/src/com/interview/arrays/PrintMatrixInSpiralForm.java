package com.interview.arrays;

/**
 * Given a 2D array, print it in spiral form. See the following examples.
 * Input:
 *  1    2   3   4
 *  5    6   7   8
 *  9   10  11  12
 *  13  14  15  16
 *  Output:
 *  1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
 * Created by abhimaloo on 7/24/14.
 */
public class PrintMatrixInSpiralForm {
    public static int[][] input = { {1,2,3,4,20},
                                    {5,6,7,8,21},
                                    {9,10,11,12,22},
                                    {13,14,15,16,23}};

    public static void printMatrixInSpiral(int [][] input) {
        int leftTop = 0;
        int rightTop = input[0].length;
        int leftBottom = -1;
        int rightBottom = input.length;
        int i=0; int j=0; int count =0;
        while(count<input.length * input[0].length){
            while(true){
                if(j<rightTop){
                    System.out.println(" "+input[i][j++]+" ");
                    count++;
                } else {
                    rightTop--;
                    j--;
                    i++;
                    break;
                }
            }

            while(true) {
                if(i<rightBottom){
                    System.out.println(" "+input[i++][j]+" ");
                    count++;
                } else{
                    i--;
                    j--;
                    rightBottom--;
                    break;
                }
            }

            while(true) {
                if(j>leftBottom){
                    System.out.println(" "+input[i][j--]+" ");
                    count++;
                } else{
                    j++;
                    i--;
                    leftBottom++;
                    break;
                }
            }

            while(true) {
                if(i>leftTop){
                    System.out.println(" "+input[i--][j]+" ");
                    count++;
                } else{
                    i++;
                    j++;
                    leftTop++;
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        printMatrixInSpiral(input);
    }
}
