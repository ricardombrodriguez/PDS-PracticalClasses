import java.util.Date;

public class TeamLeader extends EmployeeDecorator {

    public TeamLeader(EmployeeInterface e) {
        super(e);
    }    

    @Override public void work() {
        this.plan();
    }

    public void plan() {
        System.out.println(e.getName() + " is planning...");
    }

}
