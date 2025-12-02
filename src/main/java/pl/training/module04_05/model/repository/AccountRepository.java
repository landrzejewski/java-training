package pl.training.module04_05.model.repository;

import pl.training.module04_05.model.Account;

public interface AccountRepository {

    Account save(Account account);

    Account findByNumber(String accountNumber);

    Account[] findAll();

    long count();
    
}
