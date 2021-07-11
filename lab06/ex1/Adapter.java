package ex1;

public class Adapter {

    private Database db;
    private Registos rg;
    static boolean printIsEmployee = true;

    //Construtor que guarda tanto um objeto Database dos "Sweets" como o Registos dos "Petiscos"
    public Adapter(Database db, Registos rg) {
        this.db = db;
        this.rg = rg;
    }

    //Transforma o objeto do tipo Employee para Empregado para depois chamar a função seguinte.
    public void addEmployee(Employee emp) {

        //Separa o nome em primeiro e segundo nome para poder criar um objeto Empregado
        String[] fullname = emp.getName().split(" ");
        String fname = "";
        String lname = "";
        if (fullname.length == 2) {
            fname = fullname[0];
            lname = fullname[1];
        } else {
            System.out.println("Error: Employee has to have a first name and last name separated with SPACE.");
            return;
        }
        Empregado e = new Empregado(fname, lname, Math.toIntExact(emp.getEmp_num()), emp.getSalary());  //Transforma também o long do código para inteiro
        addEmployee(e);                                                                                 //Invoca a função seguinte
    }

    //Adiciona um Empregado à base de dados do tipo Registos (rg).
    //O printIsEmployee é um booleano que controla a função isEmployee() para não imprimir a verificação da existência de um empregado quando só pretendemos adicioná-lo
    public void addEmployee(Empregado emp) {

        printIsEmployee = false;

        if (this.isEmployee(emp.getCodigo())) {
            System.out.println("Error: There is already an employee with that code.");
            printIsEmployee = true;
            return;
        }

        printIsEmployee = true;

        this.rg.insere(emp);
        System.out.println(emp + " was added.");
    }

    //Transforma o código de um objeto Employee (em long) para o de um Empregado (inteiro) e invoca a próx. função
    public void removeEmployee(long num){
        removeEmployee(Math.toIntExact(num));
    }

    //Remove um Empregado caso este exista ou no Database (db) ou no Registos (rg)
    public void removeEmployee(int num){
        boolean b1 = this.db.deleteEmployee(Long.valueOf(num));
        boolean b2 = this.rg.remove(num);
        if (b1 || b2) {
            System.out.println("Employee nº " + num + " was removed."); 
            return;
        }
        System.out.println("Error: There is no employee with that number.");
    }

      //Transforma o código de um objeto Employee (em long) para o de um Empregado (inteiro) e invoca a próx. função
    public boolean isEmployee(long num){
        
        boolean employee = isEmployee(Math.toIntExact(num));
        return employee;
    }

    //Verificação se existe algum empregado com o código passado como argumento. Agora já pretendemos dar print da verificação e por isso usamos o printIsEmployee
    public boolean isEmployee(int num){
        
        //Verificar se existe na Database db
        for (Empregado emp : this.rg.listaDeEmpregados()){
            if (emp.getCodigo() == num && printIsEmployee) {
                System.out.println(num + " is an employee.");
                return true;
            } else if (emp.getCodigo() == num) {
                return true;
            }
        }

        //Verificar se existe nos Registos rg
        for (Employee emp : this.db.getAllEmployees()){
            if (emp.getEmp_num() == Long.valueOf(num) && printIsEmployee){
                System.out.println(num + " is an employee.");
                return true;
            } else if (emp.getEmp_num() == Long.valueOf(num)) {
                return true;
            }
        }

        //Não existe tal empregado com esse número
        if (printIsEmployee) System.out.println(num + " isn't an employee.");
        return false;
    }

    //Print em lista de todos os empregados. Tanto os que estão na Database db como no Registos rg), uma vez que interessa apresentar, também, empregados que estão na base de dados "antiga"
    public void printEmployees() {

        System.out.format("\n\n-------------- EMPLOYEE'S LIST -------------\n");

        for (Empregado emp : this.rg.listaDeEmpregados()){
            System.out.println(emp);
        }

        for (Employee emp : this.db.getAllEmployees()){
            System.out.println(emp);
        }

        System.out.format("--------------------------------------------\n\n");

    }


}