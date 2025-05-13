package pl.training.module04_05.model;

import java.util.Objects;

public class Account {

    private final String number;
    private Money balance;

    public Account(String number, Money balance) {
        this.number = number;
        this.balance = balance;
    }

    public void deposit(Money amount) {
        balance = balance.add(amount);
    }

    public void withdraw(Money amount) throws InsufficientFundsException {
        checkBalance(amount);
        balance = balance.subtract(amount);
    }

    private void checkBalance(Money amount) throws InsufficientFundsException {
        if (amount.isGreaterOrEqual(balance)) {
            throw new InsufficientFundsException();
        }
    }

    public String getNumber() {
        return number;
    }

    public Money getBalance() {
        return balance;
    }

    public boolean hasNumber(String number) {
        return this.number.equals(number);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(number, account.number) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, balance);
    }

}
