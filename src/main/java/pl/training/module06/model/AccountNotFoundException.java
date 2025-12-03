package pl.training.module06.model;

public class AccountNotFoundException extends RuntimeException {

    private final String accountNumber;

    public AccountNotFoundException(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

}
