package com.rambogames;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Scanner;
import com.google.gson.*;

public class Main {

    static String protocolFile = "protocol.json";

    static String exceptionDetailsStartStr = "************Start of Exception Details*********************** \n";
    static String exceptionDetailsEndStr = "************End of Exception Details*********************** \n";

    static int numberOfCowboys =0;
    static int healthPoints = 10;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        System.out.println("*****************Shootout at Wild West******************* \n");

        System.out.println("In the beginning in far away land called Wild West a shoot out happened. There was only one survivor in the shootout. It is the account of the shootout and the story of the lone survivor. \n");

        //Step 1: Get Number of Cowboys
        System.out.println("Please Enter the number of Cowboys you want to participate in the Shootout: \n");

        try(Scanner inp = new Scanner(System.in)){
            numberOfCowboys = inp.nextInt();
        }

        CDLL cowboys =  getCowboys(numberOfCowboys);

        //Step 2: Display Cowboys Circular Linked List/ Shootout Formation
        displayShootoutFormation(numberOfCowboys, cowboys);

        System.out.println("Selecting a Random Cowboy as a first shooter. \n");

        //Step 3: Pick a random node
        Node startingCowboy =  cowboys.getNodeAt(getRandomNumber(numberOfCowboys));

        //Step 4: Set it as head node so this Cowboy is the first shooter.
        cowboys.setHeadNode(startingCowboy);

        displayShootoutFormation(numberOfCowboys, cowboys);
        LinkedList<ShootoutHistory> shootoutHistories = new LinkedList<ShootoutHistory>();



        int counter = 1;

        do{
            System.out.printf("************************** Round = %d ************************ %n", counter);
            ShootoutHistory shootoutHistory = new ShootoutHistory();

            shootoutHistory.round = counter;
            shootoutHistory.shootout = new Shootout();

            //Step 5: Check the current health points of the Active shooter if it is even shoot right else shoot left
            if(cowboys.getHeadNode().getData().getCowboyCurrentHealth() % 2 == 0){
                int activeCowboyNumber = cowboys.getHeadNode().getData().getCowboyNumber();
                int targetCowboyNumber = cowboys.getHeadNode().getRight().getData().getCowboyNumber();
                int targetCowboyHealthPointsBeforeGettingShot = cowboys.getHeadNode().getRight().getData().getCowboyCurrentHealth();
                boolean isDead = false;
                int healthPointsLost = shoot();
                int healthPointsAfterGettingShot = targetCowboyHealthPointsBeforeGettingShot - healthPointsLost;

                if( healthPointsAfterGettingShot <=0) {
                    healthPointsAfterGettingShot = 0;
                    isDead = true;
                }
                cowboys.getHeadNode().getRight().getData().setCowboyCurrentHealth(healthPointsAfterGettingShot);
                System.out.println("Cowboy "+ activeCowboyNumber + " shot Cowboy "+ targetCowboyNumber +". Cowboy "+targetCowboyNumber + " had "+ targetCowboyHealthPointsBeforeGettingShot + " health points and after getting shot, his remaining health points are "+healthPointsAfterGettingShot );

                shootoutHistory.setActiveCowboy(activeCowboyNumber);
                shootoutHistory.setTargetCowboy(targetCowboyNumber);
                shootoutHistory.setHealthPointsLost(healthPointsLost);
                shootoutHistory.setHealthPointsLeft(healthPointsAfterGettingShot);

                if(!isDead)
                    cowboys.setHeadNode(cowboys.getHeadNode().getRight());
                else {
                    System.out.println("Cowboy"+ targetCowboyNumber + "died.");
                    cowboys.deleteCurrentNode(cowboys.getHeadNode().getRight());
                }
            }
            else{
                int activeCowboyNumber = cowboys.getHeadNode().getData().getCowboyNumber();
                int targetCowboyNumber = cowboys.getHeadNode().getLeft().getData().getCowboyNumber();
                int targetCowboyHealthPointsBeforeGettingShot = cowboys.getHeadNode().getRight().getData().getCowboyCurrentHealth();
                boolean isDead = false;

                int healthPointsLost = shoot();
                int healthPointsAfterGettingShot = targetCowboyHealthPointsBeforeGettingShot - healthPointsLost;

                if( healthPointsAfterGettingShot <=0) {
                    healthPointsAfterGettingShot = 0;
                    isDead = true;
                }

                cowboys.getHeadNode().getLeft().getData().setCowboyCurrentHealth(healthPointsAfterGettingShot);
                System.out.println("Cowboy "+ activeCowboyNumber + " shot Cowboy "+ targetCowboyNumber +". Cowboy "+targetCowboyNumber + " had "+ targetCowboyHealthPointsBeforeGettingShot + " health points and after getting shot, his remaining health points are "+healthPointsAfterGettingShot );

                shootoutHistory.setActiveCowboy(activeCowboyNumber);
                shootoutHistory.setTargetCowboy(targetCowboyNumber);
                shootoutHistory.setHealthPointsLost(healthPointsLost);
                shootoutHistory.setHealthPointsLeft(healthPointsAfterGettingShot);

                if(!isDead)
                    cowboys.setHeadNode(cowboys.getHeadNode().getLeft());
                else{
                    System.out.println("Cowboy "+ targetCowboyNumber + " died.");
                    cowboys.deleteCurrentNode(cowboys.getHeadNode().getLeft());
                }
            }
            
            
            shootoutHistories.add(shootoutHistory);
            counter++;
        }while(cowboys.getSizeOfCDLL() > 1);
        
