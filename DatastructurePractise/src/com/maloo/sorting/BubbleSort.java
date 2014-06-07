package com.maloo.sorting;

/**
 * Created by abhishekm787 on 6/6/14.
 */
public class BubbleSort {

    public static int[] input = {4,1,2,8,3,7,5,9};

    //Good : Its good for smaller lists
    // Best Case complexity is O(n)
    // Best for checking whether the list is sorted or not
    public static int[] bubbleSort(int[] input) {
        //Biggest Element bubbles to the last in every itreration
        //Next iteration will ignore the biggest elements which are organized in previous
        //iterations
        for (int i =0; i < input.length; i++) {
            for(int j = 1; j <input.length-i; j++ ) {
                if(input[j] < input[j-1]) {
                    int temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }

        return input;
    }

    public static void print(int[] input) {
        for(int i =0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }

    public static void main(String[] args) {

        bubbleSort(input);
        System.out.println("After Sort");
        print(input);
    }

}
