package com.rambogames;

public class Cowboy {
    private int cowboyNumber;
    private int cowboyCurrentHealth;

    public Cowboy(int cowboyNumber, int cowboyCurrentHealth){
        this.cowboyNumber = cowboyNumber;
        this.cowboyCurrentHealth = cowboyCurrentHealth;
    }

    public int getCowboyNumber(){
        return cowboyNumber;
    }
    public int getCowboyCurrentHealth(){
        return cowboyCurrentHealth;
    }

    public void setCowboyNumber(int cowboyNumber){
       this.cowboyNumber = cowboyNumber  ;
    }

    public void setCowboyCurrentHealth(int cowboyCurrentHealth){
        this.cowboyCurrentHealth = cowboyCurrentHealth;
    }


}
