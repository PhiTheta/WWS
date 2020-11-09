package com.rambogames;

public class Shootout{
    int activeCowboy;
    int targetCowboy;
    int healthPointsLost;
    int healthPointsLeft;

    Shootout(){
        this.activeCowboy = 0;
        this.targetCowboy = 0;
        this.healthPointsLost = 0;
        this.healthPointsLeft = 0;
    }

    Shootout(int activeCowboy, int targetCowboy, int healthPointsLost, int healthPointsLeft){
        this.activeCowboy = activeCowboy;
        this.targetCowboy = targetCowboy;
        this.healthPointsLost = healthPointsLost;
        this.healthPointsLeft = healthPointsLeft;
    }
}