package com.rambogames;

public class Node {
    private Cowboy data;
    private Node left;
    private Node right;

    public Node(){
        data = null;
        this.left = null;
        this.right = null;
    }
    public Node(Cowboy data){
        this.data = data;
    }
    public Node(Cowboy data, Node left, Node right){
        this.data = data;
        this.right = right; //Next Node
        this.left = left; //Previous Node
    }

    public void setRight(Node right){
        this.right = right;
    }

    public Node getRight(){
        return right;
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public Node getLeft(){
        return left;
    }

    public void setData(Cowboy data){
        this.data = data;
    }

    public Cowboy getData(){
        return data;
    }
}
