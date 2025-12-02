package pl.training.module04.model;

public class Bank {

    private static final Money NEW_ACCOUNT_BONUS = new Money(100, Currency.PLN);

    private AccountNumberGenerator numberGenerator;
    private AccountRepository repository;

    public Bank(AccountNumberGenerator numberGenerator, AccountRepository repository) {
        this.numberGenerator = numberGenerator;
        this.repository = repository;
    }

    public Account createAccount(boolean isPremium) {
        var number = numberGenerator.next();
        var account = isPremium ? new PremiumAccount(number, NEW_ACCOUNT_BONUS) : new Account(number);
        return repository.save(account);
    }

    public void deposit(String accountNumber, Money amount) {
        var account = repository.findByNumber(accountNumber);
        if (account == null) {
            return;
        }
        account.deposit(amount);
        repository.save(account);
    }

    public void withdraw(String accountNumber, Money amount) {
        var account = repository.findByNumber(accountNumber);
        if (account == null) {
            return;
        }
        account.withdraw(amount);
        repository.save(account);
    }

}
