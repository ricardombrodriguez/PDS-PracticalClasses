class Employee {
    private String name;
    private double salary;
    private BankAccount bankAccount;
    
    public Employee(String n, double s) {
        this.name = n;
        salary = s;
        this.bankAccount = new BankAccountImpl("PeDeMeia", 0);
    }

    public BankAccount getBankAccount(){
        return this.bankAccount;
    }

    public double getSalary() {
        return salary;
    }
}