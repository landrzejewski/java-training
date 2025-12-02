package pl.training.module04.model;

public interface AccountRepository {

    Account save(Account account);

    Account findByNumber(String accountNumber);
    
}
