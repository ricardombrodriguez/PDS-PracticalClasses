import java.util.ArrayList;
import java.util.Random;

public class SocialSecurity {

    private static ArrayList<Employee> allowedEmployees = new ArrayList<Employee>();
    
    public static void regist(Employee emp) {
        if ( !allowedEmployees.contains(emp) ) {
            Random random = new Random();
            int ssNumber = random.nextInt(999999999 - 100000000 + 1) + 100000000;  //numero random de 9 digitos
            System.out.println("[SOCIAL SECURITY]: "+ emp.getName() +" registered | [NUMBER]: " + ssNumber);
            allowedEmployees.add(emp);

        } else {
            System.out.println("[SOCIAL SECURITY]: "+ emp.getName() +" already have registered");
        }
    }

}