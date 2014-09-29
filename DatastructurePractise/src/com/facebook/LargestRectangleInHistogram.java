package com.facebook;

import java.util.LinkedList;

/**
 * Created by abhimaloo on 9/28/14.
 */
public class LargestRectangleInHistogram {

    public static int[] hist = {2,3,5,1,6,7,8};

    public static int getMaxAreaBruteForce(int[] hist) {
        if(hist == null || hist.length == 0){
            return -1;
        }
        int maxArea = 0;

        for( int i = 0; i< hist.length; i++) {
            int minValue = hist[i];
            for(int j = i; j >= 0; j--) {
                minValue = Math.min(minValue, hist[j]);
                int area = minValue * (i-j +1);
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }


    public static int getMaxArea(int[] histogram) {
        LinkedList<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        int i = 0;

        while(i < histogram.length) {
            if(stack.isEmpty() || histogram[stack.peek()] < histogram[i]) {
                stack.push(i++);
            } else {
                int stackTop = stack.peek();
                stack.pop();
                int stackTopArea = histogram[stackTop] * (stack.isEmpty()? i: i - stack.peek() -1);
                maxArea = Math.max(maxArea, stackTopArea);
            }
        }

        while(!stack.isEmpty()) {
            int stackTop = stack.peek();
            stack.pop();
            int stackTopArea = histogram[stackTop] * (stack.isEmpty()? i: i - stack.peek() -1);
            maxArea = Math.max(maxArea, stackTopArea);
        }

        return maxArea ;
    }

    public static void main(String[] args) {
        System.out.println(getMaxAreaBruteForce(hist));
        System.out.println(getMaxArea(hist));
    }

}
