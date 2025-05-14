package pl.training.module06.model;

import java.util.Currency;

public final class PremiumAccount extends Account {

    private static final Money ON_START_BONUS = Money.of(100, Currency.getInstance("PLN"));

    public PremiumAccount(String number) {
        super(number, ON_START_BONUS);
    }

    @Override
    public void withdraw(Money amount) {
        balance = balance.subtract(amount);
    }

    public boolean hasDebit() {
        return balance.value().signum() < 0;
    }

}
