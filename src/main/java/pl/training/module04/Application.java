package pl.training.module04;

import pl.training.module04.model.Account;
import pl.training.module04.model.Currency;
import pl.training.module04.model.Money;
import pl.training.module04.model.PremiumAccount;

public class Application {

    static void main(String[] args) {
        Money money = new Money(10, Currency.PLN);
        Account account = new PremiumAccount("0001" , money);
        account.deposit(money);

        System.out.println(account);
    }

}
