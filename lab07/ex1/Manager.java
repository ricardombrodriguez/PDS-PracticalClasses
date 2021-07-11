
public class Manager extends EmployeeDecorator {

    public Manager(EmployeeInterface e) {
        super(e);
    }

    @Override public void work() {
        this.manage();
    }

    public void manage(){
        System.out.print(e.getName() + "is managing...");
    }
}
