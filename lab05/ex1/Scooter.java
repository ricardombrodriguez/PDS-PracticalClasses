package ex1;

public class Scooter implements Vehicle {
    
    static int maxVolume = 0;
    static int maxPassengers = 1;

    public int getMaxVolume(){
        return maxVolume;
    }

    public int getMaxPassengers(){
        return maxPassengers;
    }

    public String toString(){
        return "Use a Scooter";
    }
}
