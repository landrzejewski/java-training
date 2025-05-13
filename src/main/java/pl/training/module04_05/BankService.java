package pl.training.module04_05;

import pl.training.module04_05.model.Account;
import pl.training.module04_05.model.Currency;
import pl.training.module04_05.model.InsufficientFundsException;
import pl.training.module04_05.model.Money;
import pl.training.module04_05.repository.AccountRepository;

public class BankService {

    private static final double DEFAULT_BALANCE = 0;

    private final AccountNumberGenerator numberGenerator;
    private final AccountRepository repository;

    public BankService(AccountNumberGenerator accountNumberGenerator, AccountRepository repository) {
        this.numberGenerator = accountNumberGenerator;
        this.repository = repository;
    }

    public Account createAccount(Currency currency) {
        var number = numberGenerator.next();
        var balance = Money.of(DEFAULT_BALANCE, currency);
        var account = new Account(number, balance);
        return repository.save(account);
    }

    public void deposit(String accountNumber, Money amount) throws AccountNotFoundException {
        var account = getAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, Money amount) throws AccountNotFoundException, InsufficientFundsException {
        var account = getAccount(accountNumber);
        account.withdraw(amount);
    }

    private Account getAccount(String accountNumber) throws AccountNotFoundException {
        var account = repository.findByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        return account;
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, Money amount) throws InsufficientFundsException, AccountNotFoundException {
        withdraw(fromAccountNumber, amount);
        try {
            deposit(toAccountNumber, amount);
        } catch (AccountNotFoundException accountNotFoundException) {
            deposit(fromAccountNumber, amount);
            throw accountNotFoundException;
        }
    }

    public void printReport() {
        System.out.println("Bank accounts:");
        var totalBalance = Money.of(DEFAULT_BALANCE, Currency.PLN); // do ulepszenia
        for (var account : repository.findAll()) {
            System.out.println(account.getNumber() + ": " + account.getBalance());
            totalBalance = totalBalance.add(account.getBalance());
        }
        System.out.println("Total balance: " + totalBalance);
    }

}
