package pl.training.module04_05.model;

public class PremiumAccount extends Account {

    public PremiumAccount(String number, Money balance) {
        super(number, balance);
    }

    @Override
    public void withdraw(Money money) {
        balance = balance.subtract(money);
    }

}
