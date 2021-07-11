package ex1;

import java.util.Vector;

// Sweets
public class Database {
    
    private Vector<Employee> employees;

    //Inicializa um Vector com objetos do tipo Employee
    public Database(){
        employees = new Vector<>();
    }

    //Adiciona Employee ao employees (vector)
    public boolean addEmployee(Employee employee){
        return employees.add(employee);
    }

    //Remove o Employee do employees (vector) com aquele código/número
    public boolean deleteEmployee(long emp_num){
        for (Employee emp : employees) {
            if (emp.getEmp_num() == emp_num) {
                employees.remove(emp);
                return true;
            }
        }
        return false;
    }

    //Retorna um array de Employee a partir do vector employees
    public Employee[] getAllEmployees(){
        Employee[] employeesList = new Employee[employees.size()];  //cria um array com o mesmo tamanho que o vector
        for (int e = 0; e < employees.size(); e++) {
            employeesList[e] = employees.get(e);
        }
        return employeesList;
    }

}
