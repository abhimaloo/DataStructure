package com.maloo.divideandconquer;

import java.util.Arrays;

/**
 * Inversion Count for an array indicates – how far (or close) the array is from being sorted.
 * If array is already sorted then inversion count is 0.
 * If array is sorted in reverse order that inversion count is the maximum.
 * ex - {1,6,2,4,8,3}
 * output 5  - {6,2}, {6,4},{6,3}, {8,3} and {4,3}
 *
 */
public class InversionInArray {

	public static int[] input = {1,6,2,4,8,3};

    /**
     * trick is to sort the array like merge sort
     * and use merge routine of merge sort to find inversions in the array
     *
     * Its a divide and conquer approach
     * @param input
     * @return
     */
	public static Holder findInversion(Holder input) {
		if(input.sortedInput.length<=1) {
			return new Holder(input.sortedInput, 0);
		}
		
		int mid = (input.sortedInput.length)/2;
		
		Holder leftInversion = findInversion(new Holder(Arrays.copyOfRange(input.sortedInput, 0, mid), input.numOfInversion));
		
		Holder rightInversion = findInversion(new Holder(Arrays.copyOfRange(input.sortedInput, mid,input.sortedInput.length ),input.numOfInversion ));

		//thats where the meat of the code is
		Holder splitInversion = findSplitInversion(leftInversion.sortedInput, rightInversion.sortedInput);
		
			
		return new Holder(splitInversion.sortedInput, input.numOfInversion+ leftInversion.numOfInversion+rightInversion.numOfInversion+splitInversion.numOfInversion);
		
	}

    /**
     * this is similar to merge subroutine in merge sort
     * @param left
     * @param right
     * @return
     */
	private static Holder findSplitInversion(int[] left, int[] right) {
		int[] merged = new int[left.length+right.length];
		int leftHead =0; int rightHead = 0;
		int splitInversions = 0;

		for(int i=0; i<left.length+right.length; i++) {
			
			if(rightHead < right.length && leftHead<left.length &&  left[leftHead]<right[rightHead]) {
				merged[i] = left[leftHead];
				leftHead++;
			} else if(rightHead < right.length && leftHead<left.length && left[leftHead]>right[rightHead]){
				//this is the case where inversion happens .. an element from right subarray
				// is lesser than an element of left subArray
                merged[i] = right[rightHead];
				rightHead ++;
                // it means that the element on right subarray is smaller than rest of the elements on left subarray
                //hence increase the inversion count by that amount
                // *VeryImportant
				splitInversions+=left.length - leftHead;
			} else if(leftHead>=left.length) {
				merged[i] = right[rightHead];
				rightHead ++;
			} else if(rightHead >= right.length) {
				merged[i] = left[leftHead];
				leftHead++;
			} 
			
		}
		
		return new Holder(merged, splitInversions);
	}
	
	public static void print(int[] input) {
		for(int i =0; i < input.length; i++) {
			System.out.println(input[i]);
		}
	}
	
	public static void main(String[] args) {
		Holder result = findInversion(new Holder(input, 0));
		print(result.sortedInput);
		System.out.println("No of Inversion :"+ result.numOfInversion);

	}

    /**
     * Holder is just a conatiner class which keeps the sorted array as an output and inversion count in that array
     */
    static class Holder {

        public int[] sortedInput;
        public int numOfInversion;
        public Holder(int[] sortedInput, int numOfInversion) {
            super();
            this.sortedInput = sortedInput;
            this.numOfInversion = numOfInversion;
        }


    }

}
