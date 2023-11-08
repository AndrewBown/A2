package mru.assignment2.model;

public class Node<T extends Comparable<T>> {

    private Token data;
    private Node<Token> next = null;

    public Node() {
        data = null;
    }
    
    public Node(Token data) {
        this.data = data;
    }
        
    public Token getData() {
        return data;
    }
    public Node<Token> getNext() {
        return next;
    }
    public void setData(Token data) {
        this.data = data;
    }
    public void setNext(Node<Token> next) {
        this.next = next;
    }
}
