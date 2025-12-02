package pl.training.module04.model;

import java.util.Objects;

public class Money {

    private double value;
    private Currency currency;

    public Money(double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public Money add(Money money) {
        if (!money.hasCurrency(currency)) {
            return null;
        }
        return new Money(value + money.value, currency);
    }

    public Money subtract(Money money) {
        if (!money.hasCurrency(currency)) {
            return null;
        }
        return new Money(value - money.value, currency);
    }

    public boolean isGreaterOrEqual(Money money) {
        if (!money.hasCurrency(currency)) {
            return false; // to fix
        }
        return value >= money.value;
    }

    public boolean hasCurrency(Currency currency) {
        return this.currency == currency;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(value, money.value) == 0 && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                ", currency=" + currency +
                '}';
    }

}
