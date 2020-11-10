package com.rambogames;

import java.io.Serializable;

public class Node<E> implements Serializable{
    private E data;
    private Node left;
    private Node right;

    public Node(){
        data = null;
        this.left = null;
        this.right = null;
    }
    public Node(E data){
        this.data = data;
    }
    public Node(E data, Node left, Node right){
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

    public void setData(E data){
        this.data = data;
    }

    public E getData(){
        return data;
    }
}
