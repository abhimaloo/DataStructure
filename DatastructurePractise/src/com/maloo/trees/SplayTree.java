package com.maloo.trees;

/**
 * Very Tricky to implement
 * Its a sort of a balanced binary tree
 * It is heavily utilized for implementing caching
 * Every operation rebalances the tree by moving the action node to root level
 *
 * Created by abhishekm787 on 6/27/14.
 */
public class SplayTree<T extends  Comparable<T>> {

    public Node<T> root = null;

    /**
     * found Node is to keep the node which needs to be splayed to the root
     */
    public Node<T> foundNode ;

    /**
     * relationship is the string which keeps the family tree from root to foundNode
     * 0 represents left child
     * 1 represents right child
     *
     * eg : relationship = "001"  means found node is right child of a left of a left child.
     *
     */
    public String relationship ;


    /**
     * This method inserts a data into splay tree
     * @param data
     */
    public void insert(T data) {
        //initialize the foundNode and relationship to null
         this.foundNode = null;

         this.relationship = null;

         // insert the node
         this.root = insert(this.root,null,data,"");

        //splay the found node to the top
        root = splay(this.foundNode,this.relationship);
    }

    private Node<T> insert(Node<T> root, Node<T> parent, T data, String relationship){

        if(root ==null){
            Node<T> newNode = new Node<>();
            newNode.data = data;
            newNode.parent = parent;
            root = newNode;
            //save the found node and relationship
            this.foundNode = root;
            this.relationship = relationship;

            return root;
        }

        if(data.compareTo(root.data) <= 0){
            // keep adding the relationship accordingly
           root.left = insert(root.left, root,data,relationship+"0");

        } else {

            root.right = insert(root.right, root,data,relationship+"1");
        }

        return root;
    }

    /**
     * This method is for lookup the data
     * @param data
     * @return
     */

    public T find(T data) {

        //initialize the foundNode and relationship to null
        this.foundNode = null;
        this.relationship = null;

        // find the node .. find method will update foundNode(or nearest to the required node) and relationship
        find(this.root,null,data,"");

        //splay the found node to the root
        root = splay(this.foundNode,relationship);

        // if root's data matches to the data requested .. return the data
        if(root!=null && root.data.compareTo(data) ==0){
            return root.data;
        } else {
            // else return null;
            return null;
        }

    }

    private  void find(Node<T> root, Node<T> parent, T data, String relationship) {

        if(root ==null) {
            // set the parent node to be the nearest found node
            this.foundNode = parent;
            // reached a null node.. delete the last acquired relationship
            this.relationship = relationship.substring(0, relationship.length()-1);
            return;
        }

        if(data.compareTo(root.data) == 0){
            // set the found node
            this.foundNode = root;
            // set the relationship
            this.relationship = relationship;

            return;
        } else if(data.compareTo(root.data) < 0) {

           find(root.left, root,data,relationship+"0");

        } else {

            find(root.right, root,data,relationship+"1");
        }
    }


    /**
     * very tricky method ..
     * it recursively splays the found node to root
     *
     *
     * @param root
     * @param relationship
     * @return
     */
    private Node<T> splay(Node<T> root, String relationship) {

        //if supplied node is already root node
        if(relationship==""){
            return root;
        }

        // if the supplied node is either direct left or direct right child of the root
        if(relationship.length()==1){
            //fetch last character of relationship
            switch(relationship) {
                case "0" :
                    //apply right rotation
                    root = rotateRight(root);
                    // delete the last character from relationship
                    relationship = relationship.substring(0, relationship.length()-1);
                    break;

                case "1" :
                    //apply left rotation
                    root = rotateLeft(root);
                    // delete the last character from relationship
                    relationship = relationship.substring(0, relationship.length()-1);
                    break;
            }
        }
        // if the supplied node lives far from the root .. consider parent and grandparent only
        else {

            // fetch last 2 characters from relationship
            switch(relationship.substring(relationship.length()-2)) {

                case "00" :
                    //left child of left child .. apply zag zag
                    //rotate the parent to right
                    root = rotateRight(root.parent);
                    //since the parent has become root.. now rotate the left child which is the original node to right
                    root = rotateRight(root.left);
                    // delete the last 2 characters from relationship
                    relationship = relationship.substring(0, relationship.length()-2);
                    break;

                case "11" :
                    //right child of right child  .. apply zig zig
                    //left rotate the parent
                    root = rotateLeft(root.parent);
                    //since the parent has become root.. now rotate the right child which is the original node to left
                    root = rotateLeft(root.right);
                    // delete the last 2 characters from relationship
                    relationship = relationship.substring(0, relationship.length()-2);
                    break;
                case "01" :
                    //right child of left child .. apply zig zag
                    // left rotate the root
                    root = rotateLeft(root);
                    // right rotate the root
                    root  = rotateRight(root);

                    // delete the last 2 characters from relationship
                    relationship = relationship.substring(0, relationship.length()-2);
                    break;
                case "10" :
                    // left child of right child  .. apply zag zig
                    //right rotate the root
                    root  = rotateRight(root);
                    // now left rotate
                    root = rotateLeft(root);

                    // delete the last 2 characters from relationship
                    relationship = relationship.substring(0, relationship.length()-2);
                    break;

            }

        }

        //recursively call the splay method till the root supplied becomes the root of the actual tree
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
        if(root.right!=null){
            root.right.parent = root.parent;
        }


        // make the parent right child of root
        root.right = root.parent;
        root.parent.parent = root;

        // update root's parent as itd grandfather
        root.parent = tempParent;

        return root;
    }

    private Node<T> rotateLeft(Node<T> root) {

        //save root's grandparent
        Node<T> tempParent = root.parent.parent;

        // pluck roots right child and make it parents left child

        root.parent.right = root.left;
        if(root.left!=null){
            root.left.parent = root.parent;
        }


        // make the parent right child of root
        root.left = root.parent;
        root.parent.parent = root;

        // update root's parent as itd grandfather
        root.parent = tempParent;

        return root;
    }



    public static void main(String[] args) {
        SplayTree<Integer> sp = new SplayTree<>();
        sp.insert(10);
        sp.insert(5);
        sp.insert(15);
        sp.insert(7);


        System.out.println(sp.find(16));
    }



    private class Node<T extends  Comparable<T>>{
        public T data = null;
        public Node<T> left = null;
        public Node<T> right = null;
        public Node<T> parent = null;
    }
}
