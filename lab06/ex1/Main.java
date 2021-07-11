package ex1;

import java.util.List;

public class Main {
    
    public static void main(String[] args){

        //Métodos do "Sweets":

        System.out.format("'Sweets' methods: \n\n");

        Database dbEmployee = new Database();

        Employee e1 = new Employee("Nuno Almeida",7294,2000);
        Employee e2 = new Employee("Diogo Ezequiel", 8320, 2000);
        Employee e3 = new Employee("António Beterraba", 3382, 1900);
        Employee e4 = new Employee("João Fonseca", 2919, 1800);

        System.out.println("Name: " + e1.getName());

        System.out.println("Diogo Ezequiel employee number: " + e2.getEmp_num());

        System.out.println("João Fonseca's salary: " + e4.getSalary());

        dbEmployee.addEmployee(e1);
        dbEmployee.addEmployee(e2);
        dbEmployee.addEmployee(e3);
        dbEmployee.addEmployee(e4);

        dbEmployee.deleteEmployee(e3.getEmp_num());

        Employee[] employeeList = dbEmployee.getAllEmployees();
        System.out.println("List of employees:");
        for (Employee emp : employeeList) {
            if (emp != null) System.out.println(emp);
        }

        System.out.println();

        //Métodos dos "Petiscos":

        System.out.format("\n'Petiscos' methods: \n\n");

        Registos registos = new Registos();

        Empregado emp1 = new Empregado("Paulo", "Moreira", 1234,2100);
        Empregado emp2 = new Empregado("Vasco","Leal",3821,2300);
        Empregado emp3 = new Empregado("António", "Moreira", 9392,2000);
        Empregado emp4 = new Empregado("Artur","Santos",9234,2000);

        System.out.println("Primeiro nome: " + emp1.getNome());
        System.out.println("Último nome: " + emp1.getApelido());

        System.out.println("O código de empregado do Vasco Leal: " + emp2.getCodigo());

        System.out.println("O salário do António: " + emp3.getSalario());

        registos.insere(emp1);
        registos.insere(emp2);
        registos.insere(emp3);
        registos.insere(emp4);

        registos.remove(emp3.getCodigo());

        System.out.println("Existe algum empregado com o número 9392? " + registos.isEmpregado(9392));

        List<Empregado> empregados = registos.listaDeEmpregados();
        System.out.println("Lista de empregados:");
        for (Empregado emp : empregados) {
            if (emp != null) System.out.println(emp);
        }

        System.out.println();

        // Com o Adapter:

        System.out.format("\n'Adapter' methods: \n\n");

        Adapter adapter = new Adapter(dbEmployee, registos);

        Employee e5 = new Employee("Cristiano Ronaldo", 3326, 3000);
        Employee e6 = new Employee("João Moutinho", 1992, 2600);
        Employee e7 = new Employee("Rui", 1222, 2600);
        Empregado emp5 = new Empregado("Diogo", "Jota", 4321,2100);
        Empregado emp6 = new Empregado("Afonso","Albuquerque",8528,1880);

        adapter.addEmployee(e5);
        adapter.addEmployee(e6);
        adapter.addEmployee(e6);
        adapter.addEmployee(e7);
        adapter.addEmployee(emp5);
        adapter.addEmployee(emp6);
        adapter.addEmployee(emp6);

        adapter.removeEmployee(e6.getEmp_num());
        adapter.removeEmployee(emp1.getCodigo());
        adapter.removeEmployee(emp2.getCodigo());
        adapter.removeEmployee(938784);

        adapter.isEmployee(e6.getEmp_num());
        adapter.isEmployee(e1.getEmp_num());
        adapter.isEmployee(emp4.getCodigo());
        adapter.isEmployee(emp3.getCodigo());
        adapter.isEmployee(189374981);

        adapter.printEmployees();

    } 
}
