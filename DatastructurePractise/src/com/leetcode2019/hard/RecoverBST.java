package com.leetcode2019.hard;

import com.datastructure2019.tree.TreeNode;

public class RecoverBST {
    public static void recoverBST(TreeNode root) {
        TreeNode[] aux = new TreeNode[3];
        aux[0] = new TreeNode(Integer.MIN_VALUE);
        aux = traverse(root, aux);
        if (aux[1] != null && aux[2] != null) {
            int temp = aux[1].val;
            aux[1].val = aux[2].val;
            aux[2].val = temp;
        }
    }

    public static TreeNode[] traverse(TreeNode root, TreeNode[] aux) {
        if (root == null) return aux;
        aux = traverse(root.left, aux);
        /**
         * Trick is : Assign the previous to first value and root to second. the second one would get updated if we find another anomaly.
         */
        if (root.val < aux[0].val) {
            if (aux[1] == null) {
                aux[1] = aux[0];
            }  // no else here
            if (aux[1] != null) {
                aux[2] = root;
            }
        }

        aux[0] = root;
        aux = traverse(root.right, aux);
        return aux;
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(3)
                .setLeft(
                        new TreeNode(1))
                .setRight(new TreeNode(4).setLeft(new TreeNode(2)));
        recoverBST(tree);
    }
}
