package pl.training.module06.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public record Money(BigDecimal value, Currency currency) {

    private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();

    public Money {
        FORMATTER.setCurrency(currency);
    }

    public static Money of(double value) {
        return new Money(BigDecimal.valueOf(value), Currency.getInstance(Locale.getDefault()));
    }

    public Money add(Money money) {
        checkCurrencyCompatibility(money);
        return new Money(value.add(money.value), currency);
    }

    public Money subtract(Money money) {
        checkCurrencyCompatibility(money);
        return new Money(value.subtract(money.value), currency);
    }

    private void checkCurrencyCompatibility(Money money) {
        if (!money.hasCurrency(currency)) {
            throw new IllegalArgumentException("Currency code not compatible");
        }
    }

    public boolean isGreaterOrEqual(Money money) {
        checkCurrencyCompatibility(money);
        return value.compareTo(money.value) >= 0;
    }

    public boolean hasCurrency(Currency currency) {
        return this.currency == currency;
    }

    @Override
    public String toString() {
        return FORMATTER.format(value);
    }

}
