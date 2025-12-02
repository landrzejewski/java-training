package pl.training.module04_05;

import pl.training.module04_05.model.*;
import pl.training.module04_05.model.generator.AccountNumberGenerator;
import pl.training.module04_05.model.generator.UuidAccountNumberGenerator;
import pl.training.module04_05.model.repository.AccountRepository;
import pl.training.module04_05.model.repository.ArrayAccountRepository;

public class Application {

    static void main(String[] args) {
        AccountNumberGenerator generator = new UuidAccountNumberGenerator();
        AccountRepository repository = new ArrayAccountRepository();
        Bank bank = new Bank(generator, repository);

        String firstAccountNumber = bank.createAccount(false)
                .getNumber();
        String secondAccountNumber = bank.createAccount(true)
                .getNumber();

        bank.deposit(firstAccountNumber, new Money(100, Currency.PLN));
        bank.deposit(secondAccountNumber, new Money(100, Currency.PLN));
        bank.withdraw(firstAccountNumber, new Money(50, Currency.PLN));
        bank.transfer(secondAccountNumber, firstAccountNumber, new Money(10, Currency.PLN));

        var summary = bank.getSummary();
        System.out.println(summary);
    }

}
