package mru.assignment3.control;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import mru.assignment3.model.*;

public class BST<T extends Comparable<Token>> {
    private TreeNode<Token> root;
    private int size;


    /**
     * Constructor for the SLL class. It initializes the head and size.
     */
    public BST() {
        root = null;
        size = 0;
    }


    /**
     * Adds a new node to the tree
     * @param wordToken Token object to be added to the list
     */
    public void add(Token wordToken, String addByType) {
        TreeNode<Token> newNode = new TreeNode<Token>(wordToken);

        if(root == null) {
            root = newNode;
            size++;
            return;
        }
        if(addByType == "alphabetical") {
            sortAlphabetical(root, newNode);
        } else if(addByType == "wordFreqMostToLeast") {
            sortWordCount(root, newNode);
        }
    }


    public TreeNode<Token> sortAlphabetical(TreeNode<Token> currNode, TreeNode<Token> newNode) {

        if(currNode == null) {
            currNode = newNode;
            size++;
            return currNode;
        }

        int compared = currNode.data.compareTo(newNode.data);
        if(compared > 0) {
            currNode.leftChild = sortAlphabetical(currNode.leftChild, newNode);
            currNode.leftChild.parent = currNode; // update parent of left child
            return currNode;
        } else if(compared < 0) {
            currNode.rightChild = sortAlphabetical(currNode.rightChild, newNode);
            currNode.rightChild.parent = currNode; // update parent of right child
            return currNode;
        } else {                    
            currNode.data.increaseIndividualWordCount();
            return currNode;
        }
    }


    private void sortWordCount(TreeNode<Token> currNode, TreeNode<Token> newNode) {
        if(currNode == null) {
            currNode = newNode;
            size++;
            return;
        }

        int compared = currNode.data.getIndividualWordCount() - newNode.data.getIndividualWordCount();
        if(compared > 0) {                                   // if newNode has a lower word count than currNode continue to sort word count
            sortWordCount(currNode.leftChild, newNode);
        } else if(compared < 0) {                           // if newNode has a higher word count than currNode continue to sort word count
            sortWordCount(currNode.rightChild, newNode);
        } else {                                            // if newNode has the same word count as currNode sort alphabetically
            sortAlphabetical(currNode, newNode);
            return;
        }
    }


    public void sortWordFreqMostToLeast(BST<T> tree) {
        Iterator<Token> treeIterator = tree.Iterator();
        while(treeIterator.hasNext()) {
            Token wordToken = treeIterator.next();
            add(wordToken, "wordFreqMostToLeast");
        }
    }


    /**
     * Clears the list 
     */
    public void clear() {
        root = null;
        size = 0;
    }


    /**
     * returns a boolean representing if the list is empty
     * @return boolean representing if the list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * returns the size of the list
     * @return
     */
    public int size() {
        return size;
    }


    public void delete(String wordOfNodeToDelete) {
        TreeNode<Token> nodeToDelete = search(wordOfNodeToDelete, root);
        delete(nodeToDelete);
    }


    public void delete(TreeNode<Token> nodeToDelete) {
        if(nodeToDelete == null) {
            return;
        }

        if(nodeToDelete.leftChild == null && nodeToDelete.rightChild == null) {            // big oof messy code lol...
            if(nodeToDelete.parent.leftChild == nodeToDelete) {
                nodeToDelete.parent.leftChild = null;
            } else {
                nodeToDelete.parent.rightChild = null;
            }
        } else if(nodeToDelete.leftChild == null) {
            if(nodeToDelete.parent.leftChild == nodeToDelete) {
                nodeToDelete.parent.leftChild = nodeToDelete.rightChild;
            } else {
                nodeToDelete.parent.rightChild = nodeToDelete.rightChild;
            }
        } else if(nodeToDelete.rightChild == null) {
            if(nodeToDelete.parent.leftChild == nodeToDelete) {
                nodeToDelete.parent.leftChild = nodeToDelete.leftChild;
            } else {
                nodeToDelete.parent.rightChild = nodeToDelete.leftChild;
            }                                                                           // ...till here, im sure some of those if statements could be combined or smth cuz the statements are the same for all of them
        } else {
            TreeNode<Token> currNode = nodeToDelete.rightChild;
            TreeNode<Token> prevNode = nodeToDelete;
            while(currNode.leftChild != null) {
                prevNode = currNode;
                currNode = currNode.leftChild;
            }
            if(prevNode != nodeToDelete) {
                prevNode.leftChild = currNode.rightChild;
            } else {
                prevNode.rightChild = currNode.rightChild;
            }
            nodeToDelete.data = currNode.data;
        }
        size--;
    }


        


    public TreeNode<Token> search(String word, TreeNode<Token> currNode) {
        if(currNode == null) {
            return null;
        }

        int compared = currNode.data.getWord().compareTo(word);
        
        if(compared > 0) {
            return search(word, currNode.leftChild);
        } else if(compared < 0) {
            return search(word, currNode.rightChild);
        } else {
            return currNode;
        }
    }


    public Iterator<Token> Iterator() {
        Iterator<Token> iterator = new BSTIterator();
        return iterator;
    }


    private void traverse(TreeNode<Token> currNode, int traverseType, Visit<T> visit) {
        if(currNode != null) {
            visit.visit(currNode.data);
            if(traverseType < 0) {
                traverse(currNode.leftChild, traverseType, visit);
                traverse(currNode.rightChild, traverseType, visit);
            }
        }
    }

    

    private Queue<Token> queue = new LinkedList<Token>();

    private class BSTIterator implements Iterator<Token> {
        private final int IN_ORDER = -1;
        public BSTIterator() {
            queue.clear();
            traverse(root, IN_ORDER, new IteratorVisit());
        }


        public boolean hasNext() {
            return !queue.isEmpty();
        }


        @Override
        public Token next() {
            return queue.remove();
        }
    }



    private class IteratorVisit implements Visit<T> {
        public void visit(Token token) {
            if(token != null) {
                queue.add(token);
            }
        }
    }



    private interface Visit<T> {
        public void visit(Token currNode);
    }



    private static class TreeNode<T extends Comparable<Token>> {
        private Token data;
        private TreeNode<Token> leftChild = null;
        private TreeNode<Token> rightChild = null;
        private TreeNode<Token> parent = null;
            
        public TreeNode(Token data) {
            this.data = data;
        }
    }
}
