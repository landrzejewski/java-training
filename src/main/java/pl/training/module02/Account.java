package pl.training.module02;

public class Account {

    String number = "";
    double balance; // domyślnie 0.0
    boolean isActive; // domyślnie false

    /*public Account() {
        isActive = true;
        System.out.println("New account created");
    }*/

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        balance = balance - amount;
    }

    public void printBalance() {
        System.out.println("Balance: " + balance);
    }

}
