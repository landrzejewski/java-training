package pl.training.module06;

public class AccountNotFoundException extends Exception {

    private final String accountNumber;

    public AccountNotFoundException(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountNotFoundException() {
        this("");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

}
