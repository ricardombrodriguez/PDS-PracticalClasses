
class BankAccountProxy implements BankAccount {

    private BankAccountImpl bank;

    public BankAccountProxy(String bank, double initialDeposit) {
        this.bank = new BankAccountImpl(bank, initialDeposit);
    }

    @Override public void deposit(double amount) {
        this.bank.deposit(amount);
    }

    @Override public boolean withdraw(double amount) {

        if (Company.user == User.OWNER) {
            return this.bank.withdraw(amount);
        }

        System.out.println("Sorry :/ You don't have access.");
        return false;
    }

    @Override public double balance() {

        if (Company.user == User.OWNER) {
            return this.bank.balance();
        }

        System.out.println("Sorry :/ You don't have access.");
        return -1;
    }
}