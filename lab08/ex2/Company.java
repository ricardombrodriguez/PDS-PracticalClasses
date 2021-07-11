import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Company {
    public static User user;
    private List<Employee> emps = new ArrayList<>();
    
    public void admitEmployee(Person p, double salary) {
        System.out.println();
        System.out.println("======================== NEW EMPLOYEE ============================");
        Employee e = new Employee(p.getName(), salary);
        emps.add(e);

        //Registo do empregado nas várias classes
        SocialSecurity.regist(e);
        Insurance.regist(e);
        EmployeeCard.regist(e);
        Parking.allow(e);

        System.out.println("==================================================================");
    }

    public void paySalaries(int month) {
        for (Employee e : emps) {
            BankAccount ba = e.getBankAccount();
            ba.deposit(e.getSalary());
        }
    }

    public List<Employee> employees() {
        return Collections.unmodifiableList(emps);
    }
}