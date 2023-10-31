package mru.assignment2.control;

import mru.assignment2.model.Node;
import mru.assignment2.model.Token;

public class SLL<T extends Comparable<Token>> {
    private Node<Token> head;
    private int size;


    /**
     * Constructor for the SLL class. It initializes the head and size.
     */
    public SLL() {
        head = null;
        size = 0;
    }


    /**
     * Adds a new node to the list at the specified index
     * @param index int representing the index to add the new node
     * @param wordToken Token object to be added to the list
     */
    public void add(int index, Token wordToken) {
        Node<Token> newNode = new Node<Token>(wordToken);
        if (index == 0) {           //if the new node should be the new head update head
            newNode.setNext(head);
            head = newNode;
        } else {                //puts the new node between the node at index - 1 and index
            Node<Token> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++;
    }


    /**
     * Returns the Token object at the specified index
     * @param index int representing the index of the Token object to be returned
     * @return Token object at the specified index
     */
    public Token get(int index) {
        Node<Token> current = head;
        while(index > 0) {              //moves to the node at the specified index
            current = current.getNext();
            index--;
        }
        return current.getData();
    }


    /**
     * Sets the Token object at the specified index to the Token object passed
     * @param index int representing the index of the Token object to be set
     * @param wordToken Token object to be set at the specified index
     */
    public void set(int index, Token wordToken) {
        Node<Token> current = head;
        for (int i = 0; i < index; i++) {           //moves to the node at the specified index
            current = current.getNext();
        }
        current.setData(wordToken);
    }


    /**
     * Sorts the list from least used to most used
     * @param wordNode Node object to be added to the list
     */
    public void addInOrder(Node<Token> wordNode, String sortType) {
        if (head == null) { //if the list is empty add the new node as the head
            head = wordNode;
            size++;
            return;
        }
        Node<Token> currNode = head;
        Node<Token> prevNode = null;

        if(sortType == "mostUsed") {           //if the sort type is most used move to the next node if the current node is greater than the new node (orders from most used to least used)
            while (currNode != null && (currNode.getData().getIndividualWordCount() >= (wordNode.getData().getIndividualWordCount()))) {
                prevNode = currNode;
                currNode = currNode.getNext();
            }
        } else if(sortType == "leastUsed") {            //if the sort type is least used move to the next node if the current node is less than the new node (orders from least used to most used)
            while (currNode != null && (currNode.getData().getIndividualWordCount() <= (wordNode.getData().getIndividualWordCount()))) {
                prevNode = currNode;
                currNode = currNode.getNext();
            }
        } else if(sortType == "alphabetical") {             //if the sort type is alphabetical move to the next node if the current node is less than the new node (orders alphabetically)
            while (currNode != null && currNode.getData().compareTo(wordNode.getData()) < 0) {
                prevNode = currNode;
                currNode = currNode.getNext();
            }
        }
        if (prevNode == null) {     //if the new node should be the new head update head            
            wordNode.setNext(head);
            head = wordNode;
            size++;
        } else if (currNode == null) {      //if the new node should be the new tail update tail
            prevNode.setNext(wordNode);
            size++;
        } else {        //puts the new node between prevNode and currNode
            prevNode.setNext(wordNode);
            wordNode.setNext(currNode);
            size++;
        }
    }


    /**
     * Clears the list
     */
    public void clear() {
        head = null;
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
}