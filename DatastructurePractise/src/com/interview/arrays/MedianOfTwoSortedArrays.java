package com.interview.arrays;

/** Problem is to find median int the result when two sorted arrays would be merged
 * for ex -input1 -  {1,3,4,5,7},  input 2 - {2,9,11,12,45}
 * Output should be 6 since merged array would be {1,2,3,4,5,7,9,11,12,45}
 * Created by abhishekm787 on 7/22/14.
 */
public class MedianOfTwoSortedArrays {

    /**
     * trick is to find medians of both the arrays and compare with each other
     * if a[med1] == b[med2] ..it means we have found the median, return wither a[med1] or b[med2]
     * if a[med1] < b[med2] .. it means median lies between med .. end1 and start2 .. to med2
     * if a[med1] > b[med2] .. it means median lies between 0 ..med1 and med2.. end2
     *  keep finding medians recursively..once both the arrays have size equals to 2
     *  return median  - (max(a[start], b[start]) + min(a[end], b[end]))/2
     *
     * @param array1
     * @param array2
     * @return
     */
    public static int findMedian(int[] array1, int[] array2) {
        int start1 = 0;
        int end1 = array1.length-1;
        int start2 = 0;
        int end2 = array2.length -1;

        while(start1+1!=end1 || start2+1!=end2) {
            int median1 = (end1 + start1)/2;
            int median2 = (end2 + start2)/2;

            if(array1[median1]== array2[median2]){
               return array1[median1];
            } else if( array1[median1] < array2[median2] ) {
                start1 = median1;
                end2 = median2;
            } else {
                end1 = median1;
                start2 = median2;
            }
        }

        int median = (Math.max(array1[start1], array2[start2]) + Math.min(array1[end1], array2[end2]))/2;
        return median;
    }

    public static void main(String[] args) {
        int [] input1 = {1,3,4,5,7};
        int [] input2 = {2,9,11,12,45};
        System.out.println(findMedian(input1, input2));
    }
}
