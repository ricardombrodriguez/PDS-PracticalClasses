package ex1;

public class VehicleFactory {
    
    static int passageiros;
    static int[] peso = null;
    static boolean wheelchair = false;

    public static Vehicle getVehicle(int passageiros, int[] peso, boolean wheelchair) {
        return verify(passageiros, peso, wheelchair);
    }

    public static Vehicle getVehicle(int passageiros, int[] peso) {
        return verify(passageiros, peso, wheelchair);
    }

    public static Vehicle getVehicle(int passageiros, boolean wheelchair) {
        return verify(passageiros, peso, wheelchair);
    }

    public static Vehicle getVehicle(int passageiros) {
        return verify(passageiros, peso, wheelchair);
    }

    public static Vehicle verify(int passageiros, int peso[], boolean wheelchair) {
        
        int peso_total = 0;
        if (peso != null)
            for (int i = 0; i < peso.length; i++) {
                if (peso[i] > 0) {
                    peso_total += peso[i];
                }
                else {
                    System.out.println("\u001B[31m" + "Error: Luggage element can't be less than one." + "\u001B[0m");
                    break;
                    
                }
            }
        
        Scooter scooter = new Scooter(); 
        Micro micro = new Micro();
        City city = new City();
        Family fam = new Family();
        Van van = new Van();
        
        Vehicle vec[] = {scooter, micro, city, fam, van};

        for (Vehicle v : vec) {

            if (passageiros <= v.getMaxPassengers() && passageiros > 0 && peso_total <= v.getMaxVolume() && wheelchair) {
                //van
                System.out.println("Vehicle for " + passageiros + " passengers" + ((peso != null) ? (" with " + peso.length + " items of luggage") : "") + " and wheelchair: "+ van.toString());
                return van;
            }
            else if (passageiros <= v.getMaxPassengers() && passageiros > 0 && peso_total <= v.getMaxVolume() && !wheelchair) {
                //not van
                System.out.println("Vehicle for " + passageiros + " passengers" + ((peso != null) ? (" with " + peso.length + " items of luggage") : "") + ((wheelchair) ? " and wheelchair" : "") + ": " + v.toString());
                return v;
            } 
        }

        System.out.println("\u001B[31m" + "There is no vehicle that can fulfill your requirements." + "\u001B[0m");
        return null;
    }

}
