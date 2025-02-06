package pl.training.bank;

import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);

    Account[] findAll();

    Optional<Account> findByNumber(String number);

}
