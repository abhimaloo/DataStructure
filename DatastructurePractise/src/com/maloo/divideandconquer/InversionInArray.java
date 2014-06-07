package com.maloo.divideandconquer;

import java.util.Arrays;

public class InversionInArray {

	public static int[] input = {1,6,2,4,8,3};
	
	public static Holder findInversion(Holder input) {
		if(input.sortedInput.length<=1) {
			return new Holder(input.sortedInput, 0);
		}
		
		int mid = (input.sortedInput.length)/2;
		
		Holder leftInversion = findInversion(new Holder(Arrays.copyOfRange(input.sortedInput, 0, mid), input.numOfInversion));
		
		Holder rightInversion = findInversion(new Holder(Arrays.copyOfRange(input.sortedInput, mid,input.sortedInput.length ),input.numOfInversion ));
		
		Holder splitInversion = findSplitInversion(leftInversion.sortedInput, rightInversion.sortedInput);
		
			
		return new Holder(splitInversion.sortedInput, input.numOfInversion+ leftInversion.numOfInversion+rightInversion.numOfInversion+splitInversion.numOfInversion);
		
	}
	
	private static Holder findSplitInversion(int[] left, int[] right) {
		int[] merged = new int[left.length+right.length];
		int leftHead =0; int rightHead = 0;
		int splitInversions = 0;
		for(int i=0; i<left.length+right.length; i++) {
			
			if(rightHead < right.length && leftHead<left.length &&  left[leftHead]<right[rightHead]) {
				merged[i] = left[leftHead];
				leftHead++;
			} else if(rightHead < right.length && leftHead<left.length && left[leftHead]>right[rightHead]){
				merged[i] = right[rightHead];
				rightHead ++;
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
