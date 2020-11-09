package com.rambogames;

import java.io.Serializable;

public class ShootoutHistory implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8820538205702901604L;
	
	int round =0;
    Shootout shootout = new Shootout();

    public void setActiveCowboy(int activeCowboy){
        this.shootout.activeCowboy = activeCowboy;
    }

    public void setTargetCowboy(int targetCowboy){
        this.shootout.targetCowboy = targetCowboy;
    }

    public void setHealthPointsLost(int healthPointsLost){
        this.shootout.healthPointsLost = healthPointsLost;
    }

    public void setHealthPointsLeft(int healthPointsLeft){
        this.shootout.healthPointsLeft = healthPointsLeft;
    }
}
