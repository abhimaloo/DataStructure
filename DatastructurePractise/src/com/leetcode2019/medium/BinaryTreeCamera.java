package com.leetcode2019.medium;

import com.datastructure2019.tree.TreeNode;

/*
https://leetcode.com/problems/binary-tree-cameras/
 */
public class BinaryTreeCamera {
    public static int NOT_MONITORED = 0;
    public static int MONITORED_NO_CAM = 1;
    public static int MONITORED_CAM = 2;
    public static int numCam = 0;

    public int minCamera(TreeNode root) {
        int top = dfs(root);
        if (top == NOT_MONITORED) {
            numCam++;
        }

        return numCam;
    }

    /*
    Solution: Go in post order.
    Every root should only be responsible for its childrens monitoring
    Three cases for the root -
    1) Both the child are monitoredNotCam; return NOt Monitored
    2) either of the child is not monitored; put the camera and return Monitored with camera
    3) else ; Monitored NO camera

     */
    public int dfs(TreeNode root) {
        if (root == null) {
            return MONITORED_NO_CAM;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        if (left == MONITORED_NO_CAM && right == MONITORED_NO_CAM) {
            return NOT_MONITORED;
        }

        if (left == NOT_MONITORED || right == NOT_MONITORED) {
            numCam++;
            return MONITORED_CAM;
        } else {
            return MONITORED_NO_CAM;
        }
    }

}
