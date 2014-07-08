package com.maloo.dynamicprogramming;

/**  Application of DP -
 * Find a max weight independent set given a graph with vertices v
 * Adjacent vertices can not be part of the solution
 * Created by abhishekm787 on 7/7/14.
 */
public class MaxWeightIndependentSet {
    public static int[] vertices = {1,2,3,4,5,6,7};
    public static int[] weights = {6,1,3,5,3,8,4};

    public static void findMaxWeight(){
        int[] maxWeight = new int[vertices.length+1];
        maxWeight[0] = 0;
        maxWeight[1] = weights[0];

        // find the maximum of maxWeight without this node  .. or with this node
        for(int i=2; i <maxWeight.length; i++) {
           maxWeight[i] =  Math.max(maxWeight[i-1], maxWeight[i-2]+weights[i-1]);
        }


        //recosntruct the solution
        for(int i = vertices.length -1; i>=0; i--){
            if(maxWeight[i+1] != maxWeight[i]){
                System.out.print(" "+vertices[i]+" ");
                i--;
            }
        }

    }

    public static void main(String[] args) {
       findMaxWeight();
    }
}
