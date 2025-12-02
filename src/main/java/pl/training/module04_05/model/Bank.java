package pl.training.module04_05.model;

import pl.training.module04_05.model.generator.AccountNumberGenerator;
import pl.training.module04_05.model.repository.AccountRepository;

public class Bank {

    private static final Money NEW_ACCOUNT_BONUS = new Money(100, Currency.PLN);

    private AccountNumberGenerator numberGenerator;
    private AccountRepository repository;

    public Bank(AccountNumberGenerator numberGenerator, AccountRepository repository) {
        this.numberGenerator = numberGenerator;
        this.repository = repository;
    }

    public Account createAccount() {
        return createAccount(false);
    }

    public Account createAccount(boolean isPremium) {
        var number = numberGenerator.next();
        var account = isPremium ? new PremiumAccount(number, NEW_ACCOUNT_BONUS) : new Account(number);
        return repository.save(account);
    }

    public boolean deposit(String accountNumber, Money amount) {
        var account = repository.findByNumber(accountNumber);
        if (account == null) {
            return false;
        }
        account.deposit(amount);
        return true;
    }

    public boolean withdraw(String accountNumber, Money amount) {
        var account = repository.findByNumber(accountNumber);
        if (account == null) {
            return false;
        }
        account.withdraw(amount);
        return true;
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, Money amount) {
        if (withdraw(fromAccountNumber, amount)) {  // todo tx
            var isSuccess = deposit(toAccountNumber, amount);
            if (!isSuccess) {
                deposit(fromAccountNumber, amount);
            }
        }
    }

    public BankSummary getSummary() {
        var totalBalance = new Money(0, Currency.PLN);
        for (var account: repository.findAll()) {
            totalBalance = totalBalance.add(account.balance);
        }
        return new BankSummary(repository.count(), totalBalance);
    }

}
