package Lesson3;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным.");
        }
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма депозита не может быть отрицательной.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма снятия не может быть отрицательной.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Недостаточно средств для снятия.");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        System.out.println("Начальный баланс: " + account.getBalance());

        account.deposit(500);
        System.out.println("Баланс после депозита 500: " + account.getBalance());

        account.withdraw(200);
        System.out.println("Баланс после снятия 200: " + account.getBalance());

        try {
            account.withdraw(2000); // Это вызовет ошибку
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            account.deposit(-100); // Это вызовет ошибку
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            account.withdraw(-50); // Это вызовет ошибку
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}