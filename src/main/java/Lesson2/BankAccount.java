package Lesson2;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

class BankAccountTest {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(100.0);

        System.out.println("Начальный баланс: " + account.getBalance());

        account.deposit(50.0);
        System.out.println("Баланс после депозита 50.0: " + account.getBalance());

        account.withdraw(30.0);
        System.out.println("Баланс после снятия 30.0: " + account.getBalance());

        account.withdraw(150.0);
        System.out.println("Баланс после снятия 150.0: " + account.getBalance());

        account.deposit(-20.0);
        System.out.println("Баланс после депозита -20.0: " + account.getBalance());
    }
}