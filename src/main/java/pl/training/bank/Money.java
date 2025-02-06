package pl.training.bank;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public record Money(BigDecimal value, Currency currency) {

    public final static Currency DEFAULT_CURRENCY = Currency.PLN;

    public Money {
        if (value.compareTo(ZERO) < 0) {
            throw new InvalidValueException();
        }
    }

    public Money() {
        this(ZERO, DEFAULT_CURRENCY);
    }

    public Money add(Money money) {
        if (hasCurrency(money.currency)) {
            var resultValue = value.add(money.value);
            return new Money(resultValue, currency);
        }
        return null;
    }

    public Money subtract(Money money) {
        if (hasCurrency(money.currency)) {
            var resultValue = value.subtract(money.value);
            return new Money(resultValue, currency);
        }
        return null;
    }

    public boolean isGreaterOrEqual(Money money) {
        return hasCurrency(money.currency) && value.compareTo(money.value) >= 0;
    }

    private boolean hasCurrency(Currency currency) {
        return this.currency.equals(currency);
    }

}
