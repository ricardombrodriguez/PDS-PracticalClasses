import java.util.Date;

public class TeamMember extends EmployeeDecorator {

    public TeamMember(EmployeeInterface e) {
        super(e);
    }

    @Override public void work() {
        this.colaborate();
    }

    public void colaborate(){
        System.out.println(e.getName() + " is colaborating...");
    }
}
