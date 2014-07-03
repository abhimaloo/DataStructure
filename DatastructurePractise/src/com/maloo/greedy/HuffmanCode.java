package com.maloo.greedy;




import java.util.*;
;

/**  This is one of the application of greedy algorithm
 * This is used to compress the messages
 * Created by abhishekm787 on 7/2/14.
 */
public class HuffmanCode {

    public static Map<String,String> dictionaryMessageToCode = new HashMap<>();
    public static Map<String,String> dictionaryCodeToMessage = new HashMap<>();

    /**
     * Encrypts a Plain text message to HuffmanCode
     * Uses Greedy algorithm
     * @param message
     * @return
     */
    public static String createHuffmanCode(String message) {
        //It keeps the frequency count
        Map<String,Integer> frequencyCount = new HashMap<>();


        //this loop builds the frequency map
        for(int i =0; i <message.length(); i++){
            if(frequencyCount.containsKey(String.valueOf(message.charAt(i)))){
                frequencyCount.put(String.valueOf(message.charAt(i)), frequencyCount.get(String.valueOf(message.charAt(i)))+1);
            } else {
                frequencyCount.put(String.valueOf(message.charAt(i)), 1);
            }
        }


        //This heap contains all the binary trees
        //this is where the greedy approach comes to picture
        PriorityQueue<BinaryTree<String>> heap  = new PriorityQueue<>();

        // populating the heap with all the single rooted trees
        for(Map.Entry<String,Integer> entry: frequencyCount.entrySet()){
            //Binary tree is a special binary tree which supports merge(), find()
            BinaryTree<String> tree = new BinaryTree<>();
            tree.createRoot(entry.getKey(),entry.getValue());
            heap.add(tree);
        }

        while(heap.size()!=1){
            BinaryTree<String> minTree1 = heap.remove();
            BinaryTree<String> minTree2 = heap.remove();

            BinaryTree<String> merged = minTree1.merge(minTree1,minTree2);
            heap.add(merged);

        }

        //this is the final expression tree which will be used for this message
        BinaryTree<String> expressionTree = heap.remove();

        String response = "";

        for(Map.Entry<String,Integer> entry: frequencyCount.entrySet()){
            //it finds the code for every character
            String expression =   BinaryTree.find(expressionTree.root,entry.getKey(),"");
            //populates the dictionaries
            //dictionary which converts messageToCode
            dictionaryMessageToCode.put(entry.getKey(),expression);
            //dictionary which contains code to message
            dictionaryCodeToMessage.put(expression,entry.getKey());
        }

        for(int i =0; i <message.length(); i++){
            //use the dictionary to encode the message
            response+=dictionaryMessageToCode.get(String.valueOf(message.charAt(i)));
        }


        return response;

    }

    public static String decode(String encryptedCode) {
        String s = "";
        String message = "";
        for(int i=0; i <encryptedCode.length(); i ++) {
           s+=encryptedCode.charAt(i);
           if(dictionaryCodeToMessage.containsKey(s)) {
               message+=dictionaryCodeToMessage.get(s);
               s ="";
           }

        }
        return message;
    }


    public static void main(String[] args) {
        System.out.println(decode(createHuffmanCode("MISSISSIPPI RIVER")));
    }

}

class BinaryTree<T extends Comparable<T>> implements Comparable<BinaryTree<T>>{

    public Node<T> root;

    /**
     * create single root
     * @param data   - its the character
     * @param size   - its the frequency/occurrence
     * @return
     */
    public Node<T> createRoot (T data, int size ) {
        Node<T> newNode = new Node<>();
        newNode.data = data;
        newNode.size =  size;

        root = newNode;
        return root;

    }

    /**
     * merge two trees into third one.. root contains the data which is concatenation of tre11.data + tree2.data
     * also adds up the frequency of the merging tree
     * @param tree1
     * @param tree2
     * @return
     */
    public static BinaryTree<String> merge(BinaryTree<String> tree1, BinaryTree<String> tree2){
        BinaryTree<String> response = new BinaryTree<>();
        response.createRoot(tree1.root.data + tree2.root.data, tree1.root.size + tree2.root.size);
        response.root.left = tree1.root;
        response.root.right = tree2.root;
        return response;
    }


    /**
     * This is a tricky subroutine
     * It recursively finds a character ..
     * Keeps on adding 0's if flow goes to left child and 1's when flow goes to right child
     * @param root
     * @param character
     * @param pattern
     * @return
     */
    public static String find(BinaryTree<String>.Node<String> root, String character, String pattern) {
        if(root.data.compareTo(character)==0) {
            return pattern;
        }

        if(root.data.contains(character)){
            if(root.left!=null && root.left.data.contains(character)){
                return find(root.left, character,pattern+"0");
            }

            if(root.right!=null && root.right.data.contains(character)){
                return find(root.right, character,pattern+"1");
            }
        }

        return null;
    }

    /**
     * It uses the root's size (frequency count) to compare with other trees
     * root contains the aggregated size of all its children
     * @param o
     * @return
     */
    @Override
    public int compareTo(BinaryTree<T> o) {
        return  Integer.compare(this.root.size, o.root.size);
    }


    class Node<T extends Comparable<T>> {
        public T data;
        public int size;
        public Node<T> left = null;
        public Node<T> right = null;
    }
}

