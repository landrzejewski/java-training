package pl.training.module_06.model;

import java.math.BigDecimal;

public record Money(BigDecimal value, Currency currency) {

    /*public Money {
        if (value.signum() == -1) {
            throw new IllegalArgumentException("Money value cannot be negative");
        }
    }*/

    public static Money of(double value, Currency currency) {
        return new Money(new BigDecimal(value), currency);
    }

    public Money add(Money money) {
        checkCurrencyCompatibility(money);
        return new Money(value.add(money.value), currency);
    }

    public Money subtract(Money money) {
        checkCurrencyCompatibility(money);
        return new Money(value.subtract(money.value), currency);
    }

    public boolean isGreaterOrEqual(Money money) {
        checkCurrencyCompatibility(money);
        return value.compareTo(money.value) >= 0;
    }

    private void checkCurrencyCompatibility(Money money) {
        if (currency != money.currency()) {
            throw new IllegalArgumentException("Currency mismatch");
        }
    }

}
