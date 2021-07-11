package ex1;

public class Micro implements Vehicle {
    
    static int maxVolume = 250;
    static int maxPassengers = 1;

    public int getMaxVolume(){
        return maxVolume;
    }

    public int getMaxPassengers(){
        return maxPassengers;
    }

    public String toString(){
        return "Use a Micro car";
    }
}
