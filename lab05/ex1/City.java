package ex1;

public class City implements Vehicle {
    
    static int maxVolume = 250;
    static int maxPassengers = 3;

    public int getMaxVolume(){
        return maxVolume;
    }

    public int getMaxPassengers(){
        return maxPassengers;
    }

    public String toString(){
        return "Use a City car";
    }
}
