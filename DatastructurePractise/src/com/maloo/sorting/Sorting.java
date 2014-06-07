package com.maloo.sorting;

import java.util.Arrays;

public class Sorting {

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
	
	
	
	public static int[] selectionSort(int[] input) {
		//goes form left to right
		//finds minimum element and swaps it with the left edge element
		for(int i=0; i <input.length-1; i++) {
			int minIndex = i;
			for(int j = i+1; j<input.length; j++) {
				if(input[j] <input[minIndex]) {
					minIndex = j;
				}
			}
			if(minIndex != i) {
				int temp  = input[i];
				input[i] = input[minIndex];
				input[minIndex] = temp;
			}	
			
		}
		
		return input;
	}
	
	
	// Start again from second element and compare it with rest of the elements on left
	//find its right place and shift all the elements on left accordingly
	//in each iteration 
	
	public static int[] insertionSort(int[] input) {
		for(int i=1; i<input.length-1; i ++) {
			int pivot = input[i];  // save the pivot element.. since while shifting it will get lost
			int j =i-1;   // set the J to be one less than i
			while(j>=0 && pivot < input[j] ){  // till J is greater than or equals to zero and pivot is less than the element in comparsion
				input[j+1] = input[j];  // do a right shift of element
				j--;
			}
			if(j!=i-1){   // if J has changed a bit means right shift has happened .. set the pivot to its correct place
				input[j+1] = pivot;
			}
		}
		
	    return input;
	}
	
	
	public static int[] mergeSort(int[] input) {
		if(input.length <= 1) {
			return input;
		}
		
		
		//First Half
	    int[] leftArray = Arrays.copyOfRange(input, 0, (input.length/2));
		//Second Half
		int[] rightArray = Arrays.copyOfRange(input, (input.length/2),input.length);
		
		return merge(mergeSort(leftArray), mergeSort(rightArray));
	
	}
	
	
	
	
	private static int[] merge(int[] left, int[] right) {
		int[] merged = new int[left.length + right.length];
		int mergedTail = left.length + right.length -1; 
		int leftTail = left.length -1; 
		int rightTail = right.length -1;
		
		while(leftTail>=0 && rightTail>=0) {
			if(left[leftTail] >= right[rightTail]) {
				merged[mergedTail] = left[leftTail];
				leftTail --;
				mergedTail --;
			}else {
				merged[mergedTail] = right[rightTail];
				rightTail --;
				mergedTail --;
			}
		}
		
		if(leftTail>=0) {
			while(leftTail>=0) {
				merged[mergedTail] = left[leftTail];
				leftTail --;
				mergedTail --;
			}
		} else if(rightTail >=0) {
			while(rightTail>=0) {
				merged[mergedTail] = right[rightTail];
				rightTail --;
				mergedTail --;
			}
		}
				
		return merged;
	}


	
	
	//choose a pivot 
	// separate elements higher than pivot to right side and lower than pivot to left
	//recursively keep doing this on left array and right array
	public static void quickSort(int[] input, int start, int end) {
		if(start>=end) {
			return;
		} else {
			int pivot = start; //choose a random pivot
			int newPivot = partition(input, start, end, pivot);
			quickSort(input, start, newPivot-1);
			quickSort(input, newPivot+1, end);
		}
		
	}
	

	private static int partition(int[] input, int start, int end, int pivot) {
		int i = pivot+1;
		int index = pivot +1;
		for(;index<=end;index++) {
			
			if(input[index] < input[pivot]) {
				int temp = input[i];
				input[i] = input[index];
				input[index] = temp;
				i++;
			}
			
		}
		int temp = input[i-1];
		input[i-1] = input[pivot];
		input[pivot] = temp;
		return i-1;
		
		
	}
	
	


    // kth largest element
	public static int quickSelect(int[] input, int start, int end, int k, int length) {
		
		if(start>=end) {
			return input[start];
		} else {
			int pivot = start; //choose a random pivot
			int newPivot = partition(input, start, end-1, pivot);
			if(newPivot > (length-k)) {
				return quickSelect(input, start, newPivot-1, k, length);
			} else if(newPivot<(length-k)){
				return quickSelect(input, newPivot+1, end, k , length);
			} else {
				return input[newPivot];
			}
			
			
		}
		
	}
	
	public static void print(int[] input) {
		for(int i =0; i < input.length; i++) {
			System.out.println(input[i]);
		}
	}
	
	
	
	public static void main(String[] args) {
		
		quickSort(input,0, input.length-1);
		System.out.println("After Sort");
		print(input);
	}

}
