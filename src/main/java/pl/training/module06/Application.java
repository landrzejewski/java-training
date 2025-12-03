package pl.training.module06;

import pl.training.module06.model.Bank;
import pl.training.module06.model.Money;
import pl.training.module06.model.generator.AccountNumberGenerator;
import pl.training.module06.model.generator.UuidAccountNumberGenerator;
import pl.training.module06.model.repository.AccountRepository;
import pl.training.module06.model.repository.TreeMapAccountRepository;

public class Application {

    static void main(String[] args) {
        AccountNumberGenerator generator = new UuidAccountNumberGenerator();
        AccountRepository repository = new TreeMapAccountRepository();
        Bank bank = new Bank(generator, repository);

        String firstAccountNumber = bank.createAccount(false)
                .getNumber();
        String secondAccountNumber = bank.createAccount(true)
                .getNumber();

        bank.deposit(firstAccountNumber, Money.of(100));
        bank.deposit(secondAccountNumber, Money.of(100));
        bank.withdraw(firstAccountNumber, Money.of(50));
        bank.transfer(secondAccountNumber, firstAccountNumber, Money.of(10));

        var summary = bank.getSummary();
        System.out.println(summary);
    }

}
