package ex1;

public class main {
    public static void main(String[] args) {
        int[] luggage;
        Vehicle v;

        // Get vehicle for 1 passenger without luggage
        v = VehicleFactory.getVehicle(1);

        // Get vehicle for 1 passenger with two items of luggage
        luggage = new int[]{100, 140};
        v = VehicleFactory.getVehicle(1, luggage);

        // Get vehicle for 3 passengers with two items of luggage
        luggage = new int[]{50, 200, 240};
        v = VehicleFactory.getVehicle(3, luggage);

        // Get vehicle for 2 passengers and wheelchair
        v = VehicleFactory.getVehicle(2, true);

        /////////////////// adicionar mais exemplos ///////////////////

        // com todos os argumentos possíveis
        luggage = new int[]{50, 200, 500, 10, 100};
        v = VehicleFactory.getVehicle(4, luggage, true);

        // um passageiro com uma elevada carga
        luggage = new int[]{50, 200, 30};
        v = VehicleFactory.getVehicle(1, luggage);

        // número de passageiros superior ao máximo
        luggage = new int[]{50, 100};
        v = VehicleFactory.getVehicle(5, luggage);

        //bagagem superior a todos os veículos:
        luggage = new int[]{300, 200, 500, 10, 100};
        v = VehicleFactory.getVehicle(3, luggage, false);

        //numero inválido de passageiros
        v = VehicleFactory.getVehicle(0);
    }
}