package pl.training.module06.model;

import pl.training.module06.model.generator.AccountNumberGenerator;
import pl.training.module06.model.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;

public class Bank {

    private static final Logger LOGGER = Logger.getLogger(Bank.class.getName());

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

    public boolean withdraw(String accountNumber, Money amount) throws  InsufficientFundsException {
        var account = findAccount(accountNumber);
        account.withdraw(amount);
        return true;
    }

    private Account findAccount(String accountNumber) {
       /*var result = repository.findByNumber(accountNumber);
        if (result.isEmpty()) {
            throw new AccountNotFoundException(accountNumber);
        }
        return result.get();*/
        return repository.findByNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, Money amount) {
        try {
            withdraw(fromAccountNumber, amount);
            deposit(toAccountNumber, amount);
        } catch (AccountNotFoundException accountNotFoundException) {
            deposit(fromAccountNumber, amount);
            throw accountNotFoundException;
        } catch (InsufficientFundsException insufficientFundsException) {
            LOGGER.log(Level.FINEST, "Insufficient funds!");
        } finally {
            LOGGER.log(Level.FINEST, "After transfer");
        }
    }

    public BankSummary getSummary() {
        /*var totalBalance = new Money(ZERO, Currency.getInstance(Locale.getDefault()));
        for (var account: repository.findAll()) {
            totalBalance = totalBalance.add(account.balance);
        }*/
        var totalBalance = repository.findAll()
                .map(account -> account.balance)
                .reduce(Money.of(0), Money::add);
        return new BankSummary(repository.count(), totalBalance);
    }

}
