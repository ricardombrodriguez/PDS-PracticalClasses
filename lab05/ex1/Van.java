package ex1;

public class Van implements Vehicle {
    
    static int maxVolume = 1000;
    static int maxPassengers = 4;

    public int getMaxVolume(){
        return maxVolume;
    }

    public int getMaxPassengers(){
        return maxPassengers;
    }

    public String toString(){
        return "Use a Van";
    }
}
