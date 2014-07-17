package com.interview.arrays;

/**
 * The problem is to count all the possible paths from top left to bottom right
 * of a mXn matrix with the constraints that
 * from each cell you can either move only to right or down
 * Created by abhishekm787 on 7/17/14.
 */
public class FindAllPossiblePathsInMatrix {
    public static int input[][] = {{1,1,1,1},
                            {1,1,1,1},
                            {1,1,1,1},
                            {1,1,1,1}};

    /**
     * simple recursion question.. backtrack once you reach the destination
     * @param x
     * @param y
     * @param count
     * @param m
     * @param n
     * @return
     */
    public static int countAllPossiblePath(int x, int y, int  count, int m, int n){
         if(x==m-1 && y==n-1){
            return 1+count;
         }

         if(x+1<m){
           count =  countAllPossiblePath(x+1,y,count,m,n);
         }
         if(y+1<n) {
           count =  countAllPossiblePath( x, y+1, count,m,n);
         }


        return count;
    }

    public static void main(String[] args) {
        System.out.println(countAllPossiblePath( 0, 0, 0,3,3));
    }

}
