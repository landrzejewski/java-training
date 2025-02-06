package pl.training.bank;

public class Bank {

    private final String name;
    private final AccountRepository repository;

    public Bank(String name, AccountRepository accountRepository) {
        this.name = name;
        this.repository = accountRepository;
    }

    public void add(Account account) {
        repository.save(account);
    }

    public void printReport() {
        System.out.println("Bank balance:");
        var totalBalance = new Money();
        for (Account account : repository.findAll()) {
            System.out.println(account);
            totalBalance.add(account.getBalance());
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Total balance: " + totalBalance);
    }

    public boolean deposit(String accountNumber, Money money) {
        var result = repository.findByNumber(accountNumber);
        if (result.isPresent()) {
            var account = result.get();
            account.deposit(money);
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountNumber, Money money) {
        var result = repository.findByNumber(accountNumber);
        if (result.isPresent()) {
            var account = result.get();
            account.withdraw(money);
            return true;
        }
        return false;
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, Money money) {
        withdraw(fromAccountNumber, money);
        var result = deposit(toAccountNumber, money);
        if (!result) {
            deposit(fromAccountNumber, money);
        }
    }

}
