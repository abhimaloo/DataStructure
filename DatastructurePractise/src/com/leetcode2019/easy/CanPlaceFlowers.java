package com.leetcode2019.easy;

public class CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length && n > 0; i++) {
            if (flowerbed[i] == 0) {
                if ((i - 1 < 0 || (i - 1 >= 0 && flowerbed[i - 1] != 1)) && (i + 1 >= flowerbed.length || (i + 1 < flowerbed.length && flowerbed[i + 1] != 1))) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }

        return n == 0;
    }

    public static void main(String[] args) {
        System.out.println(canPlaceFlowers(new int[]{0, 0, 1, 0, 0}, 1));
    }
}
