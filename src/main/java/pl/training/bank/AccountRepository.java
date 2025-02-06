package pl.training.bank;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);

    List<Account> findAll();

    Optional<Account> findByNumber(String number);

}
