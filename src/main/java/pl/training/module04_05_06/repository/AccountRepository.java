package pl.training.module04_05_06.repository;

import pl.training.module04_05_06.Account;

public interface AccountRepository {

    Account save(Account account);

    Account[] findAll();

    Account findByNumber(String number);

}
