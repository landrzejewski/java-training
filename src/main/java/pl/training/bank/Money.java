package pl.training.bank;

import java.util.Objects;

public class Money {

    public final static String DEFAULT_CURRENCY = "PLN";

    private double value;
    private final String currency;

    public Money(double value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public Money() {
        this(0, DEFAULT_CURRENCY);
    }

    public void add(Money money) {
        if (hasCurrency(money.currency)) {
            value = value + money.value;
        }
    }

    public void subtract(Money money) {
        if (hasCurrency(money.currency)) {
            value = value - money.value;
        }
    }

    public boolean isGreaterOrEqual(Money money) {
        return hasCurrency(money.currency) && value >= money.value;
    }

    private boolean hasCurrency(String currency) {
        return this.currency.equals(currency);
    }

    public void setValue(double value) {
        if (value >= 0) {
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(value, money.value) == 0 && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return "Money{" + "value=" + value + ", currency='" + currency + "'}";
    }

}
