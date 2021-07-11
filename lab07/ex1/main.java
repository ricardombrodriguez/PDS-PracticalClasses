

import java.util.Date;

class main {
    public static void main(String[] args) {
        
        EmployeeInterface e1 = new Employee("João Batista");

        TeamMember tm1 = new TeamMember( new Employee("Francisca Alegria"));
        TeamMember tm2 = new TeamMember( new Employee("Joaquim Silva"));
        TeamMember tm3 = new TeamMember( new Employee("Diana C. Batista"));
        TeamMember tm4 = new TeamMember( new Employee("Sara Matos"));

        TeamLeader tl1  = new TeamLeader( new Employee("Ricardo Gordo"));

        Manager m1 = new Manager( new Employee("Bernardo Rosa"));
        Manager m2 = new Manager( new TeamLeader( new Employee("Soraia Tavares")));

        TeamLeader tl2 = new TeamLeader( new TeamLeader( new Employee("Dulce Lasagna")));

        
        EmployeeInterface lista[] = { e1,tm1,tm2,tm3,tm4,tl1,m1,m2,tl2 };

        for (EmployeeInterface e : lista) {
            e.start(new Date());
            e.work();
            e.terminate(new Date());
        }

    }
}