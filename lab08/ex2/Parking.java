import java.util.ArrayList;
import java.util.Random;

public class Parking {

    private static ArrayList<Employee> allowedEmployees = new ArrayList<Employee>();
    private static int counter = 0;

    public static void allow(Employee emp) {
        if ( !allowedEmployees.contains(emp) ) {
            System.out.println("[PARKING]: "+ emp.getName() +" allowed | [NUMBER]: " + (++counter));

        } else {
            System.out.println("[PARKING]: "+ emp.getName() +" already have permission");
        }
    }

}