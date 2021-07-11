package ex2;

import java.util.*;

public class Plane {
    private boolean hasExecutive;                  // Marks if this plane has executive class seating
    private int[] seatsT;             // Number of economy class seats
    private int[] seatsE;             // Number of executive class seats

    public Plane(int[] lugE, int[] lugT){
        this.seatsE = Arrays.copyOf(lugE,lugE.length);
        this.seatsT = Arrays.copyOf(lugT,lugT.length);
        this.hasExecutive = true;
    }

    public Plane(int[] lugT){
        this.seatsE = new int[]{0, 0};
        this.seatsT = Arrays.copyOf(lugT,lugT.length);
        this.hasExecutive = false;
    }

    public int getNumSeatsE(){
        if(hasExecutive) {
            return (seatsE[0] * seatsE[1]);
        }
        else
            return 0;
    }

    public int getNumSeatsT(){
        return (seatsT[0] * seatsT[1]);
    }

    public int[] getSeatDimensionsE(){
        return seatsE;
    }

    public int[] getSeatDimensionsT(){
        return seatsT;
    }

    public boolean hasExecutive(){
        return this.hasExecutive;
    }
}
