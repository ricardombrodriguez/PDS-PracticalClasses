import java.util.Date;

abstract class EmployeeDecorator implements EmployeeInterface {

    protected EmployeeInterface e;
    
    EmployeeDecorator(EmployeeInterface e) {
        this.e = e;
    }
    
    public void start(Date date) { e.start(date);}
    public void terminate(Date date) { e.terminate(date);}
    public void work() { e.work();}
    public String getName() { return e.getName();}

}