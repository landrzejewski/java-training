package pl.training.module_06.repository;

import pl.training.module_06.model.Account;

import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);

    Account[] findAll();

    Optional<Account> findByNumber(String number);

}
