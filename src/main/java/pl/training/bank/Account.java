package pl.training.bank;

public class Account {

    private final String number;
    private Money balance = new Money();

    public Account(String number) {
        this.number = number;
    }

    public void deposit(Money money) {
        balance = balance.add(money);
    }

    public boolean withdraw(Money money) {
        boolean result = false;
        if (balance.isGreaterOrEqual(money)) {
            balance = balance.subtract(money);
            result = true;
        }
        return result;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
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
    public String toString() {
        return "Account{" + "number='" + number + "', balance=" + balance + "}";
    }

}
