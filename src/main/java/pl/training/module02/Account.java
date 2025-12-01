package pl.training.module02;

public class Account {

    String number = "";
    double balance;  // domyślnie 0.0
    boolean isActive; // domyślnie false

    /*public pl.training.module02.Account() {
        isActive = true;
    }*/

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        balance = balance - amount;
    }

    public void printBalance() {
        System.out.println(balance);
    }

    @Override
    public String toString() {
        return "pl.training.module02.Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", isActive=" + isActive +
                '}';
    }

}
