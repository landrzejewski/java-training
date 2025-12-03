package pl.training.module06.model.repository;

import pl.training.module06.model.Account;

public interface AccountRepository {

    Account save(Account account);

    Account findByNumber(String accountNumber);

    Account[] findAll();

    long count();
    
}
