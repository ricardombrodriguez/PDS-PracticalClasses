package ex1;

public class Family implements Vehicle {
    
    static int maxVolume = 600;
    static int maxPassengers = 4;

    public int getMaxVolume(){
        return maxVolume;
    }

    public int getMaxPassengers(){
        return maxPassengers;
    }

    public String toString(){
        return "Use a Family car";
    }
}
