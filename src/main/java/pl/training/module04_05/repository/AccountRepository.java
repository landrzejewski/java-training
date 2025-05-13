package pl.training.module04_05.repository;

import pl.training.module04_05.model.Account;

public interface AccountRepository {

    Account save(Account account);

    Account[] findAll();

    Account findByNumber(String number);

}
