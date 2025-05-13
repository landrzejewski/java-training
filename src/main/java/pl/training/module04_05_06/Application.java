package pl.training.module04_05_06;

public class Application {

    private static final Currency DEFAULT_CURRENCY = Currency.PLN;

    public static void main(String[] args) {
       var money = Money.of(10.0, DEFAULT_CURRENCY);
       var otherMoney = Money.of(20.0, DEFAULT_CURRENCY);
       System.out.println(money.add(otherMoney));
    }

}
