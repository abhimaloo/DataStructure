package com.facebook;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class JumpGame {

    public static int[] input = {2,3,1,1,4};

    public static int minJump(int[] input) {
        if(input == null || input.length == 0 || input[0] == 0){
            return -1;
        }

        int[] minJump = new int[input.length+1];
        minJump[0] = 0;


        for(int i = 1; i<= input.length; i++) {
            minJump[i] = Integer.MAX_VALUE;
            for(int j = 0; j <i ; j++) {
                if(minJump[j] + input[j] >= i && minJump[i] > minJump[j]) {
                    minJump[i]  = minJump[j] + 1;
                }
            }

        }

        return minJump[input.length-1];
    }

    public static void main(String[] args) {
        System.out.println(minJump(input));
    }
}
