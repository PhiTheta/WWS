package com.rambogames;

public class CDLL {
    private Node head;
    @SuppressWarnings("unused")
	private int data;

    //get a New Node
    public Node getNewNode(){
        Node newNode = new Node();
        newNode.setLeft(newNode);
        newNode.setRight(newNode);
        return newNode;
    }

    public Node getHeadNode(){
        return head;
    }

    public void setHeadNode(Node head){
        this.head = head;
    }

    public Node getNodeLeft(Node currentNode){
        return currentNode.getLeft();
    }

    public Node getNodeRight(Node currentNode){
        return currentNode.getRight();
    }

    public Node getNodeAt(int position){
        Node currentNode = head;
        for (int i=1; i < position; i++){
            currentNode = currentNode.getRight();
        }
        return currentNode.getRight();
    }

    public void addNodeAtBeginning(Cowboy data){
        Node newNode = new Node(data);
        if(head == null){
            newNode.setRight(newNode);
            newNode.setLeft(newNode);
            head = newNode;
        }
        else {
            Node temp = head.getLeft();
            temp.setRight(newNode);
            newNode.setLeft(temp);
            newNode.setRight(head);
            head.setLeft(newNode);
            head = newNode;
        }
    }

    public void addNodeAtEnd(Cowboy data){
        Node newNode = new Node(data);

        if (head == null){
            newNode.setRight(newNode);
            newNode.setLeft(newNode);
            head = newNode;
        }
        else{
            Node temp = head.getLeft();
            temp.setRight(newNode);
            newNode.setRight(head);
            head.setLeft(newNode);
            newNode.setLeft(temp);
        }
    }

    //Delete Current node
    public void deleteCurrentNode(Node head){
        if(head == null)
            System.out.println("List is already empty!");
        else{
            Node temp=head.getRight();
            head.getLeft().setRight(temp);
            temp.setLeft(head.getLeft());
            head=temp;
        }
    }


    //Get Size of the CDLL if head is null size is zero if not loop through and get the count
    public int getSizeOfCDLL(){
        //set the counter to zero
        int count = 0;

        if (head == null)
            return count;
        else{
            Node temp = head;
            do{
                temp = temp.getRight();
                count++;
            } while (temp != head);
        }

        return count;
    }

    public void displayCDLL(int cdllSize){

        System.out.print("\nCircular Doubly Linked List = ");
        //create a pointer to point at head
        Node pointR = head;

        if (cdllSize == 0){
            System.out.print("List is empty\n");
            return;
        }

        //If Single element in the List then show the link to itself.
        if (head.getRight() == head) {
            System.out.print(head.getData()+ " <--> " + pointR.getData()+ "\n");
            return;
        }

        System.out.print(head.getData() + " <--> ");
        // Move the cursor to the right and then loop through and list all the other elements
        pointR = head.getRight();

        while (pointR.getRight() != head){
            System.out.print(pointR.getData()+ " <--> ");
            pointR = pointR.getRight();
        }

        System.out.print(pointR.getData()+ " <--> ");
        pointR = pointR.getRight();
        System.out.print(pointR.getData()+ "\n");
    }



}
