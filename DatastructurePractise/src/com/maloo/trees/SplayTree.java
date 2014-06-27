package com.maloo.trees;

/**
 * Very Tricky to implement
 * Created by abhishekm787 on 6/27/14.
 */
public class SplayTree<T extends  Comparable<T>> {

    public Node<T> root = null;

    public Node<T> insert(Node<T> root, Node<T> parent, T data, String relationship){

        if(root ==null){
            Node<T> newNode = new Node<>();
            newNode.data = data;
            newNode.parent = parent;
            root = newNode;
            //splay the root to the top
            root = splay(root,relationship);
            return root;
        }

        if(data.compareTo(root.data) <= 0){
           root.left = insert(root.left, root,data,relationship+"0");
        } else {
            root.right = insert(root.right, root,data,relationship+"1");
        }

        return root;
    }


    private Node<T> splay(Node<T> root, String relationship) {

        //root node
        if(relationship==""){
            return root;
        }

        if(relationship.length()==1){
            switch(relationship) {
                case "0" :
                    //apply right rotation
                    relationship = relationship.substring(0, relationship.length()-1);
                    break;

                case "1" :
                    //apply left rotation
                    relationship = relationship.substring(0, relationship.length()-1);
                    break;
            }
        } else {

            switch(relationship.substring(relationship.length()-2)) {

                case "00" :
                    //left child of left child .. apply zig zig
                    relationship = relationship.substring(0, relationship.length()-2);
                    break;

                case "11" :
                    //right child of right child
                    relationship = relationship.substring(0, relationship.length()-2);
                    break;
                case "01" :
                    //right child of left child
                    relationship = relationship.substring(0, relationship.length()-2);
                    break;
                case "10" :
                    // left child of right child
                    relationship = relationship.substring(0, relationship.length()-2);
                    break;

            }

        }

        while(root.parent!=null){
            root = splay(root,relationship);
        }

        return root;
    }


    private Node<T> rotateRight(Node<T> root) {

        //save root's grandparent
        Node<T> tempParent = root.parent.parent;

        // pluck roots right child and make it parents left child
        root.parent.left = root.right;
        root.right.parent = root.parent;

        // make the parent right child of root
        root.right = root.parent;
        root.parent.parent = root;

        // update root's parent as itd grandfather
        root.parent = tempParent;

        return root;
    }


    public static void main(String[] args) {
        SplayTree<Integer> sp = new SplayTree<>();
        sp.root = sp.insert(sp.root,null,10,"");
        System.out.println("Hello");
    }



    private class Node<T extends  Comparable<T>>{
        public T data = null;
        public Node<T> left = null;
        public Node<T> right = null;
        public Node<T> parent = null;
    }
}
