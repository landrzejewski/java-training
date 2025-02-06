package pl.training.bank;

public class AccountNotFoundException extends Exception {

    private String accountNumber;

    public AccountNotFoundException(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountNotFoundException() {
    }

}
