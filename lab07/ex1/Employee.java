import java.util.Date;

public class Employee implements EmployeeInterface {
    
    private String name;

    public Employee(String name) {
        this.name = name;
    }
    
    @Override public void start(Date date){
        System.out.println(this.name + " started working on " + date.toString());
    }

    @Override public void terminate(Date date){
        System.out.println(this.name + " stopped working on " + date.toString());
    }

    @Override public void work() {
        System.out.println(this.name + " is working...");
    }

    @Override public String getName(){
        return this.name;
    }

}