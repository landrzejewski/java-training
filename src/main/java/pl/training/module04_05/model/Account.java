package pl.training.module04_05.model;

import java.util.Objects;

public class Account {

    private static final Money DEFAULT_BALANCE = new Money(0, Currency.PLN);

    private String number;
    protected Money balance;

    public Account(String number) {
        this(number, DEFAULT_BALANCE);
    }

    public Account(String number, Money balance) {
        this.number = number;
        this.balance = balance;
    }

    public void deposit(Money money) {
        balance = balance.add(money);
    }

    public void withdraw(Money money) {
        if (balance.isGreaterOrEqual(money)) {
            balance = balance.subtract(money);
        }
    }

    public boolean hasNumber(String number) {
        return this.number.equals(number);
    }

    public String getNumber() {
        return number;
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

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }

}
