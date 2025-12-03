package pl.training.module06.model;

import pl.training.module06.model.generator.AccountNumberGenerator;
import pl.training.module06.model.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;

public class Bank {

    private static final Money NEW_ACCOUNT_BONUS = new Money(TEN, Currency.getInstance(Locale.getDefault()));

    private final AccountNumberGenerator numberGenerator;
    private final AccountRepository repository;

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
        var account = findAccount(accountNumber);
        account.deposit(amount);
        return true;
    }

    public boolean withdraw(String accountNumber, Money amount) {
        var account = findAccount(accountNumber);
        account.withdraw(amount);
        return true;
    }

    private Account findAccount(String accountNumber) {
        var result = repository.findByNumber(accountNumber);
        if (result.isEmpty()) {
            throw new AccountNotFoundException(accountNumber);
        }
        return result.get();
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, Money amount) {
        withdraw(fromAccountNumber, amount);
        try {
            deposit(toAccountNumber, amount);
        } catch (AccountNotFoundException accountNotFoundException) {
            deposit(fromAccountNumber, amount);
            throw accountNotFoundException;
        }
    }

    public BankSummary getSummary() {
        var totalBalance = new Money(ZERO, Currency.getInstance(Locale.getDefault()));
        /*for (var account: repository.findAll()) {
            totalBalance = totalBalance.add(account.balance);
        }*/
        return new BankSummary(repository.count(), totalBalance);
    }

}
