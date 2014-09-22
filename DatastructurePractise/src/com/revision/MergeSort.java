package com.revision;

/**
 * Created by abhimaloo on 9/21/14.
 */
public class MergeSort {
    public static int[] input ={5,3,1,2,4,6};
    public static void sort(int[] input, int low , int high) {
        if(low < high) {
            int mid = low + (high - low)/2;
             sort(input, low, mid);
             sort(input, mid+1, high);
             merge(input, low, mid, high);

        }
    }

    private static void merge(int[] input, int low, int mid, int high) {
        int[] back = new int[high - low +1];
        int leftHead = low;
        int leftTail = mid;
        int rightHead = mid+1;
        int rightTail = high;
        int mergehead = 0;

        while(leftHead <= leftTail && rightHead <= rightTail) {
            if(input[leftHead] < input[rightHead]) {
                back[mergehead] = input[leftHead];
                leftHead++;
                mergehead++;
            } else {
                back[mergehead] = input[rightHead];
                rightHead++;
                mergehead ++;
            }

        }

        while(leftHead <= leftTail){
            back[mergehead++] = input[leftHead++];
        }

        while(rightHead <= rightTail) {
            back[mergehead++] = input[rightHead++];
        }

        System.arraycopy(back, 0 ,input, low, high - low +1);

    }

    public static void main(String[] args) {
        sort(input, 0, input.length-1);
        for(int i: input){
            System.out.println(i);
        }
    }

}
