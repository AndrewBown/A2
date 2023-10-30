package mru.assignment2.control;

import mru.assignment2.model.Node;
import mru.assignment2.model.Token;

public class SLL<T extends Comparable<Token>> {
    private Node<Token> head;
    private int size;

    public SLL() {
        head = null;
        size = 0;
    }

    public void add(Token wordToken) {
        Node<Token> newNode = new Node<Token>(wordToken);
        if (head == null) {
            head = newNode;
        } else {
            Node<Token> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    public void add(int index, Token wordToken) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<Token> newNode = new Node<Token>(wordToken);
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node<Token> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++;
    }

    public Token get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<Token> current = head;
        while(index > 0) {
            current = current.getNext();
            index--;
        }
        return current.getData();
    }

    public void set(int index, Token wordToken) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<Token> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setData(wordToken);
    }

    public Token remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Token removedData;
        if (index == 0) {
            removedData = head.getData();
            head = head.getNext();
        } else {
            Node<Token> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            removedData = current.getNext().getData();
            current.setNext(current.getNext().getNext());
        }
        size--;
        return removedData;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public boolean contains(Token wordToken) {
        Node<Token> current = head;
        while (current != null) {
            if (current.getData().equals(wordToken)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void addInOrder(Node<Token> wordNode) {
        if (head == null) { //if the list is empty add the new node as the head
            head = wordNode;
            return;
        }
        //go thropugh the list to find the right spot for the new node
        Node<Token> currNode = head;
        Node<Token> prevNode = null;

        // used to determine the position of the new node, whether the new node should be the new head, new tail or in between
        while (currNode != null && currNode.getData().compareTo(wordNode.getData()) < 0) {
            prevNode = currNode;
            currNode = currNode.getNext();
        }

        if (prevNode == null) { //if the new node should be the new head update head            
            wordNode.setNext(head);
            head = wordNode;
            size++;
        } else if (currNode == null) {  //if the new node should be the new tail update tail
            prevNode.setNext(wordNode);
            size++;
        } else {    //puts the new node between prevNode and currNode
            prevNode.setNext(wordNode);
            wordNode.setNext(currNode);
            size++;
        }
    }


    public void sortMostToLeastUsedWords(Node<Token> wordNode) {
        if (head == null) { //if the list is empty add the new node as the head
            head = wordNode;
            size++;
            return;
        }
        //go thropugh the list to find the right spot for the new node
        Node<Token> currNode = head;
        Node<Token> prevNode = null;

        // used to determine the position of the new node, whether the new node should be the new head, new tail or in between
        while (currNode != null && (currNode.getData().getIndividualWordCount() >= (wordNode.getData().getIndividualWordCount()))) {
            if(currNode.getData().getIndividualWordCount() == wordNode.getData().getIndividualWordCount()) {
                if(currNode.getData().compareTo(wordNode.getData()) > 0) {
                    break;
                }
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }

        if (prevNode == null) { //if the new node should be the new head update head            
            wordNode.setNext(head);
            head = wordNode;
            size++;
        } else if (currNode == null) {  //if the new node should be the new tail update tail
            prevNode.setNext(wordNode);
            size++;
        } else {    //puts the new node between prevNode and currNode
            prevNode.setNext(wordNode);
            wordNode.setNext(currNode);
            size++;
        }
    }


    public void sortLeastToMostUsedWords(Node<Token> wordNode) {
        if (head == null) { //if the list is empty add the new node as the head
            head = wordNode;
            size++;
            return;
        }
        //go thropugh the list to find the right spot for the new node
        Node<Token> currNode = head;
        Node<Token> prevNode = null;

        // used to determine the position of the new node, whether the new node should be the new head, new tail or in between
        while (currNode != null && (currNode.getData().getIndividualWordCount() <= (wordNode.getData().getIndividualWordCount()))) {
            if(currNode.getData().getIndividualWordCount() == wordNode.getData().getIndividualWordCount()) {
                if(currNode.getData().compareTo(wordNode.getData()) > 0) {
                    break;
                }
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }

        if (prevNode == null) { //if the new node should be the new head update head            
            wordNode.setNext(head);
            head = wordNode;
            size++;
        } else if (currNode == null) {  //if the new node should be the new tail update tail
            prevNode.setNext(wordNode);
            size++;
        } else {    //puts the new node between prevNode and currNode
            prevNode.setNext(wordNode);
            wordNode.setNext(currNode);
            size++;
        }
    }
}