package com.leetcode2019.medium;

/*
https://leetcode.com/problems/regions-cut-by-slashes/
In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.

(Note that backslash characters are escaped, so a \ is represented as "\\".)

Return the number of regions.



Example 1:

Input:
[
  " /",
  "/ "
]
Output: 2
Explanation: The 2x2 grid is as follows:

Example 2:

Input:
[
  " /",
  "  "
]
Output: 1
Explanation: The 2x2 grid is as follows:

Example 3:

Input:
[
  "\\/",
  "/\\"
]
Output: 4
Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
The 2x2 grid is as follows:

Example 4:

Input:
[
  "/\\",
  "\\/"
]
Output: 5
Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
The 2x2 grid is as follows:

Example 5:

Input:
[
  "//",
  "/ "
]
Output: 3
Explanation: The 2x2 grid is as follows:



Note:

1 <= grid.length == grid[0].length <= 30
grid[i][j] is either '/', '\', or ' '.
 */
public class RegionCutBySlashes {

    public int regionsBySlashes(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        // for union find
        int[] root = new int[(m + 1) * (n + 1)];
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }

        int totalRegion = 1;

        // first connect the outer nodes to build the rectangle
        for (int i = 1; i < m + 1; i++) {
            int x = (i - 1) * (n + 1);  // its in form of x*n + y; here y is 0
            int y = i * (n + 1);
            union(root, x, y);
            int x1 = (i - 1) * (n + 1) + n;
            int y1 = (i * (n + 1)) + n;
            union(root, x1, y1);
        }

        for (int i = 1; i < n + 1; i++) {
            int x = i - 1;   // its in form of x*n + y; here y is 0
            int y = i;
            union(root, x, y);
            int x1 = ((m + 1) * n) + i - 1;
            int y1 = ((m + 1) * n) + i;
            union(root, x1, y1);
        }
        // lets connect the regions

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == '/') {
                    totalRegion += union(root, (i * (n + 1)) + j + 1, (i + 1) * (n + 1) + j);
                } else if (grid[i].charAt(j) == '\\') {
                    totalRegion += union(root, (i * (n + 1)) + j, (i + 1) * (n + 1) + j + 1);
                }
            }
        }
        return totalRegion;
    }

    public int union(int[] root, int i, int j) {
        int region = 0;
        int rootI = find(root, i);
        int rootJ = find(root, j);

        if (rootI != rootJ) {
            root[rootI] = rootJ;
        } else {
            region++;
        }
        return region;
    }

    public int find(int[] root, int i) {
        while (root[i] != i) {
            i = root[i];
        }
        return i;
    }

    public static void main(String[] args) {
        RegionCutBySlashes region = new RegionCutBySlashes();
        System.out.println(region.regionsBySlashes(new String[]{"\\/", "/\\"}));
    }

}
