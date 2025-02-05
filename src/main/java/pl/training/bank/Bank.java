package pl.training.bank;

public class Bank {

    private String name = "";
    private Account[] accounts = new Account[5];
    private int index;

    public void add(Account account) {
        if (index == accounts.length - 1) {
            var newAccounts = new Account[accounts.length * 2];
            System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
            accounts = newAccounts;
        }
        accounts[index++] = account;
    }

    public void printReport() {
        System.out.println("Bank balance:");
        var totalBalance = new Money();
        for (int accountIndex = 0; accountIndex < index; accountIndex++) {
            var account = accounts[accountIndex];
            System.out.println(account);
            totalBalance.add(account.getBalance());
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Total balance: " + totalBalance);
    }

    public boolean deposit(String accountNumber, Money money) {
        var account = getAccountByNumber(accountNumber);
        if (account != null) {
            account.deposit(money);
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountNumber, Money money) {
        var account = getAccountByNumber(accountNumber);
        if (account != null) {
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

    private Account getAccountByNumber(String number) {
        int accountIndex = 0;
        while (accountIndex < index) {
            var account = accounts[accountIndex];
            if (account.hasNumber(number)) {
                return account;
            }
            accountIndex++;
        }
        return null;
    }

}
