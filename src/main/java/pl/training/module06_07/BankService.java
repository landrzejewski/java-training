package pl.training.module06_07;

import pl.training.module06_07.model.Account;
import pl.training.module06_07.model.Currency;
import pl.training.module06_07.model.InsufficientFundsException;
import pl.training.module06_07.model.Money;
import pl.training.module06_07.repository.AccountRepository;

import java.math.BigDecimal;

public class BankService {

    private static final double DEFAULT_BALANCE = 0;
    private static final String SEPARATOR = ": ";

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
        return repository.findByNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));
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

    public String generateReport() {
        var report = new StringBuilder("Bank accounts:").append(System.lineSeparator());
        repository.findAllStream()
                .forEach(account -> report
                        .append(account.getNumber())
                        .append(SEPARATOR)
                        .append(account)
                        .append(System.lineSeparator()));
        var totalBalance = repository.findAllStream()
                .map(Account::getBalance)
                .reduce(Money.of(0, Currency.PLN), Money::add);
        report.append("Total balance: ")
                .append(totalBalance)
                .append(System.lineSeparator());
        return report.toString();
    }

}
