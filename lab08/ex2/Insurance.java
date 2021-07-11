import java.util.ArrayList;
import java.util.Random;

public class Insurance {

    private static ArrayList<Employee> allowedEmployees = new ArrayList<Employee>();
    
    public static void regist(Employee emp) {
        if ( !allowedEmployees.contains(emp) ) {
            Random random = new Random();
            int insuranceNum = random.nextInt(999999 - 100000 + 1) + 100000;  //numero random de 6 digitos
            System.out.println("[INSURANCE]: "+ emp.getName() +" allowed | [NUMBER]: " + insuranceNum);

        } else {
            System.out.println("[INSURANCE]: "+ emp.getName() +" already have permission");
        }
    }

}