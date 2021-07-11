import java.util.Random;
import java.util.ArrayList;

public class EmployeeCard {

    private static ArrayList<Employee> allowedEmployees = new ArrayList<Employee>();
    
    public static void regist(Employee emp) {
        if ( !allowedEmployees.contains(emp) ) {
            Random random = new Random();
            int cardNumber = random.nextInt(99999999 - 10000000 + 1) + 10000000;  //numero random de 8 digitos
            System.out.println("[EMPLOYEE CARD]: "+ emp.getName() +" allowed | [NUMBER]: " + cardNumber);

        } else {
            System.out.println("[EMPLOYEE CARD]: "+ emp.getName() +" already have a card");
        }
    }

}