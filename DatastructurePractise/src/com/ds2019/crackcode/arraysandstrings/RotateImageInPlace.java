package com.ds2019.crackcode.arraysandstrings;

public class RotateImageInPlace {
    public static int[][] input = {
            {1, 2, 4, 5},
            {5, 6, 7, 6},
            {8, 9, 8, 7},
            {3, 6, 9, 2}
    };

    /**
     * Solution : Take one square (parimeter wise) at a time and rotate it. Then move to inner square and keep following
     * <p>
     * Optimization
     * For Square metrics: left and top can be represented with same variable - start
     * and right and bottom can be represented as end
     *
     * @param input
     * @return
     */
    public static int[][] rotate(int[][] input) {
        int left = 0;
        int right = input.length - 1;
        int top = 0;
        int bottom = input.length - 1;

        for (int i = 0; i < input.length / 2; i++) {

            for (int index = 0; index < right - left; index++) {
                int temp = input[top][left + index];
                input[top][left + index] = input[top + index][right];
                input[top + index][right] = input[bottom][right - index];
                input[bottom][right - index] = input[bottom - index][left];
                input[bottom - index][left] = temp;
            }
            left++;
            right--;
            top++;
            bottom--;
        }

        return input;
    }

    public static void print(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                System.out.print(" " + input[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print(input);
        rotate(input);
        System.out.println();
        print(input);

    }
}
