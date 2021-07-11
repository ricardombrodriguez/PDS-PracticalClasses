import java.util.List;

public class SharkCompany {
    public static void main(String[] args) {
        Company shark = new Company();
        Company.user = User.OWNER;

        shark.admitPerson("Maria Silva", 1000);
        shark.admitPerson("Manuel Pereira", 900);
        shark.admitPerson("Aurora Machado", 1200);
        shark.admitPerson("Augusto Lima", 1100);

        List<Employee> sharkEmps = shark.employees();

        for (Employee e : sharkEmps)
            // "talking to strangers", but this is not a normal case
            if (!Double.isNaN(e.getBankAccount().balance()))
                System.out.println(e.getBankAccount().balance());
            else
                System.out.println("Balance Operation not allowed");

        shark.paySalaries(1);
        for (Employee e : sharkEmps) {
            e.getBankAccount().withdraw(500);
            if (!Double.isNaN(e.getBankAccount().balance()))
                System.out.println(e.getBankAccount().balance());
            else
                System.out.println("Balance Operation not allowed");
        }
    }
}