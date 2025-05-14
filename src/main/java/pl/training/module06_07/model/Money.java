package pl.training.module06_07.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public record Money(BigDecimal value, Currency currency) {

    private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance(Locale.getDefault());

    public Money {
        FORMATTER.setCurrency(currency);
    }

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

    @Override
    public String toString() {
        return FORMATTER.format(value);
    }

}
