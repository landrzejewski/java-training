package pl.training;

import pl.training.bank.Account;
import pl.training.bank.Bank;
import pl.training.bank.Money;

public class Application {

    public static void main(String[] args) {
        var account = new Account();
        account.setNumber("0000000001");

        var secondAccount = new Account();
        secondAccount.setNumber("0000000002");

        var bank = new Bank();
        bank.add(account);
        bank.add(secondAccount);

        var money = new Money();
        money.setValue(100);
        bank.deposit("0000000001", money);

        bank.printReport();
    }

}
