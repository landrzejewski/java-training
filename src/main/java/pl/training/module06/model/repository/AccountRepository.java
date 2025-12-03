package pl.training.module06.model.repository;

import pl.training.module06.model.Account;

import java.util.Optional;
import java.util.stream.Stream;

public interface AccountRepository {

    Account save(Account account);

    Optional<Account> findByNumber(String accountNumber);

    Page findAll(PageRequest pageRequest);

    Stream<Account> findAll();

    long count();
    
}
