package pl.training.bank;

public class Money {

    private double value;
    private String currency = "PLN";

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

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Money{" + "value=" + value + ", currency='" + currency + "'}";
    }

}
