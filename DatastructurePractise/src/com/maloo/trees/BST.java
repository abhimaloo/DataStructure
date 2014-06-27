package com.maloo.trees;

import java.util.LinkedList;

/**
 * This is a binary search tree implementation
 */
public class BST<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T data) {

      root = insertInternal(root, data);
    }

    private Node<T> insertInternal(Node<T> root, T data) {
        if(root == null) {
            Node<T> newNode = new Node<T>();
            newNode.data = data;
            newNode.left = null;
            newNode.right = null;
            root = newNode;
            return root;
        }

        if(root.data.compareTo(data) <0) {
            //this is how you update the parent
           root.right =  insertInternal(root.right,data);
        } else {
            // this is how you update the parent
            root.left = insertInternal(root.left, data);
        }

        return root;
    }


    public Node<T> find(Node<T> root, T data) {
        if(root == null) {
            return null;
        }
        if(root.data.compareTo(data) ==0) {
            return root;
        } else if(root.data.compareTo(data) <1){
            return find(root.right,data);
        } else {
            return find(root.left, data);
        }


    }

    public void preorder(Node<T> root){
        if(root == null) {
            return;
        }

        System.out.println(root.data.toString());
        preorder(root.left);
        preorder(root.right);
    }

    public void inorder(Node<T> root){
        if(root == null) {
            return;
        }

        preorder(root.left);
        System.out.println(root.data.toString());
        preorder(root.right);
    }

    public void postorder(Node<T> root){
        if(root == null) {
            return;
        }

        preorder(root.left);
        preorder(root.right);
        System.out.println(root.data.toString());
    }


    public void levelOrder(Node<T> root){
        LinkedList<Node<T>> queue = new LinkedList<>();
        LinkedList<Integer> levelqueue = new LinkedList<>();
        int level = 0;
        queue.addLast(root);
        levelqueue.addLast(level);
        while(!queue.isEmpty()){
            Node<T> processed = queue.removeFirst();
            level = levelqueue.removeFirst();
            System.out.println("Level :"+level+", Data : "+processed.data.toString());
            level ++;
            if(processed.left!=null) {
                queue.addLast(processed.left);
                levelqueue.addLast(level);
            }

            if(processed.right!=null){
                queue.addLast(processed.right);
                levelqueue.addLast(level);
            }

        }

    }


    /**
     * This method prints all the paths from root to all the leaf nodes
     * @param root
     * @param path
     */
    public void printAllPaths(Node<T> root, String path) {
        //edge case of null root
        if(root ==null) {
            return;
        }

        //append the data of the root to path
        path+=" "+root.data.toString()+" ";

        //base case : for a leaf node , left and right child has to be null
        if(root.left == null && root.right ==null) {
            System.out.println(path);
            return;
        } else {
            //recurse appropriately to the left child first
            if(root.left !=null) {
                printAllPaths(root.left, path);
            }

            //then to the right child
            if(root.right!=null) {
                printAllPaths(root.right, path);
            }

        }


    }


    public Node<T> findLowestCommonAncestor(Node<T> root, T data1, T data2){
        if(root ==null){
            return null;
        }

        if(contains(root.left, data1) && contains(root.left, data2)){
            return findLowestCommonAncestor(root.left, data1, data2);
        }
        if(contains(root.right,data1) && contains(root.right, data2)){
            return findLowestCommonAncestor(root.right, data1, data2);
        }

        if((contains(root.left,data1) && contains(root.right, data2) || contains(root.left,data2) && contains(root.right, data1))) {
            return root;
        }

        return null;
    }

    public boolean contains(Node<T> root, T data){

        if(root.data.compareTo(data) ==0){
            return true;
        }
        if(root.left!=null){
            return contains(root.left, data);
        }
        if(root.right!=null) {
            return contains(root.right, data);
        }
       return false;
    }

    public boolean isBalanced(Node<T> root) {
        if(root == null) {
            return false;
        }


      return !(java.lang.Math.abs(findMaxDepth(root,0)-findMinDepth(root,0)) >1);
    }

    public int findMaxDepth(Node<T> root, int currentDepth) {

        if(root.left == null && root.right ==null) {
            return currentDepth;
        }
        int leftDepth = 0;
        int rightDepth = 0;
        currentDepth ++;

        if(root.left!=null){
            leftDepth = findMaxDepth(root.left,currentDepth);
        } if(root.right!=null) {
            rightDepth = findMaxDepth(root.right,currentDepth);
        }

       return java.lang.Math.max(leftDepth,rightDepth);
    }

    public int findMinDepth(Node<T> root, int currentDepth) {

        if(root.left == null && root.right ==null) {
            return currentDepth;
        }
        int leftDepth = 0;
        int rightDepth = 0;
        currentDepth ++;

        if(root.left!=null){
            leftDepth = findMaxDepth(root.left,currentDepth);
        } if(root.right!=null) {
            rightDepth = findMaxDepth(root.right,currentDepth);
        }

        return java.lang.Math.min(leftDepth, rightDepth);
    }



    public T findMin(Node<T> root) {
        if(root.left ==null) {
            return root.data;
        }

        return findMin(root.left);
    }


    public boolean delete(Node<T> root,T data, Node<T> parent, int compareResult) {


        //check the not found case
        if(root == null){
            return false;
        }

        // if we find the match
        if(root.data.compareTo(data) == 0) {
            // THere are three cases

            //case 1 : target has no children
            if(root.left ==null && root.right ==null) {
                setParentAccordingToLastComparision(parent,null,compareResult);
                return true;
            }
            //case 2 : target has only one subtree, either left or right
            else if(root.left!=null && root.right == null) {
                setParentAccordingToLastComparision(parent,root.left,compareResult);
                return true;
            } else if(root.left==null && root.right != null) {
                setParentAccordingToLastComparision(parent,root.right,compareResult);
                return true;
            }

            //case 3: target has both the children present
            else if(root.left!=null && root.right != null) {
                //its the tricky one
                Node<T> cursor = root;
                //find the minimum node after the found node and save its data
                T minValue= findMin(cursor);
                //delete the minimum node recursively
                delete(root,minValue,parent,compareResult);
                //copy the minimum node data
                root.data = minValue;
                return true;
            }

        }
        // recurse on left or right subtree according to the situation
        else if(root.data.compareTo(data) < 0){
           return delete(root.right,data,root, root.data.compareTo(data));
        } else {
           return delete(root.left,data,root, root.data.compareTo(data));
        }

       return false;
    }

    private void setParentAccordingToLastComparision(Node<T> parent, Node<T> child, int compareResult) {
        if(compareResult>0){
            parent.left = child;
        } else if(compareResult<0){
            parent.right = child;
        } else {
            //have to handle edge case when root is getting deleted
            this.root = child;
        }
    }

    public static BST<Integer> minHeightBinaryTree(int[] array, int start, int end, BST<Integer> tree ) {

        if(start>end){
            return null;
        }

        int mid = (start + end)/2;
        tree.insert(array[mid]);
        minHeightBinaryTree(array,start, mid-1, tree);
        minHeightBinaryTree(array, mid+1, end, tree);

        return tree;

    }

    public boolean containsTree(Node<T> root, Node<T> otherRoot) {

        if(root ==null || otherRoot ==null){
            return false;
        }

        if(root.data.compareTo(otherRoot.data)==0){
           if(matchTree(root,otherRoot,false)){
               return true;
           }
        }

        if(root.left!=null){
            if(containsTree(root.left,otherRoot)){
                return true;
            }
        }

        if(root.right!=null) {
            if(containsTree(root.right,otherRoot)){
                return true;
            }
        }

        return false;


    }

    public boolean matchTree(Node<T> root1, Node<T> root2, boolean matchFound){

        if(root1 ==null || root2 ==null){
            matchFound = false;
            return matchFound;
        }

        if(root1.data.compareTo(root2.data) ==0){
            matchFound = true;
            if(root1.left!=null) {
                matchFound= matchFound && matchTree(root1.left,root2.left,matchFound);
            }
            if(root1.right!=null && matchFound) {
                matchFound= matchFound && matchTree(root1.right,root2.right,matchFound);
            }
        }

        return matchFound;

    }

    /**
     *
     * @param root
     * @return
     */
    public void mirror(Node<T> root) {
        //if reach null return the same root
        if(root ==null){
            return ;
        }

        //go to leftmost element
        mirror(root.left);
        //go to right element
        mirror(root.right);

        // while popping out swap the left and right pointer
        Node<T> temp = root.left;
        root.left = root.right;
        root.right = temp;
    }


    public static boolean isBST(Node<Integer> root, int min, int max) {
        boolean success = false;
        if(root.data > min && root.data <max ){
            success = true;
            if(root.left!=null){
                if(root.data>root.left.data) {
                    success = success && isBST(root.left, min,root.data);
                }else{
                    return false;
                }
            }

            if(root.right!=null && success){
                if(root.data<root.right.data){
                    success = success && isBST(root.right, root.data,max);
                } else{
                    return false;
                }
            }

        } else{
            success = false;
        }
        return success;
    }





    public static void main(String args[]) {
        BST<Integer> bst  = new BST<Integer>();
//        bst.insert(18);
//        bst.insert(12);
//        bst.insert(25);
//        bst.insert(4);
//        bst.insert(15);
//        bst.insert(28);
//        bst.insert(13);
//        bst.insert(17);
//        bst.insert(14);

//          bst.insert(5);
//          bst.insert(2);
//          bst.insert(7);
//          bst.insert(9);
//          bst.insert(1);

        //System.out.println("deleted" +bst.delete(bst.root,12,bst.root,0));
        //bst.levelOrder(bst.root);
        //System.out.println(bst.findLowestCommonAncestor(bst.root,1,9));

        //int []array = {1,2,3,4,5,6,7,8,9};
       // BST<Integer> bst1 =  minHeightBinaryTree(array,0,array.length-1, new BST<Integer>());
       // bst1.preorder(bst1.root);


        bst.insert(5);
        bst.insert(2);
        bst.insert(3);
        bst.insert(1);
        bst.insert(8);
        bst.insert(7);
        bst.insert(6);
        bst.mirror(bst.root);






        System.out.println("found :");




    }



}

class Node<T extends Comparable<T>> {
    public T data;
    public Node<T> left;
    public Node<T> right;
}

