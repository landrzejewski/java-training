package pl.training.bank;

public record Money(double value, Currency currency) {

    public final static Currency DEFAULT_CURRENCY = Currency.PLN;

    public Money {
        if (value < 0) {
            throw new InvalidValueException();
        }
    }

    public Money() {
        this(0, DEFAULT_CURRENCY);
    }

    public Money add(Money money) {
        if (hasCurrency(money.currency)) {
            return new Money(value + money.value, currency);
        }
        return null;
    }

    public Money subtract(Money money) {
        if (hasCurrency(money.currency)) {
            return new Money(value - money.value, currency);
        }
        return null;
    }

    public boolean isGreaterOrEqual(Money money) {
        return hasCurrency(money.currency) && value >= money.value;
    }

    private boolean hasCurrency(Currency currency) {
        return this.currency.equals(currency);
    }

}