        System.out.println("\n Cowboy "+ cowboys.getHeadNode().getData().getCowboyNumber() + " survived and emerged victorious in the shootout. \n");
        saveProtocolFile(protocolFile,shootoutHistories);
        calculateMd5Checksum(protocolFile);

    }

    //Creates and Returns cowboys
    public static CDLL getCowboys(int numberOfCowboys) {
        CDLL cowboys = new CDLL();

        switch (numberOfCowboys) {
            case 0:
            case 1:
                System.out.println("You need to enter more than 1 Cowboy for shootout.");
                break;
            default:
                for (int i = 1; i <= numberOfCowboys; i++) {
                    cowboys.addNodeAtEnd(new Cowboy(i,healthPoints));
                }
        }

        return cowboys;
    }

    //Displays the shootout formation
    public static void displayShootoutFormation(int cdllSize, CDLL cdll) {

        System.out.print("\n Shootout formation = ");
        //create a pointer to point at head
        Node head = cdll.getHeadNode();

        Node pointR = head;

        if (cdllSize == 0){
            System.out.print("List is empty\n");
            return;
        }

        //If Single element in the List then show the link to itself.
        if (head.getRight() == head) {
            System.out.print("Cowboy Number: " + head.getData().getCowboyNumber() + " <--> " + pointR.getData().getCowboyNumber()+ "\n");
            return;
        }

        System.out.print("Cowboy Number: " + head.getData().getCowboyNumber() + " <--> ");
        // Move the cursor to the right and then loop through and list all the other elements
        pointR = head.getRight();

        while (pointR.getRight() != head){
            System.out.print("Cowboy Number: " + pointR.getData().getCowboyNumber()+ " <--> ");
            pointR = pointR.getRight();
        }

        System.out.print("Cowboy Number: " + pointR.getData().getCowboyNumber()+ " <--> ");
        pointR = pointR.getRight();
        System.out.print("Cowboy Number: " + pointR.getData().getCowboyNumber()+ "\n \n");
    }

    public static int shoot(){
        return getRandomDamagePoints(5);
    }

    //In our case number of bullets are 5
    public static int getRandomDamagePoints(int totalBullets){
        return (int) (Math.random() * totalBullets) + 1;
    }

    public static int getRandomNumber(int numberOfCowboys){
        return (int) (Math.random() * numberOfCowboys) + 1;
    }

    private static void saveProtocolFile(String filePath, LinkedList<ShootoutHistory> jsonObj) throws IOException
    {
        try{

            Gson wildWestShootout = new GsonBuilder().setPrettyPrinting().create(); //.disableInnerClassSerialization()
            //If you do not use try block here then you have to explicitly filewriter.flush() and filewriter.close().
            try(FileWriter jsonFileWriter = new FileWriter(filePath, StandardCharsets.UTF_8)){
                wildWestShootout.toJson(jsonObj, jsonFileWriter);
            }
            System.out.printf("The Protocol file is stored in %s file and its MD5 Cheksum is: %s" ,filePath, calculateMd5Checksum(filePath));
        }
        catch(Exception ex) {
            System.out.println(exceptionDetailsStartStr);
            ex.printStackTrace();
            System.out.println(exceptionDetailsEndStr);
        }

    }

    private static String calculateMd5Checksum(String filePath) throws NoSuchAlgorithmException, IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5"); //It can be SHA, MD2, MD5, SHA-256, SHA-384...
        // file hashing with DigestInputStream
        try (DigestInputStream digstrm = new DigestInputStream(new FileInputStream(filePath), md))
        {
            while(digstrm.read() != -1) ; //empty loop to clear the data
            md = digstrm.getMessageDigest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // bytes to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest())
        {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

}
